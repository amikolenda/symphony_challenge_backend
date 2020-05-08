package is.symphony.collegeinternship.olympicgames.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    @ResponseBody
    public String getLogin() {
        System.out.println("GET successful");
        return "Login successful";
    }
}