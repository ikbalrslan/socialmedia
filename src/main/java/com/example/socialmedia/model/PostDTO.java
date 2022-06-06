package com.example.socialmedia.model;

import lombok.*;

import java.util.Date;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private int id;
    private String description;
    private UserDTO owner;
    private String image;
    private Date createdAt;
    private boolean liked;
}
