package com.example.tour.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name ="destination")
public class DestinationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;
    private String title;
    private String content;
    private String urlImage;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tourId")
    private ToursEntity tour;


}
