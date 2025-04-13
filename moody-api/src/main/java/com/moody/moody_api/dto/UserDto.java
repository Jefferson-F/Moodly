package com.moody.moody_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserDto {
    @Email(message = "Email invalido")
    @NotBlank(message = "Email não pode estar vazio")
    private String email;
    @Size(min = 8, message = "A senha precisa de no minimo 8 caracteres")
    @NotBlank(message = "Senha não pode estar vazia")
    private String password;
}
