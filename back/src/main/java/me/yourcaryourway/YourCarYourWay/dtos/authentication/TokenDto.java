package me.yourcaryourway.YourCarYourWay.dtos.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay.dtos.ApiResponseDto;

@Data
@AllArgsConstructor
public class TokenDto implements ApiResponseDto {
    private String token;
}
