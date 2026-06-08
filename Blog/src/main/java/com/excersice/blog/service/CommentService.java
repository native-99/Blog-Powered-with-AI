package com.excersice.blog.service;

import com.excersice.blog.entity.Comment;
import com.excersice.blog.entity.Post;
import com.excersice.blog.repository.CommentRepository;
import com.excersice.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class CommentService {


    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    public Iterable<Comment> getComments(String postSlug, Integer pageNo, Integer limit) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(postSlug,false)
                .orElse(null);

        if(post == null){
            return null;
        }
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        return commentRepository.findByPostId(post.getId(),pageRequest).getContent();
    }

    public Comment getComment(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment createComment(Comment comment) {

        Post post = postRepository.findFirstBySlugAndIsDeleted(comment.getPost().getSlug(), false)
                .orElse(null);
        if(post == null){
            return null;
        }
        comment.setCreatedAt(Instant.now().getEpochSecond());
        comment.setPost(post);
        return commentRepository.save(comment);
    }
}