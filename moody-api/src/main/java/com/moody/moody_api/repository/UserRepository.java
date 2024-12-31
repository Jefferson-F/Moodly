package com.moody.moody_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moody.moody_api.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID>{//Criando repository para usuario
    Optional<UserModel> findByEmail(String email);
    
}
