package com.excersice.blog.response.post;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostResponse {

    private Integer id;
    private String title;
    private String body;
    private String slug;
    private Long createdAt;
    private Long commentCount;
}
