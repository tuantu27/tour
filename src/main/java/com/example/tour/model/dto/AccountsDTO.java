package com.example.tour.model.dto;

import com.example.tour.entity.CompanysEntity;
import com.example.tour.entity.RoleEntity;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class AccountsDTO {
    private Long accountId;
    private String username;
    private String avatar;
    private Date createDate;
    private CompanysEntity company;
    private List<RoleEntity> roles;

}
