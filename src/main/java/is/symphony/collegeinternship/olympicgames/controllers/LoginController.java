package is.symphony.collegeinternship.olympicgames.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import is.symphony.collegeinternship.olympicgames.repositories.AthleteRepository;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AthleteRepository athleteRepository;

    @GetMapping
    @ResponseBody
    public String getLogin() {
        System.out.println("GET successful");
        return "Login successful";
    }

}