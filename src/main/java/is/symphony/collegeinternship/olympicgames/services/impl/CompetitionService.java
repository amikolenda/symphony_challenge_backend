package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.Competition;
import is.symphony.collegeinternship.olympicgames.models.dto.CompetitionDTO;
import is.symphony.collegeinternship.olympicgames.repositories.CompetitionRepository;
import is.symphony.collegeinternship.olympicgames.repositories.SportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompetitionService {
    private static Logger LOGGER = LoggerFactory.getLogger(CompetitionService.class);

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    SportRepository sportRepository;

    @Autowired
    DTOConverterService dtoConverterService;

    @Autowired
    AthleteService athleteService;

    public CompetitionDTO save(CompetitionDTO competitionDTO) throws ElementExistsException, ElementNotFoundException {
        if (competitionRepository.existsByName(competitionDTO.getName())) {
            LOGGER.error("Competition already exists!");
            throw new ElementExistsException();
        }
        Competition competition = dtoConverterService.convertCompetitionDTOToDAO(competitionDTO);

        Set<Athlete> athletesSet = competition.getAthletes();
        if (athletesSet != null) competition.setAthletes(athleteService.setAthletes(athletesSet));

        competition.setState("CREATED");

        LOGGER.info("Saving competition...");
        Competition save = competitionRepository.save(competition);
        LOGGER.info("Saved {}", save);

        return dtoConverterService.convertCompetitionDTO(save);
    }


    public List<CompetitionDTO> findAllDTO() {
        LOGGER.info("Accessing DB to get all competitions...");
        List<Competition> allCompetitions = competitionRepository.findAll();
        LOGGER.info("Found {} competitions!", allCompetitions.size());

        List<CompetitionDTO> allCompetitionsList = competitionRepository.findAll().stream().map(dtoConverterService::convertCompetitionDTO).collect(Collectors.toList());
        return allCompetitionsList;
    }

    public CompetitionDTO findDTOByName(String name) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a competition...");
            Competition found = competitionRepository.findByName(name);
            LOGGER.info("Competition found.");
            return dtoConverterService.convertCompetitionDTO(found);
        } catch (Exception e){
            LOGGER.info("Competition not found.");
            throw new ElementNotFoundException();
        }
    }

    public Competition findByName(String name) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a competition...");
            Competition found = competitionRepository.findByName(name);
            LOGGER.info("Competition found.");
            return found;
        } catch (Exception e){
            LOGGER.info("Competition not found.");
            throw new ElementNotFoundException();
        }
    }
    public CompetitionDTO findDTOById(Long id) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a competition...");
            Competition found = competitionRepository.findById(id).get();
            LOGGER.info("Competition found.");
            return dtoConverterService.convertCompetitionDTO(found);
        } catch (Exception e){
            LOGGER.info("Competition not found.");
            throw new ElementNotFoundException();
        }
    }
    public Competition findById(Long id) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a competition...");
            Competition found = competitionRepository.findById(id).get();
            LOGGER.info("Competition found.");
            return found;
        } catch (Exception e){
            LOGGER.info("Competition not found.");
            throw new ElementNotFoundException();
        }
    }

    public void delete(Long id) throws ElementNotFoundException {
        LOGGER.info("Accessing DB to get a competition...");
        Competition competition = findById(id);
        LOGGER.info("Deleting competition...");
        competitionRepository.delete(competition);
        LOGGER.info("Competition deleted.");
    }

    public CompetitionDTO update(CompetitionDTO competitionDTO) throws ElementExistsException, ElementNotFoundException {
        if (!competitionRepository.existsById(competitionDTO.getId())) {
            LOGGER.error("Competition does not exist!");
            throw new ElementNotFoundException();
        }
        Competition competition = dtoConverterService.convertCompetitionDTOToDAO(competitionDTO);
        Set<Athlete> athletesSet = competition.getAthletes();
        if (athletesSet != null) competition.setAthletes(athleteService.setAthletes(athletesSet));

        LOGGER.info("Updating competition...");
        Competition save = competitionRepository.save(competition);
        LOGGER.info("Updated {}", save);
        return dtoConverterService.convertCompetitionDTO(save);
    }

    public Competition setCompetitionState(Long id, String state) throws ElementNotFoundException{
        Competition competition = findById(id);
        LOGGER.info("Setting competition state: {}", state);
        competition.setState(state);
        LOGGER.info("Saving competition...");
        Competition save = competitionRepository.save(competition);
        LOGGER.info("Saved and state set {}", save);
        return save;
    }

    public Competition setCompetition(Competition competition) throws ElementNotFoundException {
        LOGGER.info("Checking if Competition exists...");
        if (competitionRepository.existsById(competition.getId())){
            Long id = competition.getId();
            Competition existingCompetition = findById(id);
            LOGGER.info("Competition exists");
            return existingCompetition;
        } else {
            LOGGER.error("Competition does not exist!");
            throw new ElementNotFoundException();
        }

    }
}
