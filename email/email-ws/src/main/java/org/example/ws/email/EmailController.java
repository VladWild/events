package org.example.ws.email;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.db.events.h2.email.Email;
import org.example.model.db.events.h2.email.EmailSendInfo;
import org.example.service.email.EmailSendService;
import org.example.service.email.EmailService;
import org.example.ws.email.dto.email.EmailDto;
import org.example.ws.email.dto.email.EmailFullDto;
import org.example.ws.email.dto.message.MessageDto;
import org.example.ws.email.dto.message.info.MessageInfoDto;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(
        value = "/api/email",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Email", description = "Сервис отправки сообщений по email")
public class EmailController {

    private final ConversionService conversionService;
    private final EmailSendService emailSendService;
    private final EmailService emailService;

    public EmailController(ConversionService conversionService,
                           EmailSendService emailSendService,
                           EmailService emailService) {
        this.conversionService = conversionService;
        this.emailSendService = emailSendService;
        this.emailService = emailService;
    }

    @GetMapping
    @Operation(summary = "Получить Email'ы", description = "Получить все сохраненные Email'ы")
    public EmailFullDto[] getEmails() {
        List<Email> emails = emailService.getAllEmails();
        return conversionService.convert(emails, EmailFullDto[].class);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Добавить Email")
    public ResponseEntity addEmail(@Valid @RequestBody EmailDto dto) {
        Email email = conversionService.convert(dto, Email.class);
        emailService.saveEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Изменить Email по id")
    public ResponseEntity changeEmail(@Valid @RequestBody EmailFullDto dto) {
        Email email = conversionService.convert(dto, Email.class);
        emailService.changeEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить Email по id")
    public ResponseEntity deleteEmail(@NotNull(message = "id - обязательный параметр") @PathVariable("id") Long id) {
        emailService.deleteEmail(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Отправить сообщение", description = "Отправить сообщение на Email")
    public ResponseEntity sendMessage(@Valid @RequestBody MessageDto dto) {
        EmailSendInfo info = conversionService.convert(dto, EmailSendInfo.class);
        emailSendService.sendMessage(info);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/send-info")
    @Operation(description = "Получить информацию об отправленных сообщениях")
    public Map<String, List<MessageInfoDto>> getSendInfo() {
        List<EmailSendInfo> emailSendInfo = emailSendService.getEmailSendInfo();
        return emailSendInfo.stream().collect(Collectors.groupingBy(info -> info.getEmail().getEmail(),
                Collectors.mapping(info -> new MessageInfoDto(info.getMassage(), info.getCreated()), toList())));
    }
}
