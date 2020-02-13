package com.example.istaskpeople.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private long id;

    @NotEmpty
    @Size(max = 90)
    @Pattern(regexp = "^[A-Za-zА-Яа-я -]+$", message = "Must contain latin or cyrillic letters, space or hyphen")
    private String fullName;

    @Pattern(regexp = "^[0-9]{10}$", message = "Must contain 10 digit number")
    private String pin;

    @NotEmpty
    @Size(max = 5)
    private String addressType;

    @Size(max = 300)
    private String addressInfo;

    @NotEmpty
    @Size(max = 5)
    private String emailType;

    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Not a valid email address")
    @Size(max = 40)
    private String email;

}
