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
@Table(name ="Account")
public class AccountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String userName;
    private String passWord;
    private String avatar;
    private String email;
    private int roles;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "account")
    private List<ToursEntity> toursEntityList;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    private CompanysEntity company;

}
