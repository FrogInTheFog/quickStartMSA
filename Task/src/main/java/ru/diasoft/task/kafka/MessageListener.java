package ru.diasoft.task.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessageListener {
    private Logger logger = LogManager.getLogger(MessageListener.class);

    @StreamListener(ConsumerChannels.DIRECTED)
    public void directed(String message) {
        logger.info("Сообщение получателю : " + message);
    }

    @StreamListener(ConsumerChannels.BROADCASTS)
    public void broadcasted(String message) {
        logger.info("Сообщение всем : " + message);
    }
}
