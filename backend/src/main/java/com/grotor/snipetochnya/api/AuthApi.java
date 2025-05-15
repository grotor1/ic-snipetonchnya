package com.grotor.snipetochnya.api;

import com.grotor.snipetochnya.dto.request.AuthenticationRequest;
import com.grotor.snipetochnya.dto.request.AccountRequest;
import com.grotor.snipetochnya.dto.request.TokenRefreshRequest;
import com.grotor.snipetochnya.dto.response.TokenCoupleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Авторизация")
@RequestMapping("/auth")
public interface AuthApi {
    @Operation(summary = "Регистрация")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Создано", content = @Content),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content),
    })
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    void registration(@Parameter(description = "Информация об аккаунте") @RequestBody AccountRequest accountRequest);

    @Operation(summary = "Вход")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenCoupleResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Данные неверные", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content),
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    TokenCoupleResponse login(@Parameter(description = "Информация для входа") @RequestBody AuthenticationRequest authenticationRequest);

    @Operation(description = "Обновление токена")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenCoupleResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Невалидный токен", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content),
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/refresh")
    TokenCoupleResponse refresh(@Parameter(description = "Токен для обновления") @RequestBody TokenRefreshRequest tokenRefreshRequest);
}