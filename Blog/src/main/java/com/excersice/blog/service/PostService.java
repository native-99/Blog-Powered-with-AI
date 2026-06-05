package com.excersice.blog.service;

import com.excersice.blog.entity.Post;
import com.excersice.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
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
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }


    public Post getPostBySlug(String slug) {
        /*
        BEFORE - cari data dari List manual

        return posts.stream()
                .filter(post -> post.getSlug().equals(slug))
                .findFirst()
                .orElse(null);
        */

        // AFTER - cari data dari database berdasarkan slug

        return postRepository.findBySlug(slug).orElse(null);
    }


    public Post createPost(Post post) {
        /*
        BEFORE - tambah data ke List manual

        posts.add(post);
        return post;
        */

        // AFTER - simpan data ke database
        post.setCreatedAt(Instant.now().getEpochSecond());
        return postRepository.save(post);
    }


    public Post updatePostBySlug(String slug, Post sentPostByUser) {
        /*
        BEFORE - cari post dari List manual

        Post savedPost = posts.stream()
                .filter(post -> post.getSlug().equals(slug))
                .findFirst()
                .orElse(null);
        */

        // AFTER - cari post dari database berdasarkan slug
        Post savedPost = postRepository.findBySlug(slug).orElse(null);

        if (savedPost == null) {
            return null;
        }

        savedPost.setTitle(sentPostByUser.getTitle());
        savedPost.setSlug(sentPostByUser.getSlug());

        /*
        BEFORE - cukup return object karena data hanya di memory

        return savedPost;
        */

        // AFTER - simpan ulang perubahan ke database
        return postRepository.save(savedPost);
    }


    public boolean deletePostById(Integer id) {
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
            return false;
        }

        /*
        BEFORE - hapus dari List manual

        posts.remove(savedPost);
        */

        // AFTER - hapus dari database
        postRepository.delete(savedPost);
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
        savedPost.setPublishedAt(Instant.now().getEpochSecond());
        savedPost.setPublished(true);

        /*
        BEFORE - cukup return object karena data hanya di memory

        return savedPost;
        */

        // AFTER - simpan status published ke database
        return postRepository.save(savedPost);
    }
}