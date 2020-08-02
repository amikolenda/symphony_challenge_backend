package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.Competition;
import is.symphony.collegeinternship.olympicgames.models.Jump;
import is.symphony.collegeinternship.olympicgames.models.Result;
import is.symphony.collegeinternship.olympicgames.models.dto.CompetitionDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.ResultDTO;
import is.symphony.collegeinternship.olympicgames.repositories.ResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {
    private static Logger LOGGER = LoggerFactory.getLogger(ResultService.class);
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private DTOConverterService dtoConverterService;
    @Autowired
    private CompetitionService competitionService;


    public Result save(Jump jump) throws ElementExistsException {
        Competition competition = jump.getCompetition();
        Athlete athlete = jump.getAthlete();

        if (resultRepository.existsByCompetitionAndAthlete(competition, athlete)) {
            LOGGER.error("Result already exists!");
            throw new ElementExistsException();
        }
        LOGGER.info("Creating result...");
        Result result = new Result();
        addFieldValues(result, jump);
        LOGGER.info("Result created. ");

        LOGGER.info("Saving result...");
        Result save = resultRepository.save(result);
        LOGGER.info("Result {} saved", save);

        LOGGER.info("Calculating positions...");
        calculatePositions(competition);
        LOGGER.info("Positions calculated");

        return result;
    }

    public List<Result> findAllResultsByCompetition(Competition competition){
        LOGGER.info("Accessing DB to get all results in order for a competition {}...", competition);
        List<Result> allResults = resultRepository.findAllByCompetitionOrderByBestJumpDescSecondBestJumpDesc(competition);
        LOGGER.info("Found all results {} in order for a competition {}...", allResults, competition);
        return allResults;
    }

    public List<ResultDTO> findAllDTOResultsByCompetition(CompetitionDTO competitionDTO){
        Competition competition = dtoConverterService.convertCompetitionDTOToDAO(competitionDTO);
        LOGGER.info("Accessing DB to get all results in order for a competition {}...", competition);
        List<Result> allResults = resultRepository.findAllByCompetitionOrderByBestJumpDescSecondBestJumpDesc(competition);
        LOGGER.info("Found all results {} in order for a competition {}...", allResults, competition);

        List<ResultDTO> allResultsList = resultRepository.findAllByCompetitionOrderByBestJumpDescSecondBestJumpDesc(competition).stream().map(dtoConverterService::convertResultDTO).collect(Collectors.toList());
        return allResultsList;
    }

    public List<Result> findAll(){
        LOGGER.info("Accessing DB to get ALL results...");
        List<Result> allResults = resultRepository.findAll();
        LOGGER.info("Found results. {}", allResults);
        return allResults;
    }

    public Result findById(Long id) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a result...");
            Result found = resultRepository.findById(id).get();
            LOGGER.info("Result {} found.", found);
            return found;
        } catch (Exception e){
            LOGGER.info("Result not found.");
            throw new ElementNotFoundException();
        }
    }

    public ResultDTO findDTOById(Long id) {
        try{
            LOGGER.info("Accessing DB to get a result...");
            Result found = resultRepository.findById(id).get();
            LOGGER.info("Result {} found.", found);
            return dtoConverterService.convertResultDTO(found);
        } catch (Exception e){
            LOGGER.info("Result not found.");
            throw new ElementNotFoundException();
        }
    }

    public void addFieldValues(Result result, Jump jump){
        LOGGER.info("Setting fields values for a result {}...",result);
        result.setAthlete(jump.getAthlete());
        result.setCompetition(jump.getCompetition());
        result.setFirstJump(jump.getFirstJump());
        result.setSecondJump(jump.getSecondJump());
        result.setThirdJump(jump.getThirdJump());
        result.setBestJump(jump.getBestJump());
        result.setSecondBestJump(jump.getSecondBestJump());
        LOGGER.info("Field values set for a result {}", result);

    }

    public void calculatePositions(Competition competition){
        LOGGER.info("Setting positions...");
        List<Result> allResults = findAllResultsByCompetition(competition);
        for (int i = 0; i < allResults.size(); i++){
            Result currentResult = allResults.get(i);
            currentResult.setPosition(i+1);
            resultRepository.save(currentResult);
        }
        List<Result> currentResults = findAllResultsByCompetition(competition);
        LOGGER.info("Positions set {}",currentResults);
    }

    public List<ResultDTO> showFinalResults(CompetitionDTO competitionDTO){
        Competition competition = competitionService.findById(competitionDTO.getId());

        competitionService.setCompetitionState(competition.getId(), "COMPLETED");

        assignMedals(competition);

        List<Result> allResults = findAllResultsByCompetition(competition);

        List<ResultDTO> allResultsList = findAllDTOResultsByCompetition(competitionDTO);

        return allResultsList;
    }

    public void assignMedals(Competition competition) throws ElementNotFoundException{
        LOGGER.info("Assigning medals...");
        List<Result> allResults = findAllResultsByCompetition(competition);
        if (allResults.size()>2 && competition.getState().equals("COMPLETED")){
            for (int i = 0; i <= 2; i++){
                Result currentResult = allResults.get(i);
                if (i==0) currentResult.setMedal("GOLD");
                if (i == 1) currentResult.setMedal("SILVER");
                if (i == 2) currentResult.setMedal("BRONZE");
                resultRepository.save(currentResult);
                LOGGER.info("Medal assigned to a result {}",findById(currentResult.getId()));
            }
        }else {
            LOGGER.error("Competition not completed!");
        }

    }

    public Result update(Jump jump) throws ElementNotFoundException{
        Competition competition = jump.getCompetition();
        Athlete athlete = jump.getAthlete();

        if (!resultRepository.existsByCompetitionAndAthlete(competition, athlete)) {
            LOGGER.error("Result does not exist!");
            throw new ElementNotFoundException();
        }
        LOGGER.info("Creating result...");
        Result result = resultRepository.findByCompetitionAndAthlete(jump.getCompetition(), jump.getAthlete());
        addFieldValues(result, jump);
        LOGGER.info("Result created. ");

        LOGGER.info("Saving result...");
        Result save = resultRepository.save(result);
        LOGGER.info("Result {} saved", save);

        LOGGER.info("Calculating positions...");
        calculatePositions(competition);
        LOGGER.info("Positions calculated");

        return result;
    }


    public void delete(Jump jump) throws ElementNotFoundException{
        LOGGER.info("Accessing DB to get a result...");
        try {
            Result result = resultRepository.findByCompetitionAndAthlete(jump.getCompetition(), jump.getAthlete());
            LOGGER.info("Deleting a result...");
            resultRepository.delete(result);
            LOGGER.info("Result deleted.");
        } catch (Exception e){
            LOGGER.error("Result not found! ");
            throw new ElementNotFoundException();
        }
    }
}
