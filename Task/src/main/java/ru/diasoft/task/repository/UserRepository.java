package ru.diasoft.task.repository;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.diasoft.task.entiry.Role;
import ru.diasoft.task.entiry.User;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    @PostConstruct
    public void init(){
        users.addAll(Arrays.asList(
                new User("admin", new BCryptPasswordEncoder().encode("admin"),
                        Collections.singletonList(Role.ADMIN),true,
                        true, true, true),
                new User("imarkin", new BCryptPasswordEncoder().encode("im"),
                        Collections.singletonList(Role.ADMIN),true,
                        true, true, true),
                new User("user", new BCryptPasswordEncoder().encode("password"),
                        Collections.singletonList(Role.ADMIN),true,
                        true, true, true)
        ));
    }

    public Optional<User> findByUsername (String user){
        return users.stream()
                .filter(username -> username.getUsername().equals(user))
                .findFirst();
    }
}
