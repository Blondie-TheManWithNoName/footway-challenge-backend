package footway.challenge.orders.dtos;

import java.util.List;

import footway.challenge.digitalProduct.dtos.DigitalProductDTO;
import footway.challenge.entities.DigitalProduct;

public class OrderDTO {

    private int id;
    private int digitalProductsCount;
    private List<DigitalProductDTO> digitalProductsLinks;

    public OrderDTO(int id, List<DigitalProduct> digitalProducts) {
        this.id = id;
        this.digitalProductsCount = digitalProducts.size();
        this.digitalProductsLinks = digitalProducts.stream()
                .map(digitalProduct -> new DigitalProductDTO(digitalProduct.getSku(), digitalProduct.getEAN(),
                        digitalProduct.getName()))
                .toList();

    }

    public int getId() {
        return id;
    }

    public List<DigitalProductDTO> getDigitalProductsLinks() {
        return digitalProductsLinks;
    }

    public int getDigitalProductsCount() {
        return digitalProductsCount;
    }

}
