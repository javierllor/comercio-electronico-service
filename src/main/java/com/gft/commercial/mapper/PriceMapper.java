package com.gft.commercial.mapper;

import com.gft.commercial.entity.PriceEntity;
import com.gft.commercial.swagger.dto.PriceDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PriceMapper {

    @Mapping(target = "finalPrice", expression = "java(price.getPrice() + \" \" + price.getCurrency())")
    PriceDto mapPriceToPriceDto(PriceEntity price);

}
