package com.example.socialmedia.model;

import lombok.*;

/**
 * @author İkbal Arslan
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String userName;
    private String fullName;
    private String profilePicture;
    private boolean followed;
}
