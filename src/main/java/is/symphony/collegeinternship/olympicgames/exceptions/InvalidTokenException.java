package is.symphony.collegeinternship.olympicgames.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(){
        super("JWT token is expired or invalid");
    }
}
