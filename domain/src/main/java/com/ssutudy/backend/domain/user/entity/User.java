package com.ssutudy.backend.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {
    @Id
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String major;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Builder
    public User(String id, String name, String major, String email, String password) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.email = email;
        this.password = password;
    }
}
