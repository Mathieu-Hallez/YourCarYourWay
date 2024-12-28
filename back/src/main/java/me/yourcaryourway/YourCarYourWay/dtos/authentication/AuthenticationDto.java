package me.yourcaryourway.YourCarYourWay.dtos.authentication;

import lombok.Data;

@Data
public class AuthenticationDto {
    private String email;
    private String password;
}
