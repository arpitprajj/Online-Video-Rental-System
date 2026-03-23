package com.rv.Online_Video_Rental_System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private String id;
    private String firstName;
    private String lastName;

    @Column(unique = true,nullable = false)
    private String email;
    @JsonIgnore
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Rental> rentals;
}
