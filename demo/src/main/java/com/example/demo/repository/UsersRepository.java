package com.example.demo.repository;

import com.example.demo.entity.Users;
import com.example.demo.repository.custom.UsersCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long>, UsersCustomRepository {
}
