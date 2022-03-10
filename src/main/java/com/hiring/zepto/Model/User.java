package com.hiring.zepto.Model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="user")
public class User {

    @Id
    @Column(length = 25)
    private String userName;
    @Column(length = 25)
    @NonNull
    private String userPassword;
    private String userRole;

    public User() {
    }

   
}