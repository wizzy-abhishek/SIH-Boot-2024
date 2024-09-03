package com.hospital.hospital.model.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
@Entity
public class UserDb {

    @Id()
    private String userName ;

    @Column(length = 18)
    private String password ;

    @Column(length = 10 , nullable = true)
    private List<String> authority ;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuthority() {
        return authority;
    }

    public void setAuthority(List<String> authority) {
        this.authority = authority;
    }

}
