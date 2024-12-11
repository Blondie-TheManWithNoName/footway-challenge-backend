package footway.challenge.mappings;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import footway.challenge.entities.Mappings;
import footway.challenge.entities.Order;
import footway.challenge.entities.PhysicalProduct;
import footway.challenge.entities.DigitalProduct;
import footway.challenge.exceptions.MappingNotFoundException;
import footway.challenge.exceptions.OrderNotFoundException;
import footway.challenge.exceptions.MappingAlreadyExistsException;
import footway.challenge.exceptions.ProductNotFoundException;
import footway.challenge.mappings.dtos.CreateMappingDTO;
import footway.challenge.mappings.dtos.MappingsDTO;
import footway.challenge.repos.MappingsRepo;
import footway.challenge.repos.OrderRepo;
import footway.challenge.repos.PhysicalProductRepo;
import footway.challenge.repos.DigitalProductRepo;

@Service
public class MappingsService {

        @PersistenceContext
        private EntityManager entityManager;

        private final MappingsRepo mappingsRepo;
        private final PhysicalProductRepo physicalProductRepo;
        private final DigitalProductRepo digitalProductRepo;
        private final OrderRepo orderRepo;

        public MappingsService(MappingsRepo mappingsRepo, PhysicalProductRepo physicalProductRepo,
                        DigitalProductRepo digitalProductRepo, OrderRepo orderRepo) {
                this.mappingsRepo = mappingsRepo;
                this.physicalProductRepo = physicalProductRepo;
                this.digitalProductRepo = digitalProductRepo;
                this.orderRepo = orderRepo;
        }

        public List<MappingsDTO> getAllMappings(String physicalSku, String digitalSku, int orderId) {

                List<Mappings> mappings;

                orderRepo.findById(orderId)
                                .orElseThrow(() -> new OrderNotFoundException());

                if (physicalSku != null && !physicalSku.isEmpty() && digitalSku != null && !digitalSku.isEmpty())
                        mappings = mappingsRepo.findByProducts(physicalSku, digitalSku, orderId);

                else if (physicalSku != null && !physicalSku.isEmpty())
                        mappings = mappingsRepo.findByPhysicalProduct(physicalSku, orderId);

                else if (digitalSku != null && !digitalSku.isEmpty())
                        mappings = mappingsRepo.findByDigitalProduct(digitalSku, orderId);

                else
                        mappings = mappingsRepo.findByOrderId(orderId);

                return mappings.stream().map(mapping -> new MappingsDTO(mapping.getId(),
                                mapping.getDigitalProduct().getSku(),
                                mapping.getPhysicalProduct().getSku()))
                                .toList();
        }

        public Mappings getMapping(int id) {
                return mappingsRepo.findById(id)
                                .orElseThrow(() -> new MappingNotFoundException());
        }

        public Mappings createMapping(CreateMappingDTO body) {

                final List<Mappings> dupProduct = mappingsRepo.findByProducts(body.getPhysicalProductSku(),
                                body.getDigitalProductSku(), body.getOrder());

                if (!dupProduct.isEmpty())
                        throw new MappingAlreadyExistsException();

                final PhysicalProduct physicalProduct = physicalProductRepo.findBySku(body.getPhysicalProductSku())
                                .orElseThrow(() -> new ProductNotFoundException());

                final DigitalProduct digitalProduct = digitalProductRepo.findBySku(body.getDigitalProductSku())
                                .orElseThrow(() -> new ProductNotFoundException());

                final Order order = orderRepo.findById(body.getOrder())
                                .orElseThrow(() -> new OrderNotFoundException());

                final Mappings mapping = new Mappings(physicalProduct, digitalProduct, order);

                final Mappings response = mappingsRepo.save(mapping);

                return response;
        }

        public void deleteMapping(int id) {
                mappingsRepo.findById(id)
                                .orElseThrow(() -> new MappingNotFoundException());

                mappingsRepo.deleteById(id);

        }

}
