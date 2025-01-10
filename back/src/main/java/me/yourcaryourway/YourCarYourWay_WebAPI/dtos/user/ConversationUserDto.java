package me.yourcaryourway.YourCarYourWay_WebAPI.dtos.user;

import lombok.Data;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.ApiResponseDto;

@Data
public class ConversationUserDto implements ApiResponseDto {
    private String lastname;
    private String firstname;
    private String email;
}
