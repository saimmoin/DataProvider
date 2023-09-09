package com.DataProvider.DataProvider.Service;

import com.DataProvider.DataProvider.DTO.*;
import com.DataProvider.DataProvider.Entity.Employee;
import com.DataProvider.DataProvider.Entity.RefreshToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {
    ResponseDTO login(loginRequestDTO dto);
    ResponseDTO register (RegisterRequestDTO dto);
    String generateToken(Employee e);


    ResponseDTO createToken(Employee userDetails);
    RefreshToken validateRefreshToken(String token);
    ResponseDTO generateRefreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
    ResponseDTO logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response);
}
