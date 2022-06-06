package com.example.socialmedia.repository;

import com.example.socialmedia.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowJpaRepository extends JpaRepository<Follow, Integer> {

    @Query("select f from Follow f where f.follower.id = :user_id and f.following.id = :post_user_id")
    Follow findFollowByPostIdAndUserId(@Param("user_id") int userId, @Param("post_user_id") int postUserId);
}
