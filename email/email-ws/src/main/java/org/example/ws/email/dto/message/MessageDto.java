package org.example.ws.email.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MessageDto {
    @NotBlank(message = "сообщение не должно быть пустым")
    @Schema(description = "сообщение", example = "Как дела?")
    private String message;
    @Schema(description = "id email'а", example = "1")
    @NotNull(message = "id email'а не должно быть пустым")
    private Long emailId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }
}
