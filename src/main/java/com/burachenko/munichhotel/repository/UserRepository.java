package com.burachenko.munichhotel.repository;

import com.burachenko.munichhotel.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BasicRepository<UserEntity> {

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findByTelNum(String telNum);

    @Query(value = "SELECT * FROM user WHERE email=:searchParameter OR tel_num=:searchParameter", nativeQuery = true)
    List<UserEntity> findUserByEmailOrTelNum(String searchParameter);

}
