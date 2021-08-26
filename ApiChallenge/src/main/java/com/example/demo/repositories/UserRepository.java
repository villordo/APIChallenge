package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {

    public abstract User findByUsername(String user);

}
