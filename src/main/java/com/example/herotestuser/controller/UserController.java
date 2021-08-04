package com.example.herotestuser.controller;

import com.example.herotestuser.dto.UserDto;
import com.example.herotestuser.entity.User;
import com.example.herotestuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new User();
        }
        User user = optionalUser.get();
        return user;
    }

    @PostMapping
    public User add(@RequestBody User user){
        User save = userRepository.save(user);
        return save;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Integer id, @RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return false;
        }
        User user1 = optionalUser.get();
        user1.setName(user.getName());
        user1.setPhone(user.getPhone());

        userRepository.save(user1);
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        userRepository.deleteById(id);
        return true;
    }

}
