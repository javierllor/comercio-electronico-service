package com.gft.commercial.controller;

import com.gft.commercial.service.PriceService;
import com.gft.commercial.swagger.api.PricesApi;
import com.gft.commercial.swagger.dto.PriceDto;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PriceController implements PricesApi {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public ResponseEntity<PriceDto> getPrice(Integer brandId, Long productId, LocalDateTime date) {
        logger.debug("In getPrice controller with brandId: {}, productId: {} and date: {}",
                brandId, productId, date);
        return ResponseEntity.ok(priceService.getPrice(brandId, productId, date));
    }
}
