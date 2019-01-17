package com.example.advancedhibernate.repo;

import com.example.advancedhibernate.db.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MessageRepo extends JpaRepository<Message, Long> {
}
