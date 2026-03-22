package com.rv.Online_Video_Rental_System.controller;

import com.rv.Online_Video_Rental_System.entity.Video;
import com.rv.Online_Video_Rental_System.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VideoController {

    @Autowired
    private VideoService videoService;


    @PostMapping("/admin/videos")
    public ResponseEntity<?> createVideo(@RequestBody Video video) {
        Video savedVideo = videoService.createVideo(video);
        return new ResponseEntity<>(savedVideo, HttpStatus.CREATED);
    }


    @GetMapping("/videos")
    public ResponseEntity<?> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();

        if (videos.isEmpty()) {
            return new ResponseEntity<>("No videos found", HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(videos);
    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<?> getVideoById(@PathVariable String id) {

        Optional<Video> video = videoService.getVideoById(id);

        if (video.isEmpty()) {
            return new ResponseEntity<>("Video not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(video.get());
    }


    @PutMapping("/admin/videos/{id}")
    public ResponseEntity<?> updateVideo(@PathVariable String id,
                                         @RequestBody Video video) {

        try {
            Video updatedVideo = videoService.updateVideo(id, video);
            return ResponseEntity.ok(updatedVideo);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admin/videos/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable String id) {

        try {
            videoService.deleteVideo(id);
            return ResponseEntity.ok("Video deleted successfully");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}