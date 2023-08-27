package com.DataProvider.DataProvider.Service;

import com.DataProvider.DataProvider.DTO.RegisterRequestDTO;
import com.DataProvider.DataProvider.DTO.loginReSPONSEDTO;
import com.DataProvider.DataProvider.DTO.loginRequestDTO;

public interface AuthService {
    loginReSPONSEDTO login(loginRequestDTO dto);
    loginReSPONSEDTO register (RegisterRequestDTO dto);
}
