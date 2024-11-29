package footway.challenge.exceptions;

import org.springframework.dao.DuplicateKeyException;

public class ProductAlreadyExistsException extends DuplicateKeyException {
    public ProductAlreadyExistsException() {
        super("Product with given SKU already exists");
    }

    public ProductAlreadyExistsException(String SKU) {
        super("Product with SKU: " + SKU + " already exists");
    }
}