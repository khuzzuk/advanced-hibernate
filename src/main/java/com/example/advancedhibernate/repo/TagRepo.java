package com.example.advancedhibernate.repo;

import com.example.advancedhibernate.db.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
}
