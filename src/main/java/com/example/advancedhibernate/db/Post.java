package com.example.advancedhibernate.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "posting")
public class Post {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posting.post_id_seq")
    @Id
    private Long id;
    @Version
    private Long version;
    private String post;
}
