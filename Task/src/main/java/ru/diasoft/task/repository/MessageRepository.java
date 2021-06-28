package ru.diasoft.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.task.entiry.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}