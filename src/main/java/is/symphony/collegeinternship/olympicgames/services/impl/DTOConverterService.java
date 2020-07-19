package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.models.Competition;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.models.Jump;
import is.symphony.collegeinternship.olympicgames.models.Sport;
import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import is.symphony.collegeinternship.olympicgames.models.dto.CompetitionDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.CountryDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.JumpDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.VolunteerDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.dto.AthleteDTO;

@Component
public class DTOConverterService {
    private static Logger LOGGER = LoggerFactory.getLogger(DTOConverterService.class);

    @Autowired
    private ModelMapper modelMapper;

    public AthleteDTO convertToDTO(Athlete athlete){
        LOGGER.info("Converting Athlete DAO to DTO...");
        AthleteDTO athleteDTO = modelMapper.map(athlete, AthleteDTO.class);
        LOGGER.info("Converted Athlete DAO to DTO.");
        return athleteDTO;
    }

    public SportDTO convertSportDTO(Sport sport){
        LOGGER.info("Converting Sport DAO to DTO...");
        SportDTO sportDTO =  modelMapper.map(sport, SportDTO.class);
        LOGGER.info("Converted Sport DAO to DTO.");
        return sportDTO;
    }

    public VolunteerDTO convertVolunteerDTO(Volunteer volunteer){
        LOGGER.info("Converting Volunteer DAO to DTO...");
        VolunteerDTO volunteerDTO = modelMapper.map(volunteer, VolunteerDTO.class);
        LOGGER.info("Converted Volunteer DAO to DTO.");
        return volunteerDTO;
    }

    public Sport convertSportDTOToDAO(SportDTO sportDTO){
        LOGGER.info("Converting Sport DTO to DAO...");
        Sport sport = modelMapper.map(sportDTO, Sport.class);
        LOGGER.info("Converted Sport DTO to DAO.");
        return sport;
    }

    public Athlete convertAthleteDTOToDAO(AthleteDTO athleteDTO){
        LOGGER.info("Converting Athlete DTO to DAO...");
        Athlete athlete = modelMapper.map(athleteDTO, Athlete.class);
        LOGGER.info("Converted Athlete DTO to DAO.");
        return athlete;
    }

    public Volunteer convertVolunteerDTOToDAO(VolunteerDTO volunteerDTO){
        LOGGER.info("Converting Volunteer DTO to DAO...");
        Volunteer volunteer = modelMapper.map(volunteerDTO, Volunteer.class);
        LOGGER.info("Converted Volunteer DTO to DAO.");
        return volunteer;
    }

    public CountryDTO convertCountryToDTO(Country country) {
        LOGGER.info("Converting Country DAO to DTO...");
        CountryDTO countryDTO = modelMapper.map(country, CountryDTO.class);
        LOGGER.info("Converted Country DAO to DTO.");
        return countryDTO;
    }

    public Competition convertCompetitionDTOToDAO(CompetitionDTO competitionDTO) {
        LOGGER.info("Converting Competition DTO to DAO...");
        Competition competition = modelMapper.map(competitionDTO, Competition.class);
        LOGGER.info("Converted Competition DTO to DAO.");
        return competition;
    }

    public CompetitionDTO convertCompetitionDTO(Competition competition) {
        LOGGER.info("Converting Competition DAO to DTO...");
        CompetitionDTO competitionDTO = modelMapper.map(competition, CompetitionDTO.class);
        LOGGER.info("Converted Competition DAO to DTO.");
        return competitionDTO;
    }

    public Jump convertJumpDTOToDAO(JumpDTO jumpDTO) {
        LOGGER.info("Converting Jump DTO to DAO...");
        Jump jump = modelMapper.map(jumpDTO, Jump.class);
        LOGGER.info("Converted Jump DTO to DAO.");
        return jump;
    }

    public JumpDTO convertJumpDTO(Jump jump) {
        LOGGER.info("Converting Jump DAO to DTO...");
        JumpDTO jumpDTO = modelMapper.map(jump, JumpDTO.class);
        LOGGER.info("Converted Jump DAO to DTO.");
        return jumpDTO;
    }

}
