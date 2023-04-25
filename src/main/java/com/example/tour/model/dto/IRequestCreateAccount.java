package com.example.tour.model.dto;
import com.example.tour.entity.CompanysEntity;
import com.example.tour.entity.RoleEntity;
import com.example.tour.entity.ToursEntity;
import java.util.Date;
import java.util.List;

public interface IRequestCreateAccount {

    public Long getAccountId();
    public void setAccountId(Long accountId);
    public String getUsername();

    public void setUsername(String username) ;
    public String getPassword();
    public void setPassword(String password);
    public String getAvatar() ;

    public void setAvatar(String avatar);

    public String getEmail() ;

    public void setEmail(String email) ;
    public Date getCreateDate() ;

    public void setCreateDate(Date createDate);

    public Date getUpdateDate() ;

    public void setUpdateDate(Date updateDate) ;

    public List<ToursEntity> getToursEntityList();

    public void setToursEntityList(List<ToursEntity> toursEntityList);

    public CompanysEntity getCompany() ;

    public void setCompany(CompanysEntity company);

    public List<RoleEntity> getRoles() ;
    public void setRoles(List<RoleEntity> roles);
}
