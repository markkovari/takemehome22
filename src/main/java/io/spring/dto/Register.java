package io.spring.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class Register {
    @NotBlank(message = "can't be empty")
    @Email(message = "should be an email")
    private String email;
    @NotBlank(message = "can't be empty")
    private String username;
    @NotBlank(message = "can't be empty")
    private String password;
}
