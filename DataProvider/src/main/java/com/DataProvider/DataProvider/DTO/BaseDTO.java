package com.DataProvider.DataProvider.DTO;

import org.springframework.http.HttpStatus;

public class BaseDTO {
    public String responseMessage;
    public HttpStatus responseCode;

    public Object responseBody;
}
