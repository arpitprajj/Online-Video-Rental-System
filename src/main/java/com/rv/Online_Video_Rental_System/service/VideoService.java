package com.rv.Online_Video_Rental_System.service;

import com.rv.Online_Video_Rental_System.entity.User;
import com.rv.Online_Video_Rental_System.entity.Video;
import com.rv.Online_Video_Rental_System.exception.ResourceNotFoundException;
import com.rv.Online_Video_Rental_System.repository.UserRepository;
import com.rv.Online_Video_Rental_System.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private UserRepository userRepository;

    public Video createVideo(Video video) {


        if (!video.isAvailability()) {
            video.setAvailability(true); // default available
        }

        return videoRepository.save(video);
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Cacheable(value = "video")
    public Optional<Video> getVideoById(String id) {
        try {
            return videoRepository.findById(id);
        }
        catch (RuntimeException ex) {
            throw new ResourceNotFoundException("Video not available :" + id);
        }

    }

    @CachePut(value = "video")
    public Video updateVideo(String id, Video updatedVideo) {

        Video existingVideo = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found"));

        existingVideo.setTitle(updatedVideo.getTitle());
        existingVideo.setGenre(updatedVideo.getGenre());
        existingVideo.setDirector(updatedVideo.getDirector());
        existingVideo.setAvailability(updatedVideo.isAvailability());

        return videoRepository.save(existingVideo);
    }

    @CacheEvict(value="video")
    public void deleteVideo(String id) {

        if (!videoRepository.existsById(id)) {
            throw new RuntimeException("Video not found");
        }

        videoRepository.deleteById(id);
    }

    @Cacheable(value = "video")
    public List<Video>searchByTitle(String title){
        return videoRepository.findByTitleContainingIgnoreCase(title);
    }

}