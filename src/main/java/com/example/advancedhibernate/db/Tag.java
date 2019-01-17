package com.example.advancedhibernate.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(schema = "posting")
public class Tag extends BaseEntity {
    @SequenceGenerator(name = "tag_id_gen", schema = "posting", sequenceName = "tag_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id_gen")
    @Id
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts;
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private Set<Message> messages;
}
