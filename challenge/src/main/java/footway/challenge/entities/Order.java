package footway.challenge.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_digital_products", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "digital_product_sku", referencedColumnName = "sku"))
    private List<DigitalProduct> digitalProducts;

    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(List<DigitalProduct> digitalProducts) {
        this.digitalProducts = digitalProducts;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public List<DigitalProduct> getDigitalProducts() {
        return digitalProducts;
    }

    public void setDigitalProducts(List<DigitalProduct> digitalProducts) {
        this.digitalProducts = digitalProducts;
    }
}
