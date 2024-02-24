package com.gft.commercial.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.gft.commercial.exception.ResourceNotFoundException;
import com.gft.commercial.repository.PricesRepository;
import jakarta.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PriceServiceTest {


    public static final Integer BRAND_ID = 1;
    public static final Long PRODUCT_ID = 35455L;
    public static final LocalDateTime DATE = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0);
    @Mock
    private PricesRepository pricesRepository;

    @Autowired
    private PriceService priceService;

//    @Test
//    void getPrice_Successful() {
//        //Given
//        when(pricesRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
//                eq(BRAND_ID), eq(PRODUCT_ID), eq(DATE), eq(DATE)))
//                .thenReturn(Optional.of(PriceEntity.builder()
//                        .brandId(BRAND_ID)
//                        .productId(PRODUCT_ID)
//                        .build()));
//
//        //When
//        PriceDto price = priceService.getPrice(BRAND_ID, PRODUCT_ID, DATE);
//
//        //Then
//        assertThat(price.getBrandId()).isEqualTo(BRAND_ID);
//        assertThat(price.getProductId()).isEqualTo(PRODUCT_ID);
//    }

    @Test
    void getPrice_NotFound() {
        //Given
        when(pricesRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                eq(BRAND_ID), eq(PRODUCT_ID), eq(DATE), eq(DATE)))
                .thenReturn(Optional.empty());

        //When
        //Then
        Assertions.assertThrows(ResourceNotFoundException.class, ()
                -> priceService.getPrice(BRAND_ID, PRODUCT_ID, DATE));
    }

    @Test
    void getPrice_FatalError() {
        //Given
        when(pricesRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                eq(BRAND_ID), eq(PRODUCT_ID), eq(DATE), eq(DATE)))
                .thenThrow(PersistenceException.class);

        //When
        //Then
        Assertions.assertThrows(Exception.class, ()
                -> priceService.getPrice(BRAND_ID, PRODUCT_ID, DATE));
    }

}
