package com.example.advancedhibernate.repo

import com.example.advancedhibernate.db.Post
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.orm.ObjectOptimisticLockingFailureException
import spock.lang.Specification

@SpringBootTest
@AutoConfigureEmbeddedDatabase
class PostRepoSpec extends Specification {
    @Autowired
    PostRepo postRepo

    def "check isolation level"() {
        given:
        Post post = new Post()
        post.post = "Post"

        when:
        postRepo.save(post)

        then:
        postRepo.findAll().get(0).post == "Post"
    }

    def "test wrong versioning"() {
        given:
        Post post = new Post()
        post.post = "Post1"
        postRepo.save(post)
        Post found = postRepo.findAll().get(0)
        found.post = "Post2"
        postRepo.save(found)
        postRepo.findAll().get(0).post == "Post2"

        when:
        postRepo.save(post)

        then:
        thrown(ObjectOptimisticLockingFailureException)
    }
}
