package com.example.tour.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public class AccountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column(unique = true)
    @NotEmpty(message = "{not.empty}")
    private String username;
    @NotEmpty(message = "{not.empty}")

    private String password;
    private String avatar;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date updateDate;

    @OneToMany(mappedBy = "account")
    private List<ToursEntity> toursEntityList;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    private CompanysEntity company;
    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Role> roles;

}
