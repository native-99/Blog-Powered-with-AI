package com.excersice.blog.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPostResponse {

    private Integer id;
    private String title;
    private String body;
    private String slug;
    private Long createdAt;
}