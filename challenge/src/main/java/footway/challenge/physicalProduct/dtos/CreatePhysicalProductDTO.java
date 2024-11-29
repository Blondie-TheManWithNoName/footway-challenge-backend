package footway.challenge.physicalProduct.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class CreatePhysicalProductDTO {

    @NotBlank
    private final String SKU;

    @NotBlank(message = "EAN is required")
    @Pattern(regexp = "\\d{13}", message = "EAN must be exactly 13 digits")
    private final String ean;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @Positive
    @NotNull
    private final double price;

    @NotNull
    private final String color;

    @Min(1)
    @NotNull()
    private final int size;

    public CreatePhysicalProductDTO(String SKU, String ean, String name, String description, double price, String color,
            int size) {
        this.SKU = SKU;
        this.ean = ean;
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.size = size;
    }

    public String getSku() {
        return SKU;
    }

    public String getEan() {
        return ean;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

}
