package com.burachenko.munichhotel.repository;

import com.burachenko.munichhotel.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findByTelNum(String telNum);
}
