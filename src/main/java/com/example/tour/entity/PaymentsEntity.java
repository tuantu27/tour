package com.example.tour.entity;

import javax.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name ="payment")
public class PaymentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private String paymentMethod;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "companyId")
    private CompanysEntity company;
}
