package com.example.demo.repository.read.custom.impl;

import com.example.demo.entity.Users;
import com.example.demo.repository.read.custom.UsersReadCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UsersReadCustomRepositoryImpl implements UsersReadCustomRepository {

    @PersistenceContext(unitName = "read")
    private EntityManager slave;

    @Override
    @Transactional("readTransactionManager")
    public List<Users> findListCustome() {
        return slave.createQuery("from Users", Users.class).getResultList();
    }
}
