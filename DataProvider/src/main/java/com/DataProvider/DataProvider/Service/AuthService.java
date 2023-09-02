package com.DataProvider.DataProvider.Service;

import com.DataProvider.DataProvider.DTO.RegisterRequestDTO;
import com.DataProvider.DataProvider.DTO.ResponseDTO;
import com.DataProvider.DataProvider.DTO.loginReSPONSEDTO;
import com.DataProvider.DataProvider.DTO.loginRequestDTO;

public interface AuthService {
    ResponseDTO login(loginRequestDTO dto);
    ResponseDTO register (RegisterRequestDTO dto);
}
