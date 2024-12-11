package footway.challenge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "physical_product", indexes = {
        // @Index(name = "physical_product_sku", columnList = "sku", unique = true),
        @Index(name = "physical_product_ean", columnList = "EAN", unique = true),
// @Index(name = "physical_product_name_description_fulltext_idx", columnList =
// "name, description", unique = false)
// CREATE FULLTEXT INDEX name_description_fulltext_idx ON physical_product(name,
// description);
})
public class PhysicalProduct {

    // * SKU Number */
    @Id
    private String sku;

    // * EAN Number */
    @Column(nullable = true)
    private String EAN;

    // * Name */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String name;

    // * Description */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    // * Price */
    @Column(nullable = false)
    private double price;

    // * Variant Color */
    @Column(nullable = false)
    private String color;

    // * Variant Size */
    @Column(nullable = false)
    private int size;

    @OneToMany(mappedBy = "physicalProduct")
    @JsonBackReference
    private List<Mappings> mappings;

    // * Constructor */
    public PhysicalProduct(String EAN, String SKU, String name, String description,
            double price, String color, int size) {
        this.EAN = EAN;
        this.sku = SKU;
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.size = size;
    }

    public PhysicalProduct() {
    }

    // * GETTERS & SETTERS */

    public String getSku() {
        return sku;
    }

    public void setSku(String SKU) {
        this.sku = SKU;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Mappings> getMappings() {
        return mappings;
    }
}
