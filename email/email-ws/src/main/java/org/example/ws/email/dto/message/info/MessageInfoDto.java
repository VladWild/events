package org.example.ws.email.dto.message.info;

import java.time.LocalDateTime;

public class MessageInfoDto {
    private String massage;
    private LocalDateTime dateTime;

    public MessageInfoDto(String massage, LocalDateTime dateTime) {
        this.massage = massage;
        this.dateTime = dateTime;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
