package com.example.demo.dtos;

import com.example.demo.domains.AppRole;
import com.example.demo.domains.AppUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDto {
    private final AppUser user;

    public UserDto(AppUser user) {
        this.user = user;
    }

    public String getFirstName () {
        return this.user.getFirstName();
    }

    public String getLastName () {
        return this.user.getName();
    }

    public String getName(){
        return this.user.getName() + ' ' + this.user.getFirstName();
    }

    public String getEmail(){
        return this.user.getEmail();
    }

    public List getRole(){
        List list = new ArrayList<>();
        Collection<AppRole> roles = this.user.getRoles();
        for(AppRole role:roles){
            list.add(role.getName());
        }
       return list;
    }
}
