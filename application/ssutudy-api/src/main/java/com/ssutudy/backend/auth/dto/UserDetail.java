package com.ssutudy.backend.auth.dto;

import com.ssutudy.backend.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDetail {
    private Integer id;
    private String name;
    private String major;
    private String email;

    public UserDetail(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.major = user.getMajor();
        this.email = user.getEmail();
    }
}
