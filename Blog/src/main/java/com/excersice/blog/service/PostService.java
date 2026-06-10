package com.excersice.blog.service;

import com.excersice.blog.entity.Post;
import com.excersice.blog.mapper.PostMapper;
import com.excersice.blog.repository.PostRepository;
import com.excersice.blog.request.CreatePostRequest;
import com.excersice.blog.response.CreatePostResponse;
import com.excersice.blog.response.GetPostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    /*
    BEFORE - sebelum pakai Repository

    Post post1 = new Post(1, "title1", "slug1");
    Post post2 = new Post(2, "title2", "slug2");

    List<Post> posts = new ArrayList<Post>(Arrays.asList(post1, post2));

    Data masih disimpan di memory/list biasa.
    Kalau aplikasi dimatikan, data hilang.
    */


    public List<Post> getPosts() {
        /*
        BEFORE - pakai List manual

        return posts;
        */

        // AFTER - pakai Repository / database
        return postRepository.findAllByIsDeleted(false);
    }


    public GetPostResponse getPostBySlug(String slug) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(slug, false)
                .orElseThrow(() -> new RuntimeException("not found"));

        return PostMapper.INSTANCE.mapToGetPostResponse(post);
    }



    public CreatePostResponse createPost(CreatePostRequest request) {
        Post post = PostMapper.INSTANCE.map(request);
        post.setCommentCount(0L);
        post.setCreatedAt(Instant.now().getEpochSecond());
        post = postRepository.save(post);

        return PostMapper.INSTANCE.mapToCreatePostResponse(post);
    }


    public Post updatePostBySlug(String slug, Post sentPostByUser) {
        Post savedPost = postRepository.findFirstBySlugAndIsDeleted(slug, false).orElse(null);

        if (savedPost == null) {
            return null;
        }

        savedPost.setTitle(sentPostByUser.getTitle());
        savedPost.setBody(sentPostByUser.getBody());
        savedPost.setSlug(sentPostByUser.getSlug());

        return postRepository.save(savedPost);
    }


    public boolean deletePostById(Integer id) {
        Post savedPost = postRepository.findById(id).orElse(null);

        if (savedPost == null) {
            return false;
        }

        savedPost.setDeleted(true);
        postRepository.save(savedPost);

        return true;
    }


    public Post publishPost(Integer id) {
        /*
        BEFORE - cari post dari List manual

        Post savedPost = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
        */

        // AFTER - cari post dari database berdasarkan id

        Post savedPost = postRepository.findById(id).orElse(null);

        if (savedPost == null) {
            return null;
        }
        savedPost.setPublished(true);
        savedPost.setPublishedAt(Instant.now().getEpochSecond());

        /*
        BEFORE - cukup return object karena data hanya di memory

        return savedPost;
        */

        // AFTER - simpan status published ke database
        return postRepository.save(savedPost);
    }
}