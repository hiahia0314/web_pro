package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.RES.Response2;
import com.example.demo.dataAccess.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/update")
    public Response2<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

//    @PutMapping("/friend/{id}")
//    public Response<UserDTO> updateFriendById(@PathVariable String id, @RequestParam(required = false) String e_mail,
//                                              @RequestParam(required = false) int age,@RequestParam(required = false) String name) {
//
//        return Response.newSuccess(userService.updateUserByUserId(id,e_mail,age,name),"success");
//
//    }
}
