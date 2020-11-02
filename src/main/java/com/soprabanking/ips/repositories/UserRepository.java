package com.soprabanking.ips.repositories;


import com.soprabanking.ips.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import com.soprabanking.ips.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Team getById(long id);
}
