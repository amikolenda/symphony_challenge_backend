package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.dto.AthleteDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.AthleteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AthleteControllerTest {
    @MockBean
    public AthleteService athleteService;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void showAthletes() {
        Athlete athlete = new Athlete().setFirstName("Ian")
                .setLastName("Miller").setBadgeNumber("1111")
                .setDateOfBirth("04-10-1877");
        athleteService.save(athlete);
        Athlete athlete1 = new Athlete().setFirstName("Mary")
                .setLastName("Miller").setBadgeNumber("2222")
                .setDateOfBirth("02-11-1887");
        athleteService.save(athlete1);
        when(athleteService.findByBadgeNumber(athlete.getBadgeNumber())).thenReturn(athlete);

        webTestClient.get()
                .uri("/athletes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBodyList(AthleteDTO.class);
    }

    @Test
    void showAthlete() {
        Athlete athlete = new Athlete().setFirstName("Ian")
                .setLastName("Miller").setBadgeNumber("1111")
                .setDateOfBirth("04-10-1877");
        athleteService.save(athlete);
        webTestClient.get()
                .uri("/athletes/1111")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(AthleteDTO.class)
                .value(athlete1 -> athlete.getBadgeNumber(), equalTo("1111"));
    }

}