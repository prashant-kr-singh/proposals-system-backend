package com.soprabanking.ips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprabanking.ips.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
