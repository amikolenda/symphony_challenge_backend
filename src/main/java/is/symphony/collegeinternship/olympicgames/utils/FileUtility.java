package is.symphony.collegeinternship.olympicgames.utils;

import org.springframework.web.multipart.MultipartFile;
import is.symphony.collegeinternship.olympicgames.exceptions.BadExtensionException;
import is.symphony.collegeinternship.olympicgames.exceptions.NoFileException;

public class FileUtility {

    private FileUtility() {
    }

    public static void validateFileIsEmpty(MultipartFile multipartFile) throws NoFileException {
        if (multipartFile.isEmpty())throw new NoFileException();
    }
    public static void validateFileExtension(MultipartFile multipartFile) throws BadExtensionException {
        String extension = getFileExtension(multipartFile);
        if (!extension.equals("txt") && !extension.equals("json") && !extension.equals("csv"))
            throw new BadExtensionException();
    }
    public static String getFileExtension(MultipartFile file) {
        String orgName = file.getOriginalFilename();
        assert orgName != null;
        String[] parts = orgName.split("\\.");
        return parts[parts.length-1];
    }


}
