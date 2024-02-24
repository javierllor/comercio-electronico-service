package com.gft.commercial.service;

import com.gft.commercial.swagger.dto.PriceDto;
import java.time.LocalDateTime;

public interface PriceService {

    PriceDto getPrice(Integer brandId, Long productId, LocalDateTime date);

}
