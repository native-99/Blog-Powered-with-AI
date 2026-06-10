package com.excersice.blog.mapper;

import com.excersice.blog.entity.Post;
import com.excersice.blog.request.CreatePostRequest;
import com.excersice.blog.response.CreatePostResponse;
import com.excersice.blog.response.GetPostResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post map(CreatePostRequest postRequest);

//    @Mapping(source = "slug" , target = "path")
    CreatePostResponse mapToCreatePostResponse(Post post);

    GetPostResponse mapToGetPostResponse(Post post);
}