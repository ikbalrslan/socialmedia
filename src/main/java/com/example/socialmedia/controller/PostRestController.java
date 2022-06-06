package com.example.socialmedia.controller;

import com.example.socialmedia.controller.dto.request.PostRestRequest;
import com.example.socialmedia.controller.dto.response.PostRestResponse;
import com.example.socialmedia.model.Post;
import com.example.socialmedia.model.PostDTO;
import com.example.socialmedia.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("social/rest")
@RequiredArgsConstructor
public class PostRestController {
    private final PostService postService;

    @GetMapping(value = "posts")
    public ResponseEntity<PostRestResponse> getPosts(@RequestBody PostRestRequest request) {

        final var userId = request.getId();
        final var postIds = request.getPostIds();
        log.info("Request to get posts with userId: {}", userId);
        final var response = new PostRestResponse();
        response.setPosts(postService.getPosts(userId, postIds));

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping(value = "posts")
    public ResponseEntity<PostRestResponse> mergePosts(@RequestBody PostRestRequest request) {

        final var userId = request.getId();
        final var postIds = request.getPostIds();
        log.info("Request to merge posts!");
        final var response = new PostRestResponse();
        response.setPosts(postService.getPosts(userId, postIds));

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
