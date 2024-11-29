
package footway.challenge.physicalProduct;

import org.springframework.web.bind.annotation.RestController;

import footway.challenge.entities.PhysicalProduct;
import footway.challenge.physicalProduct.dtos.CreatePhysicalProductDTO;
import footway.challenge.physicalProduct.dtos.UpdatePhysicalProductDTO;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PhysicalProductController {

    private final PhysicalProductService physicalProductService;

    public PhysicalProductController(PhysicalProductService physicalProductService) {
        this.physicalProductService = physicalProductService;
    }

    @GetMapping("/physical-products")
    public ResponseEntity<List<PhysicalProduct>> getAllPhysicalProducts() {
        return ResponseEntity.ok(physicalProductService.getAllPhysicalProducts());
    }

    @GetMapping("/physical-products/{SKU}")
    public ResponseEntity<PhysicalProduct> getPhysicalProduct(@PathVariable String SKU) throws NotFoundException {
        return ResponseEntity.ok(physicalProductService.getPhysicalProduct(SKU));
    }

    @PostMapping("/physical-products")
    public ResponseEntity<PhysicalProduct> createPhysicalProduct(@Valid @RequestBody CreatePhysicalProductDTO body) {
        return ResponseEntity.created(URI.create("/physical-products/" + body.getSku()))
                .body(physicalProductService.createPhysicalProduct(body));
    }

    @PutMapping("/physical-products/{SKU}")
    public ResponseEntity<PhysicalProduct> updatePhysicalProduct(@PathVariable String SKU,
            @Valid @RequestBody UpdatePhysicalProductDTO body) {
        return ResponseEntity.ok(physicalProductService.updatePhysicalProduct(SKU, body));
    }

    @DeleteMapping("/physical-products/{SKU}")
    public ResponseEntity<PhysicalProduct> deletePhysicalProduct(@PathVariable String SKU) {
        physicalProductService.deletePhysicalProduct(SKU);
        return ResponseEntity.noContent().build();
    }
}
