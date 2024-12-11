
// UNUSED PARENT CLASS

// package footway.challenge.entities;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.Inheritance;
// import jakarta.persistence.InheritanceType;
// import java.util.Objects;

// @Entity
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// public abstract class Product {

// // * SKU Number */
// @Id
// private String sku;

// // * EAN Number */
// @Column(nullable = true)
// private String EAN;

// // * Name */
// @Column(nullable = false)
// private String name;

// // * Description */
// @Column(nullable = false)
// private String description;

// // * Price */
// @Column(nullable = false)
// private double price;

// // * Variant Color */
// @Column(nullable = false)
// private String color;

// // * Variant Size */
// @Column(nullable = false)
// private int size;

// // * Constructor */
// public Product(String EAN, String SKU, String name, String description,
// double price, String color, int size) {
// this.EAN = EAN;
// this.sku = SKU;
// this.name = name;
// this.description = description;
// this.price = price;
// this.color = color;
// this.size = size;
// }

// public Product() {
// }

// // * GETTERS & SETTERS */

// public String getSku() {
// return sku;
// }

// public void setSku(String SKU) {
// this.sku = SKU;
// }

// public String getEAN() {
// return EAN;
// }

// public void setEAN(String EAN) {
// this.EAN = EAN;
// }

// public String getName() {
// return name;
// }

// public void setName(String name) {
// this.name = name;
// }

// public String getDescription() {
// return description;
// }

// public void setDescription(String description) {
// this.description = description;
// }

// public double getPrice() {
// return price;
// }

// public void setPrice(double price) {
// this.price = price;
// }

// public String getColor() {
// return color;
// }

// public void setColor(String color) {
// this.color = color;
// }

// public int getSize() {
// return size;
// }

// public void setSize(int size) {
// this.size = size;
// }

// @Override
// public boolean equals(Object o) {
// if (this == o)
// return true;
// if (o == null || getClass() != o.getClass())
// return false;
// Product product = (Product) o;
// return Objects.equals(sku, product.sku);
// }

// @Override
// public int hashCode() {
// return Objects.hash(sku);
// }

// }
