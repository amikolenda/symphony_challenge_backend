package is.symphony.collegeinternship.olympicgames.exceptions;


public class NoSuchElementException extends RuntimeException{

    public NoSuchElementException(){
       super("Element not found");
    }
}
