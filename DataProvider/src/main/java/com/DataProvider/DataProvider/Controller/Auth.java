package com.DataProvider.DataProvider.Controller;


import com.DataProvider.DataProvider.DTO.*;
import com.DataProvider.DataProvider.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class Auth {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public loginReSPONSEDTO register(@RequestBody RegisterRequestDTO dto){

        return authService.register(dto);
    }
    @PostMapping("/login")
    public loginReSPONSEDTO login(@RequestBody loginRequestDTO dto){

        return authService.login(dto);
    }
}
