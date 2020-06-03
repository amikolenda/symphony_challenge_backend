package is.symphony.collegeinternship.olympicgames.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import is.symphony.collegeinternship.olympicgames.exceptions.BadExtensionException;
import is.symphony.collegeinternship.olympicgames.exceptions.InvalidFieldNameException;
import is.symphony.collegeinternship.olympicgames.exceptions.NoFileException;
import is.symphony.collegeinternship.olympicgames.services.impl.FileUploadServiceImpl;


@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private FileUploadServiceImpl fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws NoFileException, BadExtensionException, InvalidFieldNameException {
        return fileUploadService.uploadFile(file);
    }


}
