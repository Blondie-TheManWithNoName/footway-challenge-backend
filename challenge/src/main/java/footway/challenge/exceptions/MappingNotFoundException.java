package footway.challenge.exceptions;

public class MappingNotFoundException extends RuntimeException {
    public MappingNotFoundException() {
        super("Mapping not found");
    }
}