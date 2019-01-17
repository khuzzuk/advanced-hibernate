package com.example.advancedhibernate.post

import com.example.advancedhibernate.db.Post
import com.example.advancedhibernate.db.Tag
import com.example.advancedhibernate.repo.PostRepo
import com.example.advancedhibernate.repo.TagRepo
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
@AutoConfigureEmbeddedDatabase
class PostServiceImplSpec extends Specification {
    @Autowired
    PostService postService
    @Autowired
    PostRepo postRepo
    @Autowired
    TagRepo tagRepo

    def "check managed entity behaviour"() {
        given:
        Post post = new Post()
        post.post = 'Post1'
        postRepo.save(post)

        when:
        postService.explicitUpdate(post.id, 'Post2')

        def result = postRepo.findById(post.id).get()

        then:
        result.post == 'Post2'
    }

    def "check cascade mess"() {
        given:
        Post post = createPost()
        Tag tag1 = createTag('Tag1')
        Tag tag2 = createTag('Tag2')
        Tag tag3 = createTag('Tag3')

        when:
        postRepo.save(post)
        tagRepo.saveAll([tag1, tag2, tag3] as List)

        def foundPost = postRepo.findById(post.id).get()
        foundPost.tags = [tag1] as Set
        postRepo.save(foundPost)

        foundPost = postRepo.findById(post.id).get()
        foundPost.tags.add(tag2)
        postRepo.save(foundPost)

        foundPost = postRepo.findById(post.id).get()
        foundPost.tags.remove(tag2)
        postRepo.save(foundPost)

        foundPost = postRepo.findById(post.id).get()
        foundPost.tags.add(tag2)
        postRepo.save(foundPost)

        foundPost = postRepo.findById(post.id).get()

        then:
        foundPost.tags.size() == 2
    }

    private static Post createPost(String value = 'Post') {
        Post post = new Post()
        post.post = value
        post
    }

    private static Tag createTag(String value = 'Tag') {
        Tag tag = new Tag()
        tag.name = value
        tag
    }
}
