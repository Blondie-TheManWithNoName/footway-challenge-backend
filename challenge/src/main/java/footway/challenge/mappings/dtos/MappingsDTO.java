package footway.challenge.mappings.dtos;

public class MappingsDTO {

    private int id;
    private String digitalProductSku;
    private String physicalProductSku;

    public MappingsDTO(int id, String digitalProductSku, String physicalProductSku) {
        this.id = id;
        this.digitalProductSku = digitalProductSku;
        this.physicalProductSku = physicalProductSku;
    }

    public int getId() {
        return id;
    }

    public String getDigitalProductSku() {
        return digitalProductSku;
    }

    public String getPhysicalProductSku() {
        return physicalProductSku;
    }

}
