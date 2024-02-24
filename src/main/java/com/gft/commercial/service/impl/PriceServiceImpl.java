package com.gft.commercial.service.impl;

import com.gft.commercial.entity.PriceEntity;
import com.gft.commercial.exception.ResourceNotFoundException;
import com.gft.commercial.mapper.PriceMapper;
import com.gft.commercial.repository.PricesRepository;
import com.gft.commercial.service.PriceService;
import com.gft.commercial.swagger.dto.PriceDto;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

    private final PricesRepository pricesRepository;
    private final PriceMapper priceMapper;

    public PriceServiceImpl(PricesRepository pricesRepository, PriceMapper priceMapper) {
        this.pricesRepository = pricesRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public PriceDto getPrice(Integer brandId, Long productId, LocalDateTime date) {
        logger.debug("In getPrice service with brandId: {}, productId: {} and date: {}",
                brandId, productId, date);
        Optional<PriceEntity> price = pricesRepository
                .findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        brandId, productId, date, date);
        return price
                .map(priceMapper::mapPriceToPriceDto)
                .orElseThrow(() -> new ResourceNotFoundException("Price"));
//        return pricesRepository
//                .findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
//                        brandId, productId, date, date)
//                .map(priceMapper::mapPriceToPriceDto)
//                .orElseThrow(() -> new ResourceNotFoundException("Price"));
    }
}
