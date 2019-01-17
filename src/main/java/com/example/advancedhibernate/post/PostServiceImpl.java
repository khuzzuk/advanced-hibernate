package com.example.advancedhibernate.post;

import com.example.advancedhibernate.db.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@AllArgsConstructor
@Service
@Transactional
public class PostServiceImpl implements PostService {
    private EntityManager entityManager;

    @Override
    public void explicitUpdate(Long id, String post) {
        Post toUpdate = entityManager.createQuery("FROM Post WHERE id = :id", Post.class)
                .setParameter("id", id)
                .getSingleResult();

        toUpdate.setPost(post);

        if (isValid(toUpdate)) {
            entityManager.persist(toUpdate);
        }
    }

    private boolean isValid(Post post) {
        return true;
    }
}
