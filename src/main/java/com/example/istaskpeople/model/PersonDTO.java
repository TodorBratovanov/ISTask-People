package com.example.istaskpeople.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonDTO {

    private long id;
    private String fullName;
    private String pin;
    private String addressType;
    private String addressInfo;
    private String emailType;
    private String email;

}
