package com.rv.Online_Video_Rental_System.repository;

import com.rv.Online_Video_Rental_System.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,String> {
}
