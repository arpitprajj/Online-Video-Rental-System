package com.rv.Online_Video_Rental_System.service;

import com.rv.Online_Video_Rental_System.entity.Rental;
import com.rv.Online_Video_Rental_System.entity.User;
import com.rv.Online_Video_Rental_System.entity.Video;
import com.rv.Online_Video_Rental_System.exception.RentalException;
import com.rv.Online_Video_Rental_System.exception.ResourceNotFoundException;
import com.rv.Online_Video_Rental_System.repository.RentalRepository;
import com.rv.Online_Video_Rental_System.repository.UserRepository;
import com.rv.Online_Video_Rental_System.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VideoRepository videoRepository;

    public Rental rentVideo(String id,String name){
        User user=userRepository.findByEmail(name);
        if(user==null) throw new ResourceNotFoundException("User not Found");
        long count=rentalRepository.countByUserAndReturnedFalse(user);
        if(count>=2) throw new RentalException("U already rented 2 videos. To Rent More please return the previous one. U can rent max 2.");
        Video video=videoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Video Not Found"));
        if(!video.isAvailability()) throw new ResourceNotFoundException("Video is not Available");
        Rental rental=new Rental();
        rental.setVideo(video);
        rental.setUser(user);
        rental.setRentedAt(LocalDateTime.now());
        rental.setReturned(false);
        rentalRepository.save(rental);
        video.setAvailability(false);
        videoRepository.save(video);
        return rental;
    }

    public Rental returnVideo(String rentalId){
       Rental rental=rentalRepository.findById(rentalId).orElseThrow(()->new ResourceNotFoundException("No such Rental Exist"));
       if(rental.isReturned()) throw new RentalException("This Video is already returned");
       rental.setReturned(true);
       Video video=rental.getVideo();
       video.setAvailability(true);
       videoRepository.save(video);
       rentalRepository.save(rental);

       return rental;
    }
    public List<Rental> getAllRentals(String name){
        User user =userRepository.findByEmail(name);
        if(user==null) throw new ResourceNotFoundException("User not Found");
        return rentalRepository.findByUser(user);

    }
    public List<Rental> getActiveRentals(String name){
        User user=userRepository.findByEmail(name);
        if(user==null) throw new ResourceNotFoundException("user Not Found");
        return rentalRepository.findByUserAndReturnedFalse(user);
    }


}
