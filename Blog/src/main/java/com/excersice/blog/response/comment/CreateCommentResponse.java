package com.excersice.blog.response.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentResponse {

    private Integer id;
    private String name;
    private String email;
    private String postSlug;
    private String body;
    private Long createdAt;
}
