package is.symphony.collegeinternship.olympicgames.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    @ResponseBody
    public ResponseEntity<String> getLogin()  {
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

}