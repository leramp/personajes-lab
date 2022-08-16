package com.alkemy.personajes.personajes.auth.entity;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;


@Entity
@Table(name="user")
public class UserEntity /*implements UserDetails*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@Email
    private String username;
    //@Size(min=8)
    private String password;
    //    private boolean accountNonExpired;
//    private boolean accountNonLocked;
//    private boolean credentialsNonExpired;
//    private boolean enabled;
//
//    public UserEntity(){
//        this.accountNonExpired = true;
//        this.accountNonLocked = true;
//        this.credentialsNonExpired = true;
//        this.enabled = true;
//    }
    public Long getId() {return id;}
    public void setId(Long id){this.id=id;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}



}

