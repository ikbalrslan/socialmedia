package com.example.socialmedia.service;

import com.example.socialmedia.model.*;
import com.example.socialmedia.repository.FollowJpaRepository;
import com.example.socialmedia.repository.LikeJpaRepository;
import com.example.socialmedia.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Transactional(readOnly = true)
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

    @Override
    @Transactional(readOnly = true)
    public List<MergedPostDTO> mergePostsLists(List<List<Integer>> listOfPosts) {
        HashMap<Integer, Post> postList = new HashMap<>();
        listOfPosts.forEach(posts -> {
            posts.forEach(postId -> {
                final var post = postJpaRepository.findPostById(postId);
                if (post != null && !postList.keySet().contains(postId))
                    postList.put(postId, post);
            });
        });
        final var posts = sortByDateAndId(postList);
        return convertToMergedPostDTOs(posts);
    }

    private HashMap<Integer, Post> sortByDateAndId(HashMap<Integer, Post> posts) {
        List<Map.Entry<Integer, Post>> list =
                new LinkedList<>(posts.entrySet());

        Collections.sort(list, Comparator.comparing(o -> (o.getValue().getCreated_at())));

        HashMap<Integer, Post> tempMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Post> post : list) {
            tempMap.put(post.getKey(), post.getValue());
        }
        return tempMap;
    }

    private List<MergedPostDTO> convertToMergedPostDTOs(HashMap<Integer, Post> posts) {
        List<MergedPostDTO> postDTOs = new ArrayList<>();
        posts.values().forEach(post -> {
            postDTOs.add(MergedPostDTO.builder()
                    .id(post.getId())
                    .description(post.getDescription())
                    .image(post.getImage())
                    .createdAt(post.getCreated_at())
                    .build()
            );
        });
        return postDTOs;
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
