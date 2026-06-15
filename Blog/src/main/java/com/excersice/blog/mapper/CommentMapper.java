package com.excersice.blog.mapper;

import com.excersice.blog.entity.Comment;
import com.excersice.blog.request.comment.CreateCommentRequest;
import com.excersice.blog.response.comment.CreateCommentResponse;
import com.excersice.blog.response.comment.GetCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public  interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    Comment map(CreateCommentRequest request);

    @Mapping(source = "post.slug", target = "postSlug")
    CreateCommentResponse mapToCreateCommentResponse(Comment comment);

    @Mapping(source = "post.slug", target = "postSlug")
    GetCommentResponse mapToGetCommentResponse(Comment comment);

    List<GetCommentResponse> mapToGetCommentResponses(List<Comment> comments);
}
