package com.soprabanking.ips.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.soprabanking.ips.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
