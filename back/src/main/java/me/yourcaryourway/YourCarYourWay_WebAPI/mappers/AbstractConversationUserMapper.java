package me.yourcaryourway.YourCarYourWay_WebAPI.mappers;

import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.user.ConversationUserDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AbstractConversationUserMapper implements EntityMapper<ConversationUserDto, User>{
}
