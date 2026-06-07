package com.excersice.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    private Integer id;
    private String name;
    private String email;
    private Integer postId;
    private String body;
    private Long createdAt;

}