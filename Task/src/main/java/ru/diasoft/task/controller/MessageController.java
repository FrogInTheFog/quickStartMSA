package ru.diasoft.task.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import ru.diasoft.task.Exceptions.NotFoundException;
import ru.diasoft.task.LogExecutionTime;
import ru.diasoft.task.entiry.Message;
import ru.diasoft.task.services.MessageService;

import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;

    private static Logger LOG = LogManager.getLogger(MessageController.class);

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping
    @LogExecutionTime
    public List<Message> getAll(){
        return messageService.getAll();
    }

    @GetMapping("{id}")
    @LogExecutionTime
    public Message getOne(@PathVariable Long id){
        Message message = messageService.getOne(id);
        if (message == null) {
            throw new NotFoundException();
        }
        return message;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LogExecutionTime
    public Message create(@RequestBody Message message){
        return messageService.create(message);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @LogExecutionTime
    public Message update(@PathVariable Long id, @RequestBody Message message){
        return  messageService.update(id, message);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @LogExecutionTime
    public void delete(@PathVariable Long id){
        messageService.delete(id);
    }

}
