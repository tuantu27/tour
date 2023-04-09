package com.example.tour.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name ="multipleTypeTour")
public class MultipleTypeTourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long multipleTypeTourId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tourId")
    private ToursEntity tour;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeTourId")
    private TypeTourEntity typeTour;

}
