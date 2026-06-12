package com.excersice.blog.service;

import com.excersice.blog.entity.Post;
import com.excersice.blog.exception.ApiException;
import com.excersice.blog.mapper.PostMapper;
import com.excersice.blog.repository.PostRepository;
import com.excersice.blog.request.post.CreatePostRequest;
import com.excersice.blog.request.post.GetPostBySlugRequest;
import com.excersice.blog.request.post.UpdatePostBySlugRequest;
import com.excersice.blog.response.post.CreatePostResponse;
import com.excersice.blog.response.post.DeletePostByIdResponse;
import com.excersice.blog.response.post.GetPostResponse;
import com.excersice.blog.response.post.PublishPostResponse;
import com.excersice.blog.response.post.UpdatePostBySlugResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<GetPostResponse> getPosts() {
        List<Post> posts = postRepository.findAllByIsDeleted(false);

        return PostMapper.INSTANCE.mapToGetPostResponses(posts);
    }

    public GetPostResponse getPostBySlug(GetPostBySlugRequest request) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(request.getSlug(), false)
                .orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        return PostMapper.INSTANCE.mapToGetPostResponse(post);
    }


    public CreatePostResponse createPost(CreatePostRequest request) {
        Post post = PostMapper.INSTANCE.map(request);
        post.setCommentCount(0L);
        post.setCreatedAt(Instant.now().getEpochSecond());
        post = postRepository.save(post);

        return PostMapper.INSTANCE.mapToCreatePostResponse(post);
    }


    public UpdatePostBySlugResponse updatePostBySlug(String slug, UpdatePostBySlugRequest request) {
        Post savedPost = postRepository.findFirstBySlugAndIsDeleted(slug, false)
                .orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        savedPost.setTitle(request.getTitle());
        savedPost.setBody(request.getBody());
        savedPost.setSlug(request.getSlug());

        Post updatedPost = postRepository.save(savedPost);

        return PostMapper.INSTANCE.mapToUpdatePostBySlugResponse(updatedPost);
    }




    public DeletePostByIdResponse deletePostById(Integer id) {
        Post savedPost = postRepository.findById(id)
                .orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        savedPost.setDeleted(true);
        Post deletedPost = postRepository.save(savedPost);

        return DeletePostByIdResponse.builder()
                .id(deletedPost.getId())
                .deleted(deletedPost.isDeleted())
                .build();
    }

    public PublishPostResponse publishPost(Integer id) {
        Post savedPost = postRepository.findById(id)
                .orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        savedPost.setPublished(true);
        savedPost.setPublishedAt(Instant.now().getEpochSecond());

        Post publishedPost = postRepository.save(savedPost);

        return PostMapper.INSTANCE.mapToPublishPostResponse(publishedPost);
    }
}
