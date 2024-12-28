package me.yourcaryourway.YourCarYourWay.mappers;

import me.yourcaryourway.YourCarYourWay.dtos.chat.SaveMessageDto;
import me.yourcaryourway.YourCarYourWay.models.Message;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

public interface EntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDtos(List<E> entities);

}