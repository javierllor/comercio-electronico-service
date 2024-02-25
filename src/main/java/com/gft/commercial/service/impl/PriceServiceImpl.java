package com.gft.commercial.service.impl;

import com.gft.commercial.exception.ResourceNotFoundException;
import com.gft.commercial.exception.ValidationException;
import com.gft.commercial.mapper.PriceMapper;
import com.gft.commercial.repository.PricesRepository;
import com.gft.commercial.service.PriceService;
import com.gft.commercial.swagger.dto.PriceDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    public PriceDto getPrice(String brandId, String productId, String date) {
        logger.debug("In getPrice service with brandId: {}, productId: {} and date: {}",
                brandId, productId, date);
        fieldsValidation(brandId, productId, date);
        return pricesRepository
                .findPriceEntity(
                        Integer.parseInt(brandId), Long.parseLong(productId), date, date)
                .map(priceMapper::mapPriceToPriceDto)
                .orElseThrow(() -> new ResourceNotFoundException("Price"));
    }

    private void fieldsValidation(String brandId, String productId, String date) throws ValidationException {
        logger.debug("In fieldsValidation service with brandId: {}, productId: {} and date: {}",
                brandId, productId, date);
        List<String> errorMessages = Stream.of(
                        validateBrandId(brandId),
                        validateProductId(productId),
                        validateDate(date)
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (!errorMessages.isEmpty()) {
            throw new ValidationException(String.join(". ", errorMessages));
        }
    }

    private String validateBrandId(String brandId) {
        try {
            Integer.parseInt(brandId);
            return null;
        } catch (NumberFormatException e) {
            logger.error("Invalid Brand Id: {}", brandId);
            return String.format("Wrong Brand Id: %s", e.getMessage());
        }
    }

    private String validateProductId(String productId) {
        try {
            Long.parseLong(productId);
            return null;
        } catch (NumberFormatException e) {
            logger.error("Invalid Product Id: {}", productId);
            return String.format("Wrong Product Id: %s", e.getMessage());
        }
    }

    private String validateDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        format.setLenient(false);
        try {
            format.parse(date);
            return null;
        } catch (ParseException e) {
            logger.error("Invalid Date: {}", date);
            return String.format("Wrong Date: %s", e.getMessage());
        }
    }
}
