package com.example.tour.entity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="typeTour")
public class TypeTourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long typeTourId;

    private String nameTypeTour;
    private String region; // 1 Mien Bac , 2 Mien Trung ,3 Mien Nam , 4 Tay Nguyen

    @OneToMany(mappedBy = "typeTour")
    private List<MultipleTypeTourEntity> lstMuTypeTour;

}
