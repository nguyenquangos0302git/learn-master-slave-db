package com.example.demo.service.impl;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.IUsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements IUsersService {

    private final UsersRepository iUsersRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Users> getAll() {
        return iUsersRepository.findListCustome();
    }

    @Override
    @Transactional(readOnly = true)
    public Users getBYId(Long id) {
        return iUsersRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Users saveUser(Users users) {
        return iUsersRepository.save(users);
    }

    @Override
    @Transactional
    public Users update(Users users) {
        Users users1 = getBYId(users.getId());
        users1.setName(users.getName());

        return saveUser(users1);
    }


}
