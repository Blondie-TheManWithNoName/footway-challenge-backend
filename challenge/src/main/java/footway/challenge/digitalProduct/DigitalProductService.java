package footway.challenge.digitalProduct;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import footway.challenge.entities.DigitalProduct;
import footway.challenge.entities.PhysicalProduct;
import footway.challenge.exceptions.ProductAlreadyExistsException;
import footway.challenge.exceptions.ProductNotFoundException;
import footway.challenge.exceptions.UnableToDeleteException;
import footway.challenge.physicalProduct.dtos.PhysicalProductDTO;
import footway.challenge.digitalProduct.dtos.CreateDigitalProductDTO;
import footway.challenge.digitalProduct.dtos.DigitalProductDTO;
import footway.challenge.digitalProduct.dtos.UpdateDigitalProductDTO;
import footway.challenge.repos.DigitalProductRepo;

@Service
public class DigitalProductService {

    private final DigitalProductRepo digitalProductRepo;

    public DigitalProductService(DigitalProductRepo digitalProductRepo) {
        this.digitalProductRepo = digitalProductRepo;
    }

    public Page<DigitalProductDTO> getAllDigitalProducts(int page, int take, String search) {

        Pageable pageable = PageRequest.of(page - 1, take, Sort.by("sku"));
        Page<DigitalProduct> digitalProducts;

        if (!search.isEmpty())
            digitalProducts = digitalProductRepo.findByNameAndDescription(search + "*", pageable);
        else
            digitalProducts = digitalProductRepo.findAll(pageable);

        return digitalProducts.map(product -> new DigitalProductDTO(
                product.getSku(),
                product.getEAN(),
                product.getName()));

    }

    public DigitalProduct getDigitalProduct(String SKU) {
        return digitalProductRepo.findById(SKU)
                .orElseThrow(() -> new ProductNotFoundException());
    }

    public DigitalProduct createDigitalProduct(CreateDigitalProductDTO body) {

        final Optional<DigitalProduct> dupProduct = digitalProductRepo.findBySkuOrEan(body.getSku(), body.getEan());

        if (!dupProduct.isEmpty())
            throw new ProductAlreadyExistsException(body.getSku());

        final DigitalProduct digitalProduct = new DigitalProduct(body.getEan(), body.getSku(), body.getName(),
                body.getDescription(), body.getPrice(), body.getColor(), body.getSize());

        return digitalProductRepo.save(digitalProduct);
    }

    public DigitalProduct updateDigitalProduct(String SKU, UpdateDigitalProductDTO body) {

        final DigitalProduct digitalProduct = digitalProductRepo.findById(SKU)
                .orElseThrow(() -> new ProductNotFoundException());

        digitalProduct.setName(body.getName());
        digitalProduct.setEAN(body.getEan());
        digitalProduct.setDescription(body.getDescription());
        digitalProduct.setPrice(body.getPrice());
        digitalProduct.setColor(body.getColor());
        digitalProduct.setSize(body.getSize());

        return digitalProductRepo.save(digitalProduct);
    }

    public void deleteDigitalProduct(String SKU) {

        digitalProductRepo.findById(SKU)
                .orElseThrow(() -> new ProductNotFoundException());

        try {
            digitalProductRepo.deleteById(SKU);
        } catch (DataIntegrityViolationException e) {
            throw new UnableToDeleteException("Digital product is being used by an order",
                    e);
        }
    }

}
