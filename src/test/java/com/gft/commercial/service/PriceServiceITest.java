package com.gft.commercial.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.gft.commercial.exception.ResourceNotFoundException;
import com.gft.commercial.swagger.dto.PriceDto;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest
@Sql(
        scripts = {"/data/prices_schema.sql", "/data/insert_prices.sql"},
        executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/data/delete_prices.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class PriceServiceITest {

    private static final int BRAND_ID = 1;
    private static final Long PRODUCT_ID = 35455L;

    private static final LocalDateTime START_DATE = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
    private static final LocalDateTime END_DATE = LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59);

    @Autowired
    private PriceService priceService;


    @Test
    void getPrice_Successful() {
        //Given
        //When
        PriceDto price =
                priceService.getPrice(BRAND_ID, PRODUCT_ID, START_DATE);

        //Then
        assertThat(price.getBrandId()).isEqualTo(BRAND_ID);
        assertThat(price.getProductId()).isEqualTo(PRODUCT_ID);
        assertThat(price.getPriceList()).isEqualTo(1);
        assertThat(price.getStartDate()).isEqualTo(START_DATE);
        assertThat(price.getEndDate()).isEqualTo(END_DATE);
        assertThat(price.getFinalPrice()).isEqualTo("35.5 EUR");
    }

    @Test
    void getPrice_NotFound() {
        //Given
        //When
        //Then
        Assertions.assertThrows(ResourceNotFoundException.class, ()
                -> priceService.getPrice(BRAND_ID, 7777L, START_DATE));
    }
}
