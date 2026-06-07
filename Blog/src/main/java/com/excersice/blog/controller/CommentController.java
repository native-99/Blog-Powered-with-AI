package com.excersice.blog.controller;

import com.excersice.blog.entity.Comment;
import com.excersice.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public Iterable<Comment> getComments(
            @RequestParam(required = false) String postSlug,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        return commentService.getComments(postSlug, pageNo, limit);
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Integer id) {
        return commentService.getComment(id);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }
}