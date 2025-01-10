package me.yourcaryourway.YourCarYourWay_WebAPI.dtos.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.ApiResponseDto;

@Data
@AllArgsConstructor
public class SuccessResponseDto implements ApiResponseDto {
    private String message;
}
