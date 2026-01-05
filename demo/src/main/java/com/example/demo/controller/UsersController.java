package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.IUsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UsersController {

    private final IUsersService iUsersService;

    @GetMapping("")
    public List<Users> findAll() {
        return iUsersService.getAll();
    }

    @GetMapping("/{id}")
    public Users findById(@PathVariable Long id) {
        return iUsersService.getBYId(id);
    }

    @PostMapping("")
    public Users save(@RequestBody Users users) {
        return iUsersService.saveUser(users);
    }

    @PostMapping("/update")
    public Users update(@RequestBody Users users) {
        return iUsersService.update(users);
    }

}
