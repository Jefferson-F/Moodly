package com.moody.moody_api.model;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @NotBlank(message= "O email não pode estar vazio")
    @Email(message= "O email não pode estar invalido")
	private String email;
    @NotBlank(message= "A senha não pode estar vazia")
    @Size(min = 8, message = "A senha precisa ter no minimo 8 caracteres")
	private String password;
}
