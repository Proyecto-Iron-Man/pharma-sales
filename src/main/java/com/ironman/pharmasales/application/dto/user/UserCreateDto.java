package com.ironman.pharmasales.application.dto.user;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCreateDto {
    @NotBlank(message = "El campo nombre es requerido")
    @Size(min = 3)
    private String name;

    private String lastName;

    @NotBlank(message = "El campo email es requerido")
    @Email(message = "Ingrese un email valido")
    private String email;


    private String password;

    @NotNull(message = "El campo perfil es requerido")
    @Positive(message = "El campo perfil debe ser un numero positivo")
    private Long profileId;
}
