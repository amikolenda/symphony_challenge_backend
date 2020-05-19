package is.symphony.collegeinternship.olympicgames.exceptions;

public class BadExtensionException extends RuntimeException {

    public BadExtensionException(){
        super("Invalid file extension. Please select a valid file to upload");
    }

}

