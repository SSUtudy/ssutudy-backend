package com.ssutudy.backend.user.repository;

import com.ssutudy.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {}
