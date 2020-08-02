package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.Competition;
import is.symphony.collegeinternship.olympicgames.models.Jump;
import is.symphony.collegeinternship.olympicgames.models.Result;
import is.symphony.collegeinternship.olympicgames.models.dto.JumpDTO;
import is.symphony.collegeinternship.olympicgames.repositories.JumpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JumpService {
    private static Logger LOGGER = LoggerFactory.getLogger(JumpService.class);
    @Autowired
    private JumpRepository jumpRepository;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private DTOConverterService dtoConverterService;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private ResultService resultService;

    public JumpDTO saveDTO(JumpDTO jumpDTO) throws ElementExistsException, ElementNotFoundException {
        Competition competition = dtoConverterService.convertCompetitionDTOToDAO(jumpDTO.getCompetition());
        Athlete athlete = dtoConverterService.convertAthleteDTOToDAO(jumpDTO.getAthlete());

        Jump jump = dtoConverterService.convertJumpDTOToDAO(jumpDTO);


        if (jumpRepository.existsByCompetitionAndAthlete(jump.getCompetition(),jump.getAthlete())) {
            LOGGER.error("Jump already exists!");
            throw new ElementExistsException();
        }

        calculateBestJumps(jump);
        competitionService.setCompetitionState(competition.getId(),"STARTED");

        jump.setAthlete(athleteService.setAthlete(athlete));
        jump.setCompetition(competitionService.setCompetition(competition));

        LOGGER.info("Saving jump...");
        Jump save = jumpRepository.save(jump);
        LOGGER.info("Jump {} saved!", save);

        LOGGER.info("Calculating results...");
        Result result = resultService.save(jump);
        LOGGER.info("Results {} calculated.", result);

        return dtoConverterService.convertJumpDTO(save);
    }

    public List<JumpDTO> findAllDTO() {
        LOGGER.info("Accessing DB to get all jumps...");
        List<Jump> allJumps = jumpRepository.findAll();
        LOGGER.info("Found {} jumps!", allJumps.size());

        List<JumpDTO> allJumpsList = jumpRepository.findAll().stream().map(dtoConverterService::convertJumpDTO).collect(Collectors.toList());
        return allJumpsList;
    }

    public JumpDTO findDTOById(Long id) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a jump...");
            Jump found = jumpRepository.findById(id).get();
            LOGGER.info("Jump {} found.", found);
            return dtoConverterService.convertJumpDTO(found);
        } catch (Exception e){
            LOGGER.info("Jump not found.");
            throw new ElementNotFoundException();
        }
    }

    public Jump findById(Long id) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a jump...");
            Jump found = jumpRepository.findById(id).get();
            LOGGER.info("Jump {} found.", found);
            return found;
        } catch (Exception e){
            LOGGER.info("Jump not found.");
            throw new ElementNotFoundException();
        }
    }

    public void delete(Long id) throws ElementNotFoundException {
        LOGGER.info("Accessing DB to get a jump...");
        Jump jump = findById(id);
        LOGGER.info("Deleting a jump...");
        jumpRepository.delete(jump);
        LOGGER.info("Jump deleted.");
        resultService.delete(jump);
    }

    public JumpDTO update(JumpDTO jumpDTO) throws ElementNotFoundException{
        Competition competition = dtoConverterService.convertCompetitionDTOToDAO(jumpDTO.getCompetition());
        Athlete athlete = dtoConverterService.convertAthleteDTOToDAO(jumpDTO.getAthlete());

        Jump jump = dtoConverterService.convertJumpDTOToDAO(jumpDTO);

        if (!jumpRepository.existsById(jump.getId())) {
            LOGGER.error("Jump does not exist!");
            throw new ElementNotFoundException();
        }

        jump.setAthlete(athleteService.setAthlete(athlete));
        jump.setCompetition(competitionService.setCompetition(competition));

        calculateBestJumps(jump);

        LOGGER.info("Saving jump...");
        Jump save = jumpRepository.save(jump);
        LOGGER.info("Jump {} saved!", save);

        LOGGER.info("Calculating results...");
        Result result = resultService.update(jump);
        LOGGER.info("Results {} calculated.", result);

        return dtoConverterService.convertJumpDTO(save);
    }


    private void calculateBestJumps(Jump jump) {
        LOGGER.info("Calculating best jumps...");
        ArrayList<Double> allJumps = new ArrayList<Double>();
        allJumps.add(jump.getFirstJump());
        allJumps.add(jump.getSecondJump());
        allJumps.add(jump.getThirdJump());
        allJumps.sort(Collections.reverseOrder());

        double bestJump = allJumps.get(0);
        jump.setBestJump(bestJump);

        double secondBestJump = allJumps.get(1);
        jump.setSecondBestJump(secondBestJump);

        LOGGER.info("Calculated best jumps.");
    }

}
