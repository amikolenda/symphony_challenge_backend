package is.symphony.collegeinternship.olympicgames.services.impl;

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

}
