package com.codeup.springblog.models;


import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    public Post() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Post(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String description;


}
