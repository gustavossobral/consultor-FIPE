package com.nexuscar.system.nexuscarsystem.repository;

import com.nexuscar.system.nexuscarsystem.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserDetails findByEmail(String email);

}
