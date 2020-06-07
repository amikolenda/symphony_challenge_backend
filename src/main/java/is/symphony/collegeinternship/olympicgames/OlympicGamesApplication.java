package is.symphony.collegeinternship.olympicgames;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import is.symphony.collegeinternship.olympicgames.models.Admin;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.repositories.AdminRepository;
import is.symphony.collegeinternship.olympicgames.repositories.AthleteRepository;
import is.symphony.collegeinternship.olympicgames.repositories.CountryRepository;
import is.symphony.collegeinternship.olympicgames.services.impl.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {AthleteRepository.class, CountryRepository.class})
public class OlympicGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlympicGamesApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	ApplicationRunner applicationRunner(CountryService countryService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			try {
				InputStream inputStream = TypeReference.class.getResourceAsStream("/json/countries.json");
				List<Country> countries = mapper.readValue(inputStream,new TypeReference<List<Country>>(){});
				countryService.saveAll(countries);
			} catch (IOException e){
				e.printStackTrace();
			}
		};
	}

	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {
		@Autowired
		AdminRepository adminRepository;

		@Override
		public void run(String...args) throws Exception {
			Admin admin = new Admin("admin","admin");
			adminRepository.save(admin);
		}
	}

}
