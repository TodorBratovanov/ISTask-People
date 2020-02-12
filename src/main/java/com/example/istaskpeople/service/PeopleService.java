package com.example.istaskpeople.service;

import com.example.istaskpeople.db.entity.Person;
import com.example.istaskpeople.db.repository.PersonRepository;
import com.example.istaskpeople.model.PersonDTO;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PersonRepository personRepository;

    public List<PersonDTO> findAll() {
        return StreamSupport
                .stream(personRepository.findAll().spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<PersonDTO> findAllByFullName(String fullName) {
        return personRepository
                .findByFullNameContainingIgnoreCase(fullName)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private PersonDTO toDto(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .fullName(person.getFullName())
                .pin(person.getPin())
                .addressType(person.getAddress().getAddressType())
                .addressInfo(person.getAddress().getAddressInfo())
                .emailType(person.getMail().getEmailType())
                .email(person.getMail().getEmail())
                .build();
    }

}
