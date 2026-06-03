package com.excersice.blog.service;

import com.excersice.blog.entity.Post;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    Post post1 = new Post(1, "title1", "slug1");
    Post post2 = new Post(2, "title2", "slug2");

    @Getter
    List<Post> posts = new ArrayList<Post>(Arrays.asList(post1, post2));

    public Post getPostBySlug(String slug) {
        return posts.stream()
                .filter(post -> post.getSlug().equals(slug))
                .findFirst()
                .orElse(null);
    }

    public Post createPost(Post post) {
        posts.add(post);
        return post;
    }

    public Post updatePostBySlug(String slug, Post sentPostByUser) {
        Post savedPost = posts.stream()
                .filter(post -> post.getSlug().equals(slug))
                .findFirst()
                .orElse(null);

        if (savedPost == null) {
            return null;
        }

        savedPost.setTitle(sentPostByUser.getTitle());
        savedPost.setSlug(sentPostByUser.getSlug());

        return savedPost;
    }

    public boolean deletePostById(Integer id) {
        Post savedPost = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (savedPost == null) {
            return false;
        }

        posts.remove(savedPost);
        return true;
    }

    public Post publishPost(Integer id) {
        Post savedPost = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (savedPost == null) {
            return null;
        }

        savedPost.setPublished(true);
        return savedPost;
    }
}