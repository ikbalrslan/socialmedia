package com.example.socialmedia.controller.dto.response;

import com.example.socialmedia.model.MergedPostDTO;
import lombok.*;

import java.util.List;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MergedPostsRestResponse {

    List<MergedPostDTO> mergedPosts;
}
