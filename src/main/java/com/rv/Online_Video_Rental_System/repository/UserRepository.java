package com.rv.Online_Video_Rental_System.repository;

import com.rv.Online_Video_Rental_System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
