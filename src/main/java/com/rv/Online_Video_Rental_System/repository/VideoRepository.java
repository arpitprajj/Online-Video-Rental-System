package com.rv.Online_Video_Rental_System.repository;

import com.rv.Online_Video_Rental_System.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video,String> {
    List<Video> findByTitleContainingIgnoreCase(String title);
}
