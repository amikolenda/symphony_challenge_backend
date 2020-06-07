package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.repositories.AthleteRepository;
import is.symphony.collegeinternship.olympicgames.security.payload.JwtAuthenticationResponse;
import is.symphony.collegeinternship.olympicgames.security.payload.LoginRequest;
import is.symphony.collegeinternship.olympicgames.security.services.AdminDetailsImpl;
import is.symphony.collegeinternship.olympicgames.security.services.AthleteDetailsImpl;
import is.symphony.collegeinternship.olympicgames.security.services.VolunteerDetailsImpl;
import is.symphony.collegeinternship.olympicgames.utils.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService {
    @Autowired
    AthleteRepository athleteRepository;

    @Autowired
    JwtUtility jwtUtility;


    @Autowired
    AuthenticationManager authenticationManager;

    public ResponseEntity<?> authenticateUser(@Valid LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtility.generateToken(authentication);
		if (authentication.getPrincipal() instanceof AthleteDetailsImpl){
			AthleteDetailsImpl athleteDetails = (AthleteDetailsImpl) authentication.getPrincipal();
		List<String> roles = athleteDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, athleteDetails.getUsername(), roles, athleteDetails.getFirstName(),athleteDetails.getLastName(),athleteDetails.getNationality(),athleteDetails.getCountry(),athleteDetails.getPhoto(),athleteDetails.getGender()));
		} else if(authentication.getPrincipal() instanceof AdminDetailsImpl){
			AdminDetailsImpl adminDetails = (AdminDetailsImpl) authentication.getPrincipal();
			List<String> roles = adminDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, adminDetails.getUsername(), roles));
		} else {
			VolunteerDetailsImpl volunteerDetails = (VolunteerDetailsImpl) authentication.getPrincipal();
			List<String> roles = volunteerDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, volunteerDetails.getUsername(), roles, volunteerDetails.getFirstName(),volunteerDetails.getLastName(),volunteerDetails.getNationality(),volunteerDetails.getCountry(),volunteerDetails.getPhoto(),volunteerDetails.getGender(),volunteerDetails.getDateOfBirth()));


		}
	}

}
