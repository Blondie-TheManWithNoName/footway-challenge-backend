package footway.challenge.physicalProduct.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class GetAllPhysicalProductsDTO {

    @Positive
    private int page = 1;

    @Positive
    @Max(50)
    private int take = 10;

    private String search = "";

    @Pattern(regexp = "\\d{13}", message = "EAN must be exactly 13 digits")
    private String ean = "";

    public GetAllPhysicalProductsDTO(int page, int take, String search, String ean) {
        this.page = page;
        this.take = take;
        this.search = search;
        this.ean = ean;
    }

    public int getPage() {
        return page;
    }

    public int getTake() {
        return take;
    }

    public String getSearch() {
        return search;
    }

    public String getEan() {
        return ean;
    }

}
