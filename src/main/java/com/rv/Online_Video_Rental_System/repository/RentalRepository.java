package com.rv.Online_Video_Rental_System.repository;

import com.rv.Online_Video_Rental_System.entity.Rental;
import com.rv.Online_Video_Rental_System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental,String> {
    Long countByUserAndReturnedFalse(User user);
    List<Rental> findByUser(User user);
    List<Rental> findByUserAndReturnedFalse(User user);
}
