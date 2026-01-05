package com.example.demo.repository.write.custom.impl;

import com.example.demo.entity.Users;
import com.example.demo.repository.write.custom.UsersWriteCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UsersWriteCustomRepositoryImpl implements UsersWriteCustomRepository {

    @PersistenceContext(unitName = "write")
    private EntityManager em;

    @Override
    @Transactional("writeTransactionManager")
    public List<Users> findListCustome() {
        return em.createQuery("from Users", Users.class).getResultList();
//        return List.of();
    }
}
