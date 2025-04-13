package com.moody.moody_api.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moody.moody_api.dto.UserDto;
import com.moody.moody_api.exception.UserNotFoundException;
import com.moody.moody_api.model.UserModel;
import com.moody.moody_api.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController{
	@Autowired//Faz a implementação da interface UserRepository em tempo de execução
	private UserRepository userRepository;

	@PostMapping("/register")//Endpoint para cadastro de usuario
	@Operation(summary = "Cadastra novo usuario", description = "Cadastra um usuario com email e senha")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Usuario cadastrado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Erro na validação de dados"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto userDto){
		BCryptPasswordEncoder crypto = new BCryptPasswordEncoder();
		String passwordCrypt = crypto.encode(userDto.getPassword());
		UserModel user = new UserModel();
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordCrypt);
		userRepository.save(user);//Salva o usuario no banco de dados, através do metódo save do Repository
		return ResponseEntity.ok("Usuário cadastrado com sucesso!");
	}
	@Operation(summary = "Deleta usuario", description = "Deleta um usuario pelo email")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuario não localizado"),
		@ApiResponse(responseCode = "500", description = "Erro Interno do Servidor")
	})
	@DeleteMapping("/delete_user/{email}")//Endpoint para deletar usuario
	public ResponseEntity<?> deleteUser(@Parameter(description = "Email") @PathVariable String email){
			Optional<UserModel> user = userRepository.findByEmail(email);//Como a busca pelo email pode retornar void, 
			//é usado o Optional
			if(user.isPresent()){//Verifica se há algo em user, se houver, deleta o usuario encontrado.
				userRepository.deleteById(user.get().getId());
				return ResponseEntity.ok("Usuario deletado com sucesso");//Exibe uma mensagem indicando o sucesso
			}
			throw new UserNotFoundException("Usuario não encontrado");

	}
	@Operation(summary = "Localiza usuario", description = "Localiza usuario pelo email")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuario não localizado"),
		@ApiResponse(responseCode = "500", description = "Erro Interno do Servidor")
	})
	@GetMapping("/{email}")
	public ResponseEntity<?> findUserByEmail(@Parameter(description = "Email")@PathVariable String email){
			Optional<UserModel> user = userRepository.findByEmail(email);
			if(user.isPresent()){
				return ResponseEntity.ok(user);
			}
			throw new UserNotFoundException("Usuario não encontrado");
	}
	@Operation(summary = "Atualiza usuario", description = "Atualiza email e senha do usuario pelo ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuario não localizado"),
		@ApiResponse(responseCode = "500", description = "Erro Interno do Servidor")
	})
	@PutMapping("/update_user/{id}")
	public ResponseEntity<?> updateUser(@Parameter(description = "ID") @PathVariable UUID id, @RequestBody @Valid UserModel updatedUser){
			try{
				Optional<UserModel> userOptional = userRepository.findById(id);
				if(userOptional.isPresent()){
					UserModel existingUser = userOptional.get();
					existingUser.setEmail(updatedUser.getEmail());
					existingUser.setPassword(updatedUser.getPassword());
					userRepository.save(existingUser);
					return ResponseEntity.ok("Usuario atualizado com sucesso");
				}
				throw new UserNotFoundException("Usuario não encontrado");

			}catch(Exception e){
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado " + e.getMessage());
			}
	}
}