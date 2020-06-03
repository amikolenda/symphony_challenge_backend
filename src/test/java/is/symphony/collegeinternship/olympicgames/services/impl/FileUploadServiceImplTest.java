package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.models.Athlete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileUploadServiceImplTest {

    @Autowired
    public FileUploadServiceImpl fileUploadService;
    @Autowired
    AthleteService athleteService;


    @Test
    void uploadFile() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/athletes.txt");
        MockMultipartFile multipartFile = new MockMultipartFile("file","athletes.txt", "application/json", fis);
        fileUploadService.uploadFile(multipartFile);
        Athlete athlete = athleteService.findById("11111-A");
        assertThat(multipartFile)
                .hasNoNullFieldsOrProperties();
        assertThat(athlete.getBadgeNumber())
                .isEqualTo("11111-A");
        assertThat(fileUploadService.uploadFile(multipartFile))
                .isEqualTo(new ResponseEntity<>("Athletes uploaded/updated.", HttpStatus.OK));

    }

}