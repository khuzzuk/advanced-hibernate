package com.example.advancedhibernate.message

import com.example.advancedhibernate.db.Message
import com.example.advancedhibernate.db.Tag
import com.example.advancedhibernate.repo.MessageRepo
import com.example.advancedhibernate.repo.TagRepo
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@AutoConfigureEmbeddedDatabase
@SpringBootTest
class MessageServiceImplSpec extends Specification {
    @Autowired
    MessageRepo messageRepo
    @Autowired
    MessageService messageService
    @Autowired
    TagRepo tagRepo

    def "check many to many set"() {
        given:
        Message message = createMessage()
        Tag tag1 = createTag('Tag1')
        Tag tag2 = createTag('Tag2')
        Tag tag3 = createTag('Tag3')

        when:
        tagRepo.saveAll([tag1, tag2, tag3] as List)
        messageRepo.save(message)

        message.tags = [tag1] as Set
        messageRepo.save(message)

        message.tags.add(tag2)
        messageRepo.save(message)

        message.tags.remove(tag2)
        messageRepo.save(message)

        message.tags.add(tag2)
        messageRepo.save(message)

        message.tags.remove(tag2)
        messageRepo.save(message)

        message.tags.add(tag2)
        messageRepo.save(message)

        def result = messageRepo.findById(message.id).get()

        then:
        result.tags.size() == 2
    }

    def "check many to many remove"() {
        given:
        Message message = createMessage()
        Tag tag1 = createTag('Tag1')
        Tag tag2 = createTag('Tag2')
        Tag tag3 = createTag('Tag3')

        when:
        tagRepo.saveAll([tag1, tag2, tag3] as List)
        messageRepo.save(message)

        message.tags = [tag1] as Set
        messageRepo.save(message)

        message.tags.add(tag2)
        messageRepo.save(message)

        def tagToRemove = tagRepo.findById(tag2.id).get()
        tagRepo.delete(tagToRemove)

        def result = messageRepo.findById(message.id)

        then:
        result.isEmpty()
    }

    private static Message createMessage(String value = 'Content') {
        Message message = new Message()
        message.content = value
        message
    }

    private static Tag createTag(String value = 'Tag') {
        Tag tag = new Tag()
        tag.name = value
        tag
    }
}
