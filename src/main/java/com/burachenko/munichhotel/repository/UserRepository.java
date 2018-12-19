package com.burachenko.munichhotel.repository;

import com.burachenko.munichhotel.dbo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
