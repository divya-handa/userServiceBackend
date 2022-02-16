package com.nagp.devops.user.persistence;

import com.nagp.devops.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
