package footway.challenge.physicalProduct.dtos;

public class PhysicalProductDTO {

    private String SKU;
    private String EAN;
    private String name;
    private String image;
    private String href;

    public PhysicalProductDTO(String SKU, String EAN, String name) {
        this.SKU = SKU;
        this.EAN = EAN;
        this.image = "/shoes" + "-" + SKU.charAt(SKU.length() - 1) + ".png";
        this.name = name;
        this.href = "/physical-products/" + SKU;
    }

    public String getSKU() {
        return SKU;
    }

    public String getEAN() {
        return EAN;
    }

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

    public String getImage() {
        return image;
    }
}
