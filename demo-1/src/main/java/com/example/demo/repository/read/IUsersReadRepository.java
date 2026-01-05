package com.example.demo.repository.read;

import com.example.demo.entity.Users;
import com.example.demo.repository.read.custom.UsersReadCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersReadRepository extends JpaRepository<Users, Long>, UsersReadCustomRepository {
}
