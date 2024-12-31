package com.moody.moody_api.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moody.moody_api.model.UserModel;
import com.moody.moody_api.repository.UserRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController{
	@Autowired//Faz a implementação da interface UserRepository em tempo de execução
	private UserRepository userRepository;

	@PostMapping("/register")//Endpoint para cadastro de usuario
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserModel user){
		try{
			userRepository.save(user);//Salva o usuario no banco de dados, através do metódo save do Repository
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado: " + e.getMessage());
		}
		return ResponseEntity.ok("Usuário cadastrado com sucesso!");
	}

	@DeleteMapping("/delete_user/{email}")//Endpoint para deletar usuario
	public ResponseEntity<?> deleteUser(@PathVariable String email){
		try{
			Optional<UserModel> user = userRepository.findByEmail(email);//Como a busca pelo email pode retornar void, 
			//é usado o Optional
			if(user.isPresent()){//Verifica se há algo em user, se houver, deleta o usuario encontrado.
				userRepository.deleteById(user.get().getId());
				return ResponseEntity.ok("Usuario deletado com sucesso");//Exibe uma mensagem indicando o sucesso
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");//Caso não encontre, exibe uma mensagem
		}catch(Exception e){//Por se tratar de uma operação com banco de dados, há o tratamento de erro de conexão

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado: " + e.getMessage());

		}
	}
	@GetMapping("/{email}")
	public ResponseEntity<?> findUserByEmail(@PathVariable String email){
		try{
			Optional<UserModel> user = userRepository.findByEmail(email);
			if(user.isPresent()){
				return ResponseEntity.ok(user);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado " + e.getMessage());
		}
	}

	@PutMapping("/update_user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody @Valid UserModel updatedUser){
			try{
				Optional<UserModel> userOptional = userRepository.findById(id);
				if(userOptional.isPresent()){
					UserModel existingUser = userOptional.get();
					existingUser.setEmail(updatedUser.getEmail());
					existingUser.setPassword(updatedUser.getPassword());
					userRepository.save(existingUser);
					return ResponseEntity.ok("Usuario atualizado com sucesso");
				}
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

			}catch(Exception e){
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado " + e.getMessage());
			}
	}
}