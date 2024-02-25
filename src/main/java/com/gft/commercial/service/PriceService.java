package com.gft.commercial.service;

import com.gft.commercial.swagger.dto.PriceDto;

public interface PriceService {

    PriceDto getPrice(String brandId, String productId, String date);

}
