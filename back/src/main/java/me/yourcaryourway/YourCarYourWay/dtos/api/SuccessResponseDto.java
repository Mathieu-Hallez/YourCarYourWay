package me.yourcaryourway.YourCarYourWay.dtos.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay.dtos.ApiResponseDto;

@Data
@AllArgsConstructor
public class SuccessResponseDto implements ApiResponseDto {
    private String message;
}
