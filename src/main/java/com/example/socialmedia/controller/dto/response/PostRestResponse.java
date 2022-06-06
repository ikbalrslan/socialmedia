package com.example.socialmedia.controller.dto.response;

import com.example.socialmedia.model.PostDTO;
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
public class PostRestResponse {

    List<PostDTO> posts;
}
