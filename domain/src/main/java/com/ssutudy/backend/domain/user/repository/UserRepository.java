package com.ssutudy.backend.domain.user.repository;

import com.ssutudy.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {}
