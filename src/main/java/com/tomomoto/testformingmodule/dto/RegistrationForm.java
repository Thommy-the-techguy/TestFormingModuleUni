package com.tomomoto.testformingmodule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class RegistrationForm {
    @NotBlank
    @Pattern(regexp = "(.+)@([a-zA-Z]{2,})\\..{2,}", message = "Invalid email")
    private String email;
    @NotBlank(message = "Username shouldn't be blank")
    @Size(min = 4, max = 15, message = "Size must be from 4 to 15 symbols")
    private String username;
    @NotBlank(message = "Password shouldn't be blank")
    @Size(min = 8, max = 20, message = "Size must be from 8 to 20 symbols")
    private String password;
}
