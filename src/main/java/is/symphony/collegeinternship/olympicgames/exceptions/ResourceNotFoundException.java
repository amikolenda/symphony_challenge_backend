package is.symphony.collegeinternship.olympicgames.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found");
    }
}