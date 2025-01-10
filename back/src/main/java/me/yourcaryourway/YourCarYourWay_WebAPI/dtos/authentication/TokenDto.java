package me.yourcaryourway.YourCarYourWay_WebAPI.dtos.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.ApiResponseDto;

@Data
@AllArgsConstructor
public class TokenDto implements ApiResponseDto {
    private String token;
}
