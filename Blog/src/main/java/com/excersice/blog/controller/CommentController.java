package com.excersice.blog.controller;

import com.excersice.blog.request.comment.CreateCommentRequest;
import com.excersice.blog.response.comment.CreateCommentResponse;
import com.excersice.blog.response.comment.GetCommentResponse;
import com.excersice.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<GetCommentResponse> getComments(
            @RequestParam String postSlug,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        return commentService.getComments(postSlug, pageNo, limit);
    }
    @GetMapping("/{id}")
    public GetCommentResponse getComment(@PathVariable Integer id) {
        return commentService.getComment(id);
    }

    @PostMapping
    public CreateCommentResponse createComment(@Valid @RequestBody CreateCommentRequest request) {
        return commentService.createComment(request);
    }
}
