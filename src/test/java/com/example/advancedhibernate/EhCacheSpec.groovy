package com.example.advancedhibernate

import com.example.advancedhibernate.db.Post
import spock.lang.Specification

import javax.persistence.Persistence

class EhCacheSpec extends Specification {
    def "check cache usage"() {
        given:
        def factory = Persistence.createEntityManagerFactory("jndi.ehcache.example")

        Post post = new Post()
        post.post = "Post"

        when:
        def entityManager = factory.createEntityManager()
        entityManager.getTransaction().begin()
        entityManager.persist(post)
        entityManager.getTransaction().commit()

        entityManager.getTransaction().begin()
        def query = entityManager.createQuery('FROM Post WHERE id = :id')
        query.setParameter("id", 1L)
        query.setHint("org.hibernate.cacheable", true)
        List<Post> found = query.getResultList() as List<Post>
        entityManager.getTransaction().commit()

        entityManager.getTransaction().begin()
        def query2 = entityManager.createQuery('FROM Post WHERE id = :id')
        query2.setParameter("id", 1L)
        query2.setHint("org.hibernate.cacheable", true)
        List<Post> found2 = query2.getResultList() as List<Post>
        entityManager.getTransaction().commit()
        def cache = factory.getCache()

        then:
        cache.contains(Post, post.id)
    }
}
