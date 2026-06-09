package com.excersice.blog.mapper;

import com.excersice.blog.entity.Post;
import com.excersice.blog.request.CreatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post map(CreatePostRequest postRequest);

    @Mapping(source = "slug" , target = "path")
    CreatePostRequest map(Post post);
}