package com.DataProvider.DataProvider.Reciever;

import jakarta.jms.MessageConsumer;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component

public class Listener {
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(MessageConsumer.class);
    @JmsListener(destination = "Hockey")

    public void recieveQueue(String msg) {
//        logger.info("The Message have been recieved successfy : "+msg);
        System.out.println("Message send successfully!"+msg);
    }
}
