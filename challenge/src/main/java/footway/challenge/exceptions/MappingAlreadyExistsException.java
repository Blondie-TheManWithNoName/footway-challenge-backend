package footway.challenge.exceptions;

import org.springframework.dao.DuplicateKeyException;

public class MappingAlreadyExistsException extends DuplicateKeyException {
    public MappingAlreadyExistsException() {
        super("Mapping with given SKUs already exists");
    }

    public MappingAlreadyExistsException(int id) {
        super("Mapping with ID: " + id + " already exists");
    }
}