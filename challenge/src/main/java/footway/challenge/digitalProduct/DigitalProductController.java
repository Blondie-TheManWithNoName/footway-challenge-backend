
package footway.challenge.digitalProduct;

import org.springframework.web.bind.annotation.RestController;

import footway.challenge.entities.DigitalProduct;
import footway.challenge.entities.DigitalProduct;
import footway.challenge.digitalProduct.dtos.CreateDigitalProductDTO;
import footway.challenge.digitalProduct.dtos.DigitalProductDTO;
import footway.challenge.digitalProduct.dtos.UpdateDigitalProductDTO;
import footway.challenge.digitalProduct.dtos.CreateDigitalProductDTO;
import footway.challenge.digitalProduct.dtos.UpdateDigitalProductDTO;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class DigitalProductController {

    private final DigitalProductService digitalProductService;

    public DigitalProductController(DigitalProductService digitalProductService) {
        this.digitalProductService = digitalProductService;
    }

    @GetMapping("/digital-products")
    public ResponseEntity<Page<DigitalProductDTO>> getAllDigitalProducts(
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int take,
            @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(digitalProductService.getAllDigitalProducts(page, take,
                search));
    }

    @GetMapping("/digital-products/{SKU}")
    public ResponseEntity<DigitalProduct> getDigitalProduct(@PathVariable String SKU) throws NotFoundException {
        return ResponseEntity.ok(digitalProductService.getDigitalProduct(SKU));
    }

    @PostMapping("/digital-products")
    public ResponseEntity<DigitalProduct> createDigitalProduct(@Valid @RequestBody CreateDigitalProductDTO body) {
        return ResponseEntity.created(URI.create("/digital-products/" + body.getSku()))
                .body(digitalProductService.createDigitalProduct(body));
    }

    @PutMapping("/digital-products/{SKU}")
    public ResponseEntity<DigitalProduct> updateDigitalProduct(@PathVariable String sku,
            @Valid @RequestBody UpdateDigitalProductDTO body) {
        return ResponseEntity.ok(digitalProductService.updateDigitalProduct(sku, body));
    }

    @DeleteMapping("/digital-products/{SKU}")
    public ResponseEntity<DigitalProduct> deleteDigitalProduct(@PathVariable String SKU) {
        digitalProductService.deleteDigitalProduct(SKU);
        return ResponseEntity.noContent().build();
    }
}
