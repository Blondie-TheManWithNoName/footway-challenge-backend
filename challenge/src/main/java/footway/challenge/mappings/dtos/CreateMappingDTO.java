package footway.challenge.mappings.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateMappingDTO {

    @NotBlank
    private final String physicalProductSku;

    @NotBlank
    private final String digitalProductSku;

    @Min(1)
    @NotNull()
    private final int order;

    public CreateMappingDTO(String physicalProductSku, String digitalProductSku, int order) {
        this.physicalProductSku = physicalProductSku;
        this.digitalProductSku = digitalProductSku;
        this.order = order;
    }

    public String getPhysicalProductSku() {
        return physicalProductSku;
    }

    public String getDigitalProductSku() {
        return digitalProductSku;
    }

    public int getOrder() {
        return order;
    }

}
