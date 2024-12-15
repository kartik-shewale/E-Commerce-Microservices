package kartik.app.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());  // This will return the exception message
        response.put("statusText", "Error");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  // 400 Bad Request
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "An unexpected error occurred.");
        response.put("statusText", "Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // 500 Internal Server Error
    }
    
    // Handle custom exceptions for duplicate entries (email, username, etc.)
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEntryException(DuplicateEntryException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());  // Custom message from the exception
        response.put("statusText", "Error");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  // 400 Bad Request
    }

    // Handle ResourceNotFoundException for missing resources like a customer or product
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());  // Custom message from the exception
        response.put("statusText", "Error");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);  // 404 Not Found
    }
}

