package is.symphony.collegeinternship.olympicgames.exceptions;

public class NoFileException extends RuntimeException {

    public NoFileException(){
        super("File is empty. Please select a file to upload.");
    }

}
