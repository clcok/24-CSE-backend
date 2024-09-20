package com.parkingmate.__CSE.repository;

import com.parkingmate.__CSE.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
