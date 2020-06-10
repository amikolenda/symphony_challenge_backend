package is.symphony.collegeinternship.olympicgames.exceptions;

public class ElementExistsException extends RuntimeException{
    public ElementExistsException(){
        super("Can not create element. Element already exists.");
    }
}
