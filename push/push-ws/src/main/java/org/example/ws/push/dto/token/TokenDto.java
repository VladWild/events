package org.example.ws.push.dto.token;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class TokenDto {
    @NotBlank(message = "Token не должен быть пустым")
    @Schema(description = "token", example = "xyb5-tIML-GoEk-p46g")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
