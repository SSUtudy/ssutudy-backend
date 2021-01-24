package com.ssutudy.backend.user.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id
    @GeneratedValue
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
}
