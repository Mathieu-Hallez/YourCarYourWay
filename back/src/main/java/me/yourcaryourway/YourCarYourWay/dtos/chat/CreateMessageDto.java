package me.yourcaryourway.YourCarYourWay.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateMessageDto extends AbstractMessageDto{}

