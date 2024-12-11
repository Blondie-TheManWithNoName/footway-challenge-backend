package footway.challenge.orders.dtos;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CreateOrderDTO {

    @NotEmpty(message = "The digitalProductsSkus list cannot be empty.")
    @Size(min = 1, message = "The digitalProductsSkus list must contain at least one SKU.")
    private final List<String> digitalProductsSkus;

    public CreateOrderDTO(List<String> digitalProductsSkus) {
        this.digitalProductsSkus = digitalProductsSkus;
    }

    public List<String> getDigitalProductsSkus() {
        return digitalProductsSkus;
    }

}
