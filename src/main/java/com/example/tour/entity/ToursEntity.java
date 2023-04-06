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
@Table(name ="tour")
public class ToursEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourId;

    private String tourName;
    private String startDate;
    private String duration; // khoang thoi gian
    private String description;
    private Long priceAdult;
    private Long priceChildren;
    private Long priceInfant;
    private int status;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId")
    private AccountsEntity account;

    @OneToMany(mappedBy = "tour")
    private List<ReviewsEntity> lstReview;

    @OneToMany(mappedBy = "tour")
    private List<DestinationsEntity> lstDestination;

    @OneToMany(mappedBy = "tour")
    private List<BookingEntity> lstBooking;

    @OneToMany(mappedBy = "tour")
    private List<MultipleTypeTourEntity> lstMulTypeTour;




}
