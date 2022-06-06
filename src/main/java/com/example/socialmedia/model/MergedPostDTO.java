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
public class MergedPostDTO {

    private int id;
    private String description;
    private String image;
    private Date createdAt;
}
