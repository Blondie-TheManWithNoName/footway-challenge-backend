package footway.challenge.orders;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import footway.challenge.entities.Order;
import footway.challenge.entities.PhysicalProduct;
import footway.challenge.digitalProduct.dtos.DigitalProductDTO;
import footway.challenge.digitalProduct.dtos.DigitalProductProjection;
import footway.challenge.entities.DigitalProduct;
import footway.challenge.exceptions.OrderNotFoundException;
import footway.challenge.exceptions.ProductNotFoundException;
import footway.challenge.mappings.dtos.MappingsDTO;
import footway.challenge.orders.dtos.CreateOrderDTO;
import footway.challenge.orders.dtos.OrderDTO;
import footway.challenge.orders.dtos.OrdersDTO;
import footway.challenge.repos.OrderRepo;
import footway.challenge.repos.PhysicalProductRepo;
import footway.challenge.repos.DigitalProductRepo;

@Service
public class OrderService {

        @PersistenceContext
        private EntityManager entityManager;

        private final OrderRepo orderRepo;
        private final DigitalProductRepo digitalProductRepo;

        public OrderService(OrderRepo orderRepo,
                        DigitalProductRepo digitalProductRepo) {
                this.orderRepo = orderRepo;
                this.digitalProductRepo = digitalProductRepo;
        }

        public List<OrdersDTO> getAllOrders() {

                List<OrdersDTO> orders = orderRepo.findAll().stream().map(order -> new OrdersDTO(order.getId(),
                                order.getDigitalProducts()))
                                .toList();

                return orders;
        }

        public OrderDTO getOrder(int id) {
                final Order order = orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException());
                final OrderDTO result = new OrderDTO(order.getId(), order.getDigitalProducts());

                return result;
        }

        public Page<DigitalProductDTO> getDigitalProducts(int id, int page, int take, String search) {

                Pageable pageable = PageRequest.of(page - 1, take, Sort.by("sku"));

                Page<DigitalProductProjection> digitalProducts;
                if (!search.isEmpty())
                        digitalProducts = orderRepo.findByOrderIdAndSearch(id, search + "*", pageable);
                else
                        digitalProducts = orderRepo.findByOrderId(id, pageable);

                return digitalProducts.map(product -> new DigitalProductDTO(product.getSku(), product.getEAN(),
                                product.getName()));

        }

        public Order createOrder(CreateOrderDTO body) {

                final List<DigitalProduct> digitalProducts = new ArrayList<>();
                for (String digitalProductSku : body.getDigitalProductsSkus())
                        digitalProducts.add(digitalProductRepo.findBySku(digitalProductSku)
                                        .orElseThrow(() -> new ProductNotFoundException(digitalProductSku)));

                final Order order = new Order(digitalProducts);

                final Order response = orderRepo.save(order);

                return response;
        }

        public void deleteOrder(int id) {
                orderRepo.findById(id)
                                .orElseThrow(() -> new OrderNotFoundException());

                orderRepo.deleteById(id);

        }

}
