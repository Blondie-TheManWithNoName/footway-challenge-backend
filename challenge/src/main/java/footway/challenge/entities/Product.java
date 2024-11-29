package footway.challenge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {

    // * SKU Number */
    @Id
    private String SKU;

    // * EAN Number */
    @Column(nullable = true)
    private String EAN;

    // * Name */
    @Column(nullable = false)
    private String name;

    // * Description */
    @Column(nullable = false)
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

    // * Constructor */
    public Product(String EAN, String SKU, String name, String description, double price, String color, int size) {
        this.EAN = EAN;
        this.SKU = SKU;
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.size = size;
    }

    public Product() {
    }

    // * GETTERS & SETTERS */

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
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

}
