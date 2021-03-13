package org.example.ws.push.dto.token;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class TokenFullDto extends TokenDto {
    @Schema(description = "id", example = "1")
    @NotNull(message = "id token'а - обязательное поле")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
