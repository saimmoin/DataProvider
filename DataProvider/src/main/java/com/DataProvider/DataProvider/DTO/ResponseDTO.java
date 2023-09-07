package com.DataProvider.DataProvider.DTO;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDTO {
    Object body;
    String message;
    HttpStatus statusCode;

}
