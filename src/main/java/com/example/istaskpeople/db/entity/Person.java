package com.example.istaskpeople.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_people")
@SecondaryTable(name = "t_addresses", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@SecondaryTable(name = "t_mails", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "pin")
    private String pin;

    @Embedded
    private Address address;

    @Embedded
    private Mail mail;

}
