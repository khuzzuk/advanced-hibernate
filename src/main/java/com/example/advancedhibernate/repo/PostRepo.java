package com.example.advancedhibernate.repo;

import com.example.advancedhibernate.db.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

@Transactional(isolation = Isolation.SERIALIZABLE)
public interface PostRepo extends JpaRepository<Post, Long> {
    @Override
    @Lock(LockModeType.WRITE)
    <S extends Post> S save(S entity);

    @Override
    @Lock(LockModeType.WRITE)
    List<Post> findAll();
}
