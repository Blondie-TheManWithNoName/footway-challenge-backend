package footway.challenge.physicalProduct;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import footway.challenge.entities.PhysicalProduct;
import footway.challenge.exceptions.ProductAlreadyExistsException;
import footway.challenge.exceptions.ProductNotFoundException;
import footway.challenge.physicalProduct.dtos.CreatePhysicalProductDTO;
import footway.challenge.physicalProduct.dtos.GetAllPhysicalProductsDTO;
import footway.challenge.physicalProduct.dtos.PhysicalProductDTO;
import footway.challenge.physicalProduct.dtos.UpdatePhysicalProductDTO;
import footway.challenge.repos.PhysicalProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Service
public class PhysicalProductService {

    private final PhysicalProductRepo physicalProductRepo;

    public PhysicalProductService(PhysicalProductRepo physicalProductRepo) {
        this.physicalProductRepo = physicalProductRepo;
    }

    public Page<PhysicalProductDTO> getAllPhysicalProducts(GetAllPhysicalProductsDTO query) {

        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getTake(), Sort.by("sku"));
        Page<PhysicalProduct> physicalProducts;

        if (query.getEan().isEmpty()) {

            if (!query.getSearch().isEmpty())
                physicalProducts = physicalProductRepo.findByNameAndDescription(query.getSearch() + "*", pageable);
            else
                physicalProducts = physicalProductRepo.findAll(pageable);

            return physicalProducts.map(product -> new PhysicalProductDTO(
                    product.getSku(),
                    product.getEAN(),
                    product.getName()));
        } else {
            PhysicalProduct product = physicalProductRepo.findByEan(query.getEan());
            if (product != null) {
                List<PhysicalProductDTO> dtoList = List.of(new PhysicalProductDTO(
                        product.getSku(),
                        product.getEAN(),
                        product.getName()));
                return new PageImpl<>(dtoList, pageable, 1);
            } else {
                return new PageImpl<>(Collections.emptyList(), pageable, 0);
            }
        }
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
        physicalProduct.setEAN(body.getEan());
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
