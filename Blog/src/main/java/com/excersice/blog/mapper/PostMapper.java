package com.excersice.blog.mapper;

import com.excersice.blog.entity.Post;
import com.excersice.blog.request.post.CreatePostRequest;
import com.excersice.blog.response.post.CreatePostResponse;
import com.excersice.blog.response.post.GetPostResponse;
import com.excersice.blog.response.post.PublishPostResponse;
import com.excersice.blog.response.post.UpdatePostBySlugResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post map(CreatePostRequest postRequest);

    CreatePostResponse mapToCreatePostResponse(Post post);

    GetPostResponse mapToGetPostResponse(Post post);

    List<GetPostResponse> mapToGetPostResponses(List<Post> posts);

    UpdatePostBySlugResponse mapToUpdatePostBySlugResponse(Post post);

    PublishPostResponse mapToPublishPostResponse(Post post);
}
