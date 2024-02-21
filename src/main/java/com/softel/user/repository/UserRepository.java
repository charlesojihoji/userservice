package com.softel.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softel.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
