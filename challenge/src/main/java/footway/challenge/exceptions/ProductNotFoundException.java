package footway.challenge.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product not found");
    }

    public ProductNotFoundException(String sku) {
        super("Product with SKU '" + sku + "' not found");
    }
}