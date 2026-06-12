package com.excersice.blog.service;

import com.excersice.blog.entity.Comment;
import com.excersice.blog.entity.Post;
import com.excersice.blog.exception.ApiException;
import com.excersice.blog.mapper.CommentMapper;
import com.excersice.blog.repository.CommentRepository;
import com.excersice.blog.repository.PostRepository;
import com.excersice.blog.request.comment.CreateCommentRequest;
import com.excersice.blog.response.comment.CreateCommentResponse;
import com.excersice.blog.response.comment.GetCommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;


@Service
public class CommentService {


    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    public List<GetCommentResponse> getComments(String postSlug, Integer pageNo, Integer limit) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(postSlug, false)
                .orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        List<Comment> comments = commentRepository.findByPostId(post.getId(), pageRequest).getContent();

        return CommentMapper.INSTANCE.mapToGetCommentResponses(comments);
    }



    public GetCommentResponse getComment(Integer id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Comment not found", HttpStatus.NOT_FOUND));

        return CommentMapper.INSTANCE.mapToGetCommentResponse(comment);
    }

    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest request) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(request.getPostSlug(), false)
                .orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        Comment comment = CommentMapper.INSTANCE.map(request);
        comment.setPost(post);
        comment.setCreatedAt(Instant.now().getEpochSecond());

        Comment savedComment = commentRepository.save(comment);

        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        return CommentMapper.INSTANCE.mapToCreateCommentResponse(savedComment);
    }
}
