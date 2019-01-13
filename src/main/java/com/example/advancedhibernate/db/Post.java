package com.example.advancedhibernate.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "posting")
public class Post {
    @SequenceGenerator(name = "post_id_gen", schema = "posting", sequenceName = "post_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
    @Id
    private Long id;
    @Version
    private Long version;
    private String post;
}
