package com.DataProvider.DataProvider.Controller;

import com.DataProvider.DataProvider.DTO.MessageDTO;
import com.DataProvider.DataProvider.DTO.RegisterRequestDTO;
import com.DataProvider.DataProvider.DTO.ResponseDTO;
import com.DataProvider.DataProvider.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Queue")
public class MessageController {
    @Autowired
    MessageService messageService;
    @PostMapping("/send")
    public String send(@RequestBody MessageDTO dto){

        return messageService.sendMessage(dto);
    }
}
