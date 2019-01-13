package com.example.advancedhibernate


import spock.lang.Specification

import javax.persistence.Persistence

class CPSpec extends Specification {
    int count = 500000

    def "check connection pool vibur"() {
        given:
        def factory = Persistence.createEntityManagerFactory("jndi.vibur.example")

        when:
        def entityManager
        for (int i = 0; i < count; i++) {
            entityManager = factory.createEntityManager()
            entityManager.getTransaction().begin()
            entityManager.getTransaction().commit()
            entityManager.close()
        }

        then:
        entityManager
    }

    def "check without connection pool"() {
        given:
        def factory = Persistence.createEntityManagerFactory("jndi.clean.example")

        when:
        def entityManager
        for (int i = 0; i < count; i++) {
            entityManager = factory.createEntityManager()
            entityManager.getTransaction().begin()
            entityManager.getTransaction().commit()
            entityManager.close()
        }

        then:
        entityManager
    }
}
