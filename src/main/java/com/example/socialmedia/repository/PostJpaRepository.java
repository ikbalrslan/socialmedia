package com.example.socialmedia.repository;

import com.example.socialmedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.id = :post_id")
    Post findPostById(@Param("post_id") int postId);
}
