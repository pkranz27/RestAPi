package com.Pk.Udemy.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pk.Udemy.User.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
