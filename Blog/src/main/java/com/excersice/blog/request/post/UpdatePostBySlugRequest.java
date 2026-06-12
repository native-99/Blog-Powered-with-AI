package com.excersice.blog.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostBySlugRequest {

    @NotBlank(message = "title is required")
    @Size(min = 2, message = "minimal 2 characters")
    private String title;

    @NotBlank(message = "body is required")
    @Size(min = 4, message = "minimal 4 characters")
    private String body;

    @NotBlank(message = "slug is required")
    @Size(min = 4, message = "minimal 4 characters")
    private String slug;
}
