package de.fhdo.swt.example.swtexampleapplication.controller;

import de.fhdo.swt.example.swtexampleapplication.entity.Journey;
import de.fhdo.swt.example.swtexampleapplication.exception.JourneyNotFoundException;
import de.fhdo.swt.example.swtexampleapplication.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class JourneyController {

    @Autowired
    private JourneyRepository journeyRepository;

    @GetMapping("/journey")
    public String showJourneyForm(Journey journey, Model model) {
        model.addAttribute("journeys", journeyRepository.findAll());
        return "journey";
    }

    @GetMapping("/journey/add")
    public String showAddJourneyForm(Journey journey) {
        return "add-journey";
    }

    @PostMapping("/journey/add")
    public String addJourney(@Valid Journey journey, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-journey";
        }

        journeyRepository.save(journey);
        model.addAttribute("journeys", journeyRepository.findAll());
        return "journey";
    }

    @GetMapping("/journey/edit/{id}")
    public String showJourneyUpdateForm(@PathVariable("id") long id, Model model) {
        Journey journey = journeyRepository.findById(id)
                .orElseThrow(() -> new JourneyNotFoundException("Invalid journey Id:" + id));

        model.addAttribute("journey", journey);
        return "update-journey";
    }

    @PostMapping("/journey/update/{id}")
    public String updateJourney(@PathVariable("id") long id, @Valid Journey journey,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            journey.setId(id);
            return "update-journey";
        }

        journeyRepository.save(journey);
        model.addAttribute("journeys", journeyRepository.findAll());
        return "journey";
    }

    @GetMapping("/journey/delete/{id}")
    public String deleteJourney(@PathVariable("id") long id, Model model) {
        Journey journey = journeyRepository.findById(id)
                .orElseThrow(() -> new JourneyNotFoundException("Invalid journey Id:" + id));
        journeyRepository.delete(journey);
        model.addAttribute("journeys", journeyRepository.findAll());
        return "journey";
    }
}
