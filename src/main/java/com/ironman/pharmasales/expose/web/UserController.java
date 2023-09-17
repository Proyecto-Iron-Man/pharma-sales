package com.ironman.pharmasales.expose.web;


import com.ironman.pharmasales.application.dto.user.UserCreateDto;
import com.ironman.pharmasales.application.dto.user.UserDto;
import com.ironman.pharmasales.application.service.UserService;
import com.ironman.pharmasales.shared.constant.StatusCode;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.exception.entity.ArgumentNotValidError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @ApiResponse(responseCode = StatusCode.CREATED)
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserCreateDto userCreate) throws DataNotFoundException {
        UserDto user = userService.create(userCreate);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(user);
    }

}
