package footway.challenge.physicalProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import footway.challenge.entities.PhysicalProduct;
import footway.challenge.exceptions.ProductAlreadyExistsException;
import footway.challenge.exceptions.ProductNotFoundException;
import footway.challenge.physicalProduct.dtos.CreatePhysicalProductDTO;
import footway.challenge.physicalProduct.dtos.UpdatePhysicalProductDTO;
import footway.challenge.repos.PhysicalProductRepo;

@Service
public class PhysicalProductService {

    private final PhysicalProductRepo physicalProductRepo;

    public PhysicalProductService(PhysicalProductRepo physicalProductRepo) {
        this.physicalProductRepo = physicalProductRepo;
    }

    public List<PhysicalProduct> getAllPhysicalProducts() {

        List<PhysicalProduct> physicalProducts = new ArrayList<>();

        physicalProductRepo.findAll().forEach(physicalProducts::add);

        return physicalProducts;

    }

    public PhysicalProduct getPhysicalProduct(String SKU) {
        return physicalProductRepo.findById(SKU)
                .orElseThrow(() -> new ProductNotFoundException());
    }

    public PhysicalProduct createPhysicalProduct(CreatePhysicalProductDTO body) {

        final Optional<PhysicalProduct> dupProduct = physicalProductRepo.findById(body.getSku());

        if (!dupProduct.isEmpty())
            throw new ProductAlreadyExistsException(body.getSku());

        final PhysicalProduct physicalProduct = new PhysicalProduct(body.getEan(), body.getSku(), body.getName(),
                body.getDescription(), body.getPrice(), body.getColor(), body.getSize());

        return physicalProductRepo.save(physicalProduct);
    }

    public PhysicalProduct updatePhysicalProduct(String SKU, UpdatePhysicalProductDTO body) {

        final PhysicalProduct physicalProduct = physicalProductRepo.findById(SKU)
                .orElseThrow(() -> new ProductNotFoundException());

        physicalProduct.setName(body.getName());
        physicalProduct.setDescription(body.getDescription());
        physicalProduct.setPrice(body.getPrice());
        physicalProduct.setColor(body.getColor());
        physicalProduct.setSize(body.getSize());

        return physicalProductRepo.save(physicalProduct);
    }

    public void deletePhysicalProduct(String SKU) {
        physicalProductRepo.findById(SKU)
                .orElseThrow(() -> new ProductNotFoundException());

        physicalProductRepo.deleteById(SKU);

    }

}
