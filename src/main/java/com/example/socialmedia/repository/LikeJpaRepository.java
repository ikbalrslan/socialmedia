package com.example.socialmedia.repository;

import com.example.socialmedia.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeJpaRepository extends JpaRepository<Like, Integer> {

    @Query("select l from Like l where l.post.id = :post_id")
    Like findLikeByPostId(@Param("post_id") int postId);
}
