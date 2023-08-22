package com.project.carRental.repos;

import com.project.carRental.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepositories extends JpaRepository<User, Long> {
    //User findByUserName(User userName);
}
