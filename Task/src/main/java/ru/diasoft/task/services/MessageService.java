package ru.diasoft.task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.task.entiry.Message;
import ru.diasoft.task.repository.MessageRepository;


import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public Message create(Message message) {
        return messageRepository.save(message);
    }

    @Transactional
    public Message update(Long id, Message message) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isPresent()) {
            message.setId(id);
            messageRepository.save(message);
            return message;
        }
        return null;
    }

    public Message getOne(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Transactional
    public boolean delete(Long id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }



}