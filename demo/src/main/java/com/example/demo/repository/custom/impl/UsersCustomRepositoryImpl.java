package com.example.demo.repository.custom.impl;

import com.example.demo.entity.Users;
import com.example.demo.repository.custom.UsersCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UsersCustomRepositoryImpl implements UsersCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Users> findListCustome() {
        return em.createQuery("from Users", Users.class).getResultList();
//        return List.of();
    }
}
