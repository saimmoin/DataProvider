package com.DataProvider.DataProvider.Service;

import com.DataProvider.DataProvider.DTO.*;
import com.DataProvider.DataProvider.Entity.RefreshToken;
import com.DataProvider.DataProvider.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {
    ResponseDTO login(loginRequestDTO dto);
    ResponseDTO register (RegisterRequestDTO dto);
    String generateToken(User u);


    ResponseDTO createToken(User userDetails);
    RefreshToken validateRefreshToken(String token);
    ResponseDTO generateRefreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
    ResponseDTO logout(Authentication authentication,
                       HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse);
}
