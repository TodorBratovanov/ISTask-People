package com.example.istaskpeople.controller;

import com.example.istaskpeople.model.PersonDTO;
import com.example.istaskpeople.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
