package com.example.istaskpeople.controller;

import com.example.istaskpeople.model.PersonDTO;
import com.example.istaskpeople.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "people")
public class PeopleController {

    private final PeopleService peopleService;

    @GetMapping
    public String showPeopleByName(@RequestParam(value = "search", required = false) String name, Model model) {
        List<PersonDTO> allByFullName = (StringUtils.isEmpty(name)) ?
                peopleService.findAll() :
                peopleService.findAllByFullName(name);
        model.addAttribute("people", allByFullName);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {
        Optional<PersonDTO> optionalPerson = peopleService.findById(id);
        if (!optionalPerson.isPresent()) {
            return "index";
        }
        model.addAttribute("person", optionalPerson.get());
        return "update-person";
    }

    @PostMapping("/edit/{id}")
    public String updatePerson(@PathVariable(value = "id") long id, @Valid PersonDTO person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "update-person";
        }
        Optional<PersonDTO> optionalUpdated = peopleService.update(person);
        model.addAttribute("people", peopleService.findAll());
        optionalUpdated.ifPresent(personDTO ->
                model.addAttribute("message", String.format("%s successfully updated.", personDTO.getFullName())));
        return "index";
    }

    @GetMapping(value = "/add")
    public String showAddForm(PersonDTO person) {
        return "add-person";
    }

    @PostMapping(value = "/add")
    public String addPerson(@Valid PersonDTO person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-person";
        }
        PersonDTO created = peopleService.save(person);
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("message", String.format("%s successfully added.", created.getFullName()));
        return "index";
    }

    @GetMapping(value = "/delete/{id}")
    public String deletePerson(@PathVariable(value = "id") long id, Model model) {
        Optional<PersonDTO> deletedOptional = peopleService.delete(id);
        model.addAttribute("people", peopleService.findAll());
        deletedOptional.ifPresent(personDTO ->
                model.addAttribute("message", String.format("%s successfully added.", personDTO.getFullName())));
        return "index";
    }

}
