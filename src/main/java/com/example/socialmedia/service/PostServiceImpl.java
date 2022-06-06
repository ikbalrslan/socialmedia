package com.example.socialmedia.service;

import com.example.socialmedia.model.Post;
import com.example.socialmedia.model.PostDTO;
import com.example.socialmedia.model.User;
import com.example.socialmedia.model.UserDTO;
import com.example.socialmedia.repository.FollowJpaRepository;
import com.example.socialmedia.repository.LikeJpaRepository;
import com.example.socialmedia.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author İkbal Arslan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostJpaRepository postJpaRepository;
    private final LikeJpaRepository likeJpaRepository;
    private final FollowJpaRepository followJpaRepository;

    @Override
    @Transactional
    public List<PostDTO> getPosts(int userId, List<Integer> postIds) {
        List<Post> posts = new ArrayList<>();
        postIds.forEach(postId -> {
            final var post = postJpaRepository.findPostById(postId);
            if (post != null) {
                posts.add(post);
            }
        });
        final var PostDTO = convertToPostDTOs(userId, posts);
        return PostDTO;
    }

    private List<PostDTO> convertToPostDTOs(int userId, List<Post> posts) {
        List<PostDTO> postDTOs = new ArrayList<>();
        posts.forEach(post -> {
            boolean isLiked = false;
            final var like = likeJpaRepository.findLikeByPostId(post.getId());
            if (like != null && like.getUser().getId() == userId) {
                isLiked = true;
            }
            postDTOs.add(PostDTO.builder()
                    .id(post.getId())
                    .description(post.getDescription())
                    .owner(buildUser(userId, post.getUser()))
                    .image(post.getImage())
                    .createdAt(post.getCreated_at())
                    .liked(isLiked)
                    .build()
            );
        });
        return postDTOs;
    }

    private UserDTO buildUser(int userId, User user){
        boolean isFollowed = false;
        final var follow = followJpaRepository.findFollowByPostIdAndUserId(userId, user.getId());

        if(follow != null){
            isFollowed= true;
        }
        return UserDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .fullName(user.getFullName())
                .profilePicture(user.getProfilePicture())
                .followed(isFollowed)
                .build();
    }
}
