package ru.diasoft.task.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface MessageService {

    List<Map<String, String>> getAll();

    Map<String, String> getOne(@PathVariable String id);

    Map<String, String> create(@RequestBody Map<String, String> message);

    Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message);

    void delete(@PathVariable String id);
}
