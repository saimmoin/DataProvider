package com.DataProvider.DataProvider.Service.impl;

import com.DataProvider.DataProvider.DTO.MessageDTO;
import com.DataProvider.DataProvider.Service.MessageService;
import jakarta.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    Queue queue;
    @Override
    public String sendMessage(MessageDTO messageDTO) {
        System.out.println("Message sending: "+messageDTO.getMessage()+"_"+messageDTO.getSource());
        String messageAndSource = messageDTO.getMessage()+messageDTO.getSource();
        jmsMessagingTemplate.convertAndSend(queue, messageAndSource);
        return messageAndSource;
    }
}
