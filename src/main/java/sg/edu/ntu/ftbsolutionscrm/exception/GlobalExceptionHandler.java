package sg.edu.ntu.ftbsolutionscrm.exception;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({FavouriteNotFoundException.class,SalesPersonNotFoundException.class,HDBSalesInteractionNotFoundException.class,HDBSalesInteractionNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourseNotFoundException(RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse (e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
// Validation exception handler
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
    // Get a list of validation errors
    List<ObjectError> validationErrors = e.getBindingResult().getAllErrors();

    // Create a StringBuilder to store all messages
    StringBuilder sb = new StringBuilder();

    // Loop through all the errors and append the messages
    for (ObjectError error : validationErrors) {
      sb.append(error.getDefaultMessage() + ". ");
    }

    ErrorResponse errorResponse = new ErrorResponse(sb.toString(), LocalDateTime.now());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  // General Exception Handler
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    // ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),
    // LocalDateTime.now());
    // logger.error....
    ErrorResponse errorResponse = new ErrorResponse("Something went wrong. Please contact support.",
        LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }












}
