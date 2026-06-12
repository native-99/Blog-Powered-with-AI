package com.excersice.blog.controller;

import com.excersice.blog.request.post.CreatePostRequest;
import com.excersice.blog.request.post.GetPostBySlugRequest;
import com.excersice.blog.request.post.UpdatePostBySlugRequest;
import com.excersice.blog.response.post.CreatePostResponse;
import com.excersice.blog.response.post.DeletePostByIdResponse;
import com.excersice.blog.response.post.GetPostResponse;
import com.excersice.blog.response.post.PublishPostResponse;
import com.excersice.blog.response.post.UpdatePostBySlugResponse;
import com.excersice.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;


    @GetMapping("/")
    public List<GetPostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public GetPostResponse getPostBySlug(@PathVariable String slug) {
        GetPostBySlugRequest request = new GetPostBySlugRequest();
        request.setSlug(slug);

        return postService.getPostBySlug(request);
    }

    @PostMapping("/")
    public CreatePostResponse createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        return postService.createPost(createPostRequest);
    }

    @PutMapping("/{slug}")
    public UpdatePostBySlugResponse updatePostBySlug(
            @PathVariable String slug,
            @Valid @RequestBody UpdatePostBySlugRequest request
    ) {
        return postService.updatePostBySlug(slug, request);
    }

    @DeleteMapping("/{id}")
    public DeletePostByIdResponse deletePostById(@PathVariable Integer id) {
        return postService.deletePostById(id);
    }

    @PostMapping("/{id}/publish")
    public PublishPostResponse publishPost(@PathVariable Integer id) {
        return postService.publishPost(id);
    }
}
