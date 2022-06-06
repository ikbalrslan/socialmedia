package com.example.socialmedia.controller.dto.request;

import lombok.*;

import java.util.List;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListOfPostsRestRequest {

    private List<List<Integer>> listOfPosts;
}
