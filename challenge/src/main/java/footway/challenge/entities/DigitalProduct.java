
package footway.challenge.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "digital_product")
public class DigitalProduct extends Product {

    @ManyToMany(mappedBy = "digitalProducts")
    private Set<PhysicalProduct> physicalProducts;

    public DigitalProduct() {
    }

    public DigitalProduct(String EAN, String SKU, String name, String description, double price, String color,
            int size) {
        super(EAN, SKU, name, description, price, color, size);
    }

}
