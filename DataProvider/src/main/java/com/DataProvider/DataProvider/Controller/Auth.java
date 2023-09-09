package com.DataProvider.DataProvider.Controller;


import com.DataProvider.DataProvider.DTO.*;
import com.DataProvider.DataProvider.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")

public class Auth {
    @Autowired
    AuthService authService;
//    @Autowired
//    RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody RegisterRequestDTO dto){

        return authService.register(dto);
    }
    @PostMapping("/login")
    public ResponseDTO login(@RequestBody loginRequestDTO dto){

        return authService.login(dto);
    }
    @PostMapping("/refresh")
    public ResponseDTO refresh(@RequestBody RefreshTokenRequestDTO dto){

        return authService.generateRefreshToken(dto);
    }

    @PostMapping("/logout")
    public ResponseDTO logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("hello");
        return   authService.logout(authentication,request,response);
    }
}
