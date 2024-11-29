package footway.challenge.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "physical_product")
public class PhysicalProduct extends Product {

    @ManyToMany
    @JoinTable(name = "physical_digital_map", joinColumns = @JoinColumn(name = "physical_product_id"), inverseJoinColumns = @JoinColumn(name = "digital_product_id"))
    private Set<DigitalProduct> digitalProducts;

    public PhysicalProduct() {
    }

    public PhysicalProduct(String EAN, String SKU, String name, String description, double price, String color,
            int size) {
        super(EAN, SKU, name, description, price, color, size);
    }

}
