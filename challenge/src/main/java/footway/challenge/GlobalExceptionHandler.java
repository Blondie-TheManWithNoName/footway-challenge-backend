package footway.challenge;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import footway.challenge.exceptions.MappingAlreadyExistsException;
import footway.challenge.exceptions.ProductAlreadyExistsException;
import footway.challenge.exceptions.UnableToDeleteException;
import footway.challenge.exceptions.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "footway.challenge")
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)

    // Data Validation Error Handler
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put("message", fieldName + ": " + errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Product and Mapping already Exists Handler
    @ExceptionHandler({ ProductAlreadyExistsException.class, MappingAlreadyExistsException.class,
            UnableToDeleteException.class })
    public ResponseEntity<ErrorResponse> handleConflictExceptions(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Product Not Found Handler
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Internal Server Error Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred. Please try again later.");
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
