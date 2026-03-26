package com.rv.Online_Video_Rental_System.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue
    private String id;
    @Version
    private Long version;
    private String title;
    private String genre;
    private String director;
    private boolean availability;

}
