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
}
