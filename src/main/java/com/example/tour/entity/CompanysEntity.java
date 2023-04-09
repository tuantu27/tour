package com.example.tour.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name ="company")
public class CompanysEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private String companyName;
    private String address;
    private String email;

    @OneToOne(mappedBy = "company")
    private AccountsEntity accountsEntity;

    @OneToMany(mappedBy = "company")
    private List<PaymentsEntity> lstPayment;
}
