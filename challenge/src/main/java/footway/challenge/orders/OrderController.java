
package footway.challenge.orders;

import org.springframework.web.bind.annotation.RestController;

import footway.challenge.digitalProduct.dtos.DigitalProductDTO;
import footway.challenge.entities.Mappings;
import footway.challenge.entities.Order;
import footway.challenge.mappings.dtos.CreateMappingDTO;
import footway.challenge.mappings.dtos.MappingsDTO;
import footway.challenge.orders.dtos.CreateOrderDTO;
import footway.challenge.orders.dtos.OrdersDTO;
import footway.challenge.orders.dtos.OrderDTO;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrdersDTO>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable int id) throws NotFoundException {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @GetMapping("/orders/{id}/digital-products")
    public ResponseEntity<Page<DigitalProductDTO>> getDigitalProducts(@PathVariable int id,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int take,
            @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(orderService.getDigitalProducts(id, page, take, search));
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderDTO body) {
        return ResponseEntity.created(URI.create("/orders/"))
                .body(orderService.createOrder(body));
    }

    // @PutMapping("/mappings/{id}")
    // public ResponseEntity<Mappings> updateMapping(@PathVariable String SKU,
    // @Valid @RequestBody UpdatePhysicalProductDTO body) {
    // return ResponseEntity.ok(orderService.updateMapping(SKU, body));
    // }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Mappings> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
