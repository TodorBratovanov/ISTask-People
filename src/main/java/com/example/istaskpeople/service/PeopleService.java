package com.example.istaskpeople.service;

import com.example.istaskpeople.db.entity.Address;
import com.example.istaskpeople.db.entity.Mail;
import com.example.istaskpeople.db.entity.Person;
import com.example.istaskpeople.db.repository.PersonRepository;
import com.example.istaskpeople.model.PersonDTO;
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

    public Optional<PersonDTO> findById(long id) {
        return personRepository.findById(id).map(this::toDto);
    }

    public PersonDTO save(PersonDTO person) {
        return toDto(personRepository.save(toEntity(person)));
    }

    public Optional<PersonDTO> update(PersonDTO dto) {
        return personRepository
                .findById(dto.getId())
                .map(p -> {
                    p.setFullName(dto.getFullName());
                    p.setPin(dto.getPin());
                    p.getAddress().setAddressType(dto.getAddressType());
                    p.getAddress().setAddressInfo(dto.getAddressInfo());
                    p.getMail().setEmailType(dto.getEmailType());
                    p.getMail().setEmail(dto.getEmail());
                    return toDto(personRepository.save(p));
                });
    }

    public Optional<PersonDTO> delete(long id) {
        return personRepository.findById(id).map(p -> {
            personRepository.delete(p);
            return toDto(p);
        });
    }

    private PersonDTO toDto(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getFullName(),
                person.getPin(),
                person.getAddress().getAddressType(),
                person.getAddress().getAddressInfo(),
                person.getMail().getEmailType(),
                person.getMail().getEmail()
        );
    }

    private Person toEntity(PersonDTO dto) {
        Address address = new Address(dto.getAddressType(), dto.getAddressInfo());
        Mail mail = new Mail(dto.getEmailType(), dto.getEmail());
        Person person = new Person();
        person.setFullName(dto.getFullName());
        person.setPin(dto.getPin());
        person.setAddress(address);
        person.setMail(mail);
        return person;
    }

}
