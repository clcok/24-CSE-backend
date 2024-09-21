package com.parkingmate.__CSE.repository;

import com.parkingmate.__CSE.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameOrName(String userName, String name);

    User findUserById(Long id);
}
