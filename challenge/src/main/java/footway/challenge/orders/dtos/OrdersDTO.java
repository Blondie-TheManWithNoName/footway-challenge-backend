package footway.challenge.orders.dtos;

import java.util.List;

import footway.challenge.entities.DigitalProduct;

public class OrdersDTO {

    private int id;
    private int digitalProductsCount;
    private List<String> digitalProductsLinks;
    private String href;

    public OrdersDTO(int id, List<DigitalProduct> digitalProducts) {
        this.id = id;
        this.digitalProductsCount = digitalProducts.size();
        this.digitalProductsLinks = digitalProducts.stream()
                .map(digitalProduct -> "/digital-products/" + digitalProduct.getSku())
                .toList();
        this.href = "/orders/" + id;

    }

    public int getId() {
        return id;
    }

    public List<String> getDigitalProductsLinks() {
        return digitalProductsLinks;
    }

    public int getDigitalProductsCount() {
        return digitalProductsCount;
    }

    public String getHref() {
        return href;
    }

}
