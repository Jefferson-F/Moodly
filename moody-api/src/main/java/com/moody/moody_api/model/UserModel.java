package com.moody.moody_api.model;



import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Indica que a classe vai ser construida através de uma entidade no banco
@Table(name = "users")
@Data // Gera Getters, Setters, toString, equal e hashcode
@AllArgsConstructor // Gera o construtor com todos os campos
@NoArgsConstructor // Construtor sem argumentos
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Gera UUID automaticamente
    private UUID id;
    @NotBlank(message= "O email não pode estar vazio")
    @Email(message= "O email não pode estar invalido")
	private String email;
    @NotBlank(message= "A senha não pode estar vazia")
    @Size(min = 8, message = "A senha precisa ter no minimo 8 caracteres")
	private String password;
}
