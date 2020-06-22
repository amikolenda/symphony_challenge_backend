package is.symphony.collegeinternship.olympicgames.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadExtensionException.class, InvalidFieldNameException.class, InvalidInputParametersException.class, NoFileException.class,NoSuchElementException.class, ResourceNotFoundException.class, ElementExistsException.class})
    public final ResponseEntity<String> appExtensionException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}