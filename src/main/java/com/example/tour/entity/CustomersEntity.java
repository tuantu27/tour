package com.example.tour.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="Customer")
public class CustomersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String fullName;
    private String phoneNumber;
    private int gender;
    private Timestamp dob; //date of birth
    private String address;


    @OneToMany(mappedBy = "customer")
    private List<ReviewsEntity> lstReview;

    @OneToMany(mappedBy = "customer")
    private List<BookingEntity> lstBooking;


}
