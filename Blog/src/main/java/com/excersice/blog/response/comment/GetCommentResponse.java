package com.excersice.blog.response.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentResponse {

    private Integer id;
    private String name;
    private String postSlug;
    private String body;
    private Long createdAt;
}
