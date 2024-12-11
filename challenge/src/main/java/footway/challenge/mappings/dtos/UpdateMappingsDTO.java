package footway.challenge.mappings.dtos;

import jakarta.validation.constraints.NotBlank;

public class UpdateMappingsDTO {

    @NotBlank
    private final String physicalProductSku;

    @NotBlank
    private final String digitalProductSku;

    public UpdateMappingsDTO(String physicalProductSku, String digitalProductSku) {
        this.physicalProductSku = physicalProductSku;
        this.digitalProductSku = digitalProductSku;
    }

    public String getPhysicalProductSku() {
        return physicalProductSku;
    }

    public String getDigitalProductSku() {
        return digitalProductSku;
    }

}
