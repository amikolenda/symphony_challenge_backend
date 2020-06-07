package is.symphony.collegeinternship.olympicgames.security.services;

import is.symphony.collegeinternship.olympicgames.models.Admin;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import is.symphony.collegeinternship.olympicgames.repositories.AdminRepository;
import is.symphony.collegeinternship.olympicgames.repositories.AthleteRepository;
import is.symphony.collegeinternship.olympicgames.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AthleteRepository athleteRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Athlete> athlete = athleteRepository.findById(userName);
        Optional<Admin> admin = adminRepository.findById(userName);
        Optional<Volunteer> volunteer = volunteerRepository.findById(userName);
        if (athlete.isPresent()) return AthleteDetailsImpl.build(athlete.get());
        else if (admin.isPresent()) return AdminDetailsImpl.build(admin.get());
        else if (volunteer.isPresent()) return VolunteerDetailsImpl.build(volunteer.get());

        throw new UsernameNotFoundException("User Not Found: " + userName);
    }
}
