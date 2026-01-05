package com.example.demo.service;

import com.example.demo.entity.Users;

import java.util.List;

public interface IUsersService {

    List<Users> getAll();

    Users getBYId(Long id);

    Users saveUser(Users users);

    Users update(Users users);

}
