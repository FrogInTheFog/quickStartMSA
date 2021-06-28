package ru.diasoft.task.services;
import org.springframework.stereotype.Service;
import ru.diasoft.task.Exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService{

    private int counter = 2;
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{put("id", "1"); put("Text", "First message");}});
        add(new HashMap<String, String>() {{put("id", "2"); put("Text", "Second message");}});
    }};

    @Override
    public List<Map<String, String>> getAll(){
        return messages;
    }

    @Override
    public Map<String, String> getOne(String id){
        return getMessage(id);
    }

    private Map<String, String> getMessage(String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Map<String, String> create(Map<String, String> message){
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @Override
    public Map<String, String> update(String id, Map<String, String> message){
        Map<String, String> messageForUser = getMessage(message.get("id"));

        messageForUser.putAll(message);
        message.put("id", id);

        return  message;
    }

    @Override
    public void delete(String id){
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }

}
