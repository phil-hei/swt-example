package de.fhdo.swt.example.swtexampleapplication.controller;

import de.fhdo.swt.example.swtexampleapplication.entity.Journey;
import de.fhdo.swt.example.swtexampleapplication.exception.JourneyNotFoundException;
import de.fhdo.swt.example.swtexampleapplication.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private JourneyRepository journeyRepository;

    @GetMapping("/api/journey/{id}")
    public Journey getJourneyById(@PathVariable Long id) {
        return journeyRepository.findById(id).orElseThrow(JourneyNotFoundException::new);
    }

    @GetMapping("/api/journeys")
    public List<Journey> getAllJourneys() {
        List<Journey> journeys = new ArrayList<Journey>();
        journeyRepository.findAll().forEach(journeys::add);
        return journeys;
    }

    @PostMapping("/api/journey")
    @ResponseStatus(HttpStatus.CREATED)
    public Journey createJourney(@RequestBody Journey journey) {
        return journeyRepository.save(journey);
    }

    @PutMapping("/api/journey/{id}")
    public Journey updateJourney(@RequestBody Journey journey, @PathVariable Long id) {
        Journey updateJourney = journeyRepository.findById(id)
                .orElseThrow(JourneyNotFoundException::new);
        updateJourney.setOrigin(journey.getOrigin());
        updateJourney.setDestination(journey.getDestination());
        return journeyRepository.save(updateJourney);
    }

    @DeleteMapping("/api/journey/{id}")
    public void deleteJourney(@PathVariable Long id) {
        journeyRepository.deleteById(id);
    }
}
