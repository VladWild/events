package org.example.ws.email.dto.email;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.ws.email.validator.email.ValidEmail;

import javax.validation.constraints.NotBlank;

public class EmailDto {
    @ValidEmail
    @NotBlank(message = "Email не должен быть пустым")
    @Schema(description = "email", example = "example@gmail.com")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
