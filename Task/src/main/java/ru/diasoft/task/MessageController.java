package ru.diasoft.task;

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

import ru.diasoft.task.services.MessageService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages/v1.1")
public class MessageController {

    private final MessageService messageService;

    private static Logger LOG = LogManager.getLogger(MessageController.class);

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping
    @LogExecutionTime
    public List<Map<String, String>> getAll(){
        return messageService.getAll();
    }

    @GetMapping("{Id}")
    @LogExecutionTime
    public Map<String, String> getOne(@PathVariable String id){
        return messageService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LogExecutionTime
    public Map<String, String> create(@RequestBody Map<String, String> message){
        return messageService.create(message);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @LogExecutionTime
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message){
        return  messageService.update(id, message);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @LogExecutionTime
    public void delete(@PathVariable String id){
        messageService.delete(id);
    }

}
