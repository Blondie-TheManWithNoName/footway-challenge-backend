
package footway.challenge.mappings;

import org.springframework.web.bind.annotation.RestController;

import footway.challenge.entities.Mappings;
import footway.challenge.mappings.dtos.CreateMappingDTO;
import footway.challenge.mappings.dtos.MappingsDTO;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
public class MappingsController {

    private final MappingsService mappingService;

    public MappingsController(MappingsService mappingsService) {
        this.mappingService = mappingsService;
    }

    @GetMapping("/mappings")
    public ResponseEntity<List<MappingsDTO>> getMappings(
            @RequestParam(value = "physicalSku", required = false) String physicalSku,
            @RequestParam(value = "digitalSku", required = false) String digitalSku,
            @RequestParam(value = "orderId", required = true) int orderId) {
        List<MappingsDTO> mappings = mappingService.getAllMappings(physicalSku, digitalSku, orderId);
        return ResponseEntity.ok(mappings);
    }

    @GetMapping("/mappings/{id}")
    public ResponseEntity<Mappings> getMapping(@PathVariable int id) throws NotFoundException {
        return ResponseEntity.ok(mappingService.getMapping(id));
    }

    @PostMapping("/mappings")
    public ResponseEntity<Mappings> createMapping(@Valid @RequestBody CreateMappingDTO body) {
        return ResponseEntity.created(URI.create("/mappings/"))
                .body(mappingService.createMapping(body));
    }

    // @PutMapping("/mappings/{id}")
    // public ResponseEntity<Mappings> updateMapping(@PathVariable String SKU,
    // @Valid @RequestBody UpdatePhysicalProductDTO body) {
    // return ResponseEntity.ok(mappingService.updateMapping(SKU, body));
    // }

    @DeleteMapping("/mappings/{id}")
    public ResponseEntity<Mappings> deleteMapping(@PathVariable int id) {
        mappingService.deleteMapping(id);
        return ResponseEntity.noContent().build();
    }
}
