package com.example.advancedhibernate

import com.example.advancedhibernate.db.Post
import spock.lang.Specification

import javax.persistence.Persistence

class MongoSpec extends Specification {
    def "test mongo save"() {
        given:
        Post toPersist = new Post()
        toPersist.post = 'Post1'

        def entityManagerFactory = Persistence.createEntityManagerFactory('jndi.mongodb.posts')
        def entityManager = entityManagerFactory.createEntityManager()

        when:
        def transaction = entityManager.getTransaction()
        transaction.begin()
        entityManager.persist(toPersist)

        entityManager.flush()
        transaction.commit()
        entityManager.clear()

        transaction = entityManager.getTransaction()
        transaction.begin()
        def results = entityManager.find(Post, toPersist.id)
        transaction.commit()

        then:
        with(results) {
            post == 'Post1'
        }
    }

}
