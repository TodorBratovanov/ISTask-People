package com.example.istaskpeople.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    @Column(name = "email_type", table = "t_mails")
    private String emailType;

    @Column(name = "email", table = "t_mails")
    private String email;

}
