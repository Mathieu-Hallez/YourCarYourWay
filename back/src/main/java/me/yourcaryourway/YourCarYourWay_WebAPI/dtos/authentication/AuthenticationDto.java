package me.yourcaryourway.YourCarYourWay_WebAPI.dtos.authentication;

import lombok.Data;

@Data
public class AuthenticationDto {
    private String email;
    private String password;
}
