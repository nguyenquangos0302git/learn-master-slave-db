package com.example.demo.repository.write;

import com.example.demo.entity.Users;
import com.example.demo.repository.write.custom.UsersWriteCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersWriteRepository extends JpaRepository<Users, Long>, UsersWriteCustomRepository {
}
