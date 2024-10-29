package com.GrupoToday.security.controller;

import com.GrupoToday.security.dto.MyReqRes;
import com.GrupoToday.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<MyReqRes> registrationUser(@RequestBody MyReqRes myReqRes) throws Exception{
        return ResponseEntity.ok(userService.signup(myReqRes));
    }
    @PostMapping("/login")
    public ResponseEntity<MyReqRes> loginUser(@RequestBody MyReqRes myReqRes){
        return ResponseEntity.ok(userService.login(myReqRes));
    }
}
