package me.yourcaryourway.YourCarYourWay.dtos.user;

import lombok.Data;
import me.yourcaryourway.YourCarYourWay.dtos.ApiResponseDto;

@Data
public class ConversationUserDto implements ApiResponseDto {
    private String lastname;
    private String firstname;
    private String email;
}
