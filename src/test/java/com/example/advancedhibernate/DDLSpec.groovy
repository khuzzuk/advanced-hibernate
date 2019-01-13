package com.example.advancedhibernate

import com.example.advancedhibernate.db.Post
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.tool.hbm2ddl.SchemaExport
import org.hibernate.tool.schema.TargetType
import spock.lang.Specification

class DDLSpec extends Specification {
    def "generate ddl sql"() {
        given:
        Map<String, String> settings = new HashMap<>()
        settings.put("connection.driver_class", "com.postgresql.Driver")
        settings.put("dialect", "org.hibernate.dialect.PostgreSQL9Dialect")
        settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/posts")
        settings.put("hibernate.connection.username", "post")
        settings.put("hibernate.connection.password", "post");
        settings.put("hibernate.hbm2ddl.auto", "validate");
        settings.put("show_sql", "true");

        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySettings(settings)
                        .build())
        metadata.addAnnotatedClass(Post)
        SchemaExport schemaExport = new SchemaExport()
        schemaExport.setHaltOnError(true)
        schemaExport.setFormat(true)
        schemaExport.setDelimiter(";")
//        schemaExport.setOutputFile("db-schema.sql")

        when:
        schemaExport.createOnly(EnumSet.of(TargetType.STDOUT), metadata.buildMetadata())

        then:
        schemaExport
    }
}
