package footway.challenge.entities;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "mappings")
public class Mappings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "physical_product_sku", referencedColumnName = "sku", nullable = false)
    private PhysicalProduct physicalProduct;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "digital_product_sku", referencedColumnName = "sku", nullable = false)
    private DigitalProduct digitalProduct;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    // Default constructor
    public Mappings() {
    }

    // Parameterized constructor
    public Mappings(PhysicalProduct physicalProduct, DigitalProduct digitalProduct, Order order) {
        this.physicalProduct = physicalProduct;
        this.digitalProduct = digitalProduct;
        this.order = order;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public PhysicalProduct getPhysicalProduct() {
        return physicalProduct;
    }

    public void setPhysicalProduct(PhysicalProduct physicalProduct) {
        this.physicalProduct = physicalProduct;
    }

    public DigitalProduct getDigitalProduct() {
        return digitalProduct;
    }

    public void setDigitalProduct(DigitalProduct digitalProduct) {
        this.digitalProduct = digitalProduct;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
