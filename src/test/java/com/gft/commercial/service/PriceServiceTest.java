package com.gft.commercial.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.gft.commercial.exception.ResourceNotFoundException;
import com.gft.commercial.repository.PricesRepository;
import jakarta.persistence.PersistenceException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PriceServiceTest {


    public static final String BRAND_ID = "1";
    public static final String PRODUCT_ID = "35455";
    public static final String DATE = "2020-06-14-00.00.00";
    @Mock
    private PricesRepository pricesRepository;

    @Autowired
    private PriceService priceService;

    @Test
    void getPrice_NotFound() {
        //Given
        when(pricesRepository.findPriceEntity(
                eq(Integer.parseInt(BRAND_ID)), eq(Long.parseLong(PRODUCT_ID)), eq(DATE), eq(DATE)))
                .thenReturn(Optional.empty());

        //When
        //Then
        Assertions.assertThrows(ResourceNotFoundException.class, ()
                -> priceService.getPrice(BRAND_ID, PRODUCT_ID, DATE));
    }

    @Test
    void getPrice_FatalError() {
        //Given
        when(pricesRepository.findPriceEntity(
                eq(Integer.parseInt(BRAND_ID)), eq(Long.parseLong(PRODUCT_ID)), eq(DATE), eq(DATE)))
                .thenThrow(PersistenceException.class);

        //When
        //Then
        Assertions.assertThrows(Exception.class, ()
                -> priceService.getPrice(BRAND_ID, PRODUCT_ID, DATE));
    }

}
