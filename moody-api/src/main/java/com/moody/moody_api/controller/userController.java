package com.moody.moody_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moody.moody_api.model.UserModel;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class userController{

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid UserModel user){
		/*if(user.getEmail() == ""){
			return "Erro ao registrar, email não pode estar vazio";
		}
		if(user.getPassword() == ""){
			return "Erro ao registrar, senha não pode estar vazio";
		}*/
		return ResponseEntity.ok("Usuário cadastrado com sucesso!");


	}
	
}