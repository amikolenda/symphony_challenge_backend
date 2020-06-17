package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.models.Sport;
import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.VolunteerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.dto.AthleteDTO;

@Component
public class DTOConverterService {

    @Autowired
    private ModelMapper modelMapper;

    public AthleteDTO convertToDTO(Athlete athlete){
        return modelMapper.map(athlete, AthleteDTO.class);
    }

    public SportDTO convertSportDTO(Sport sport){
        return modelMapper.map(sport, SportDTO.class);
    }

    public VolunteerDTO convertVolunteerDTO(Volunteer volunteer){
        return modelMapper.map(volunteer, VolunteerDTO.class);
    }

}
