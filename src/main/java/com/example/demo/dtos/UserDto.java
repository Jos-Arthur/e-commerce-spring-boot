package com.example.demo.dtos;

import com.example.demo.domains.AppRole;
import com.example.demo.domains.AppUser;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean isEnabled;
    private Boolean isBlocked;
    private Collection<AppRole> roles = new ArrayList<>();

    public String getName() {
        return this.firstName+" "+this.lastName;
    }

    public List getRoles(){
        List list = new ArrayList<>();
        for(AppRole role:this.roles){
            list.add(role.getName());
        }
       return list;
    }
}
