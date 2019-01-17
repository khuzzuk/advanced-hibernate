package com.example.advancedhibernate.message;

import com.example.advancedhibernate.db.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private EntityManager entityManager;

    @Override
    public void save(Message message) {

    }
}
