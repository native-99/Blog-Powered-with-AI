package com.excersice.blog.repository;

import com.excersice.blog.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    Optional<Post> findFirstBySlug(String slug);

    Optional<Post> findFirstBySlugAndIsDeleted(String slug, boolean isDeleted);

    List<Post> findAllByIsDeleted(boolean isDeleted);
}