package org.example.ws.email.dto.email;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class EmailFullDto extends EmailDto {
    @Schema(description = "id", example = "1")
    @NotNull(message = "id email'а - обязательное поле")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
