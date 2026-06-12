package com.excersice.blog.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublishPostResponse {

    private Integer id;
    private String title;
    private String slug;
    private Boolean published;
    private Long publishedAt;
}
