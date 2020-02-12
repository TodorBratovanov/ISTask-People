package com.example.istaskpeople.db.repository;

import com.example.istaskpeople.db.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByFullNameContainingIgnoreCase(String fullName);

}
