package io.spring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class Login {
    @NotBlank(message = "can't be empty")
    @Email(message = "should be an email")
    private String email;
    @NotBlank(message = "can't be empty")
    private String password;
}
