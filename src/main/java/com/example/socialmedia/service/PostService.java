package com.example.socialmedia.service;

import com.example.socialmedia.model.Post;
import com.example.socialmedia.model.PostDTO;

import java.util.List;

/**
 * @author İkbal Arslan
 */
public interface PostService {

    List<PostDTO> getPosts(int userId, List<Integer> postIds);
}
