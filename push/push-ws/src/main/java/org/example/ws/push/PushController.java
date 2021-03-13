package org.example.ws.push;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.db.events.derby.push.Token;
import org.example.service.push.PushService;
import org.example.ws.push.dto.token.TokenDto;
import org.example.ws.push.dto.token.TokenFullDto;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/api/push",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Push", description = "Сервис отправки push сообщений")
public class PushController {

    private final ConversionService conversionService;
    private final PushService pushService;

    public PushController(ConversionService conversionService,
                          PushService pushService) {
        this.conversionService = conversionService;
        this.pushService = pushService;
    }

    @GetMapping
    @Operation(summary = "Получить Token'ы", description = "Получить все сохраненные Token'ы")
    public TokenFullDto[] getEmails() {
        List<Token> tokens = pushService.getAllTokens();
        return conversionService.convert(tokens, TokenFullDto[].class);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Добавить Email")
    public ResponseEntity addEmail(@Valid @RequestBody TokenDto dto) {
        Token token = conversionService.convert(dto, Token.class);
        pushService.saveToken(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
