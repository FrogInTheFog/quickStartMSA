package ru.diasoft.task.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.diasoft.task.entiry.Message;
import ru.diasoft.task.repository.MessageRepository;

import static org.junit.Assert.assertEquals;

@DataJpaTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void getDataJpaTest() {
        Message message = messageService.getOne(1L);
        assertEquals("Message text 1", message.getText());
    }

    @Test
    public void createDataJpaTest() {
        Message message = new Message();
        message.setText("Test message for create");
        messageService.create(message);

        Long newID = message.getId();
        Assert.assertNotNull(newID);
        Message foundMessage = messageService.getOne(newID);
        Assert.assertNotNull(foundMessage);
    }

    @Test
    public void updateDataJpaTest() {
        Message message = new Message();
        message.setText("Test message for update");
        messageService.create(message);

        Long newID = message.getId();
        Assert.assertNotNull(newID);

        Message newMessage = new Message();
        newMessage.setText("NEW Test message for update");
        messageService.update(newID, newMessage);


        Message foundMessage = messageService.getOne(newID);
        assertEquals("NEW Test message for update", foundMessage.getText());
    }

    @Test
    public void deleteDataJpaTest() {
        messageService.delete(2L);
        Assert.assertNull(messageService.getOne(2L));
    }

    @TestConfiguration
    static class MessageServiceTestContextConfiguration {

        @Bean
        public MessageService userService(MessageRepository messageRepository) {
            return new MessageService(messageRepository);
        }
    }


}