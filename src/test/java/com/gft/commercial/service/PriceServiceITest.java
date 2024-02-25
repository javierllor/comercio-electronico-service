package com.gft.commercial.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.gft.commercial.exception.ResourceNotFoundException;
import com.gft.commercial.exception.ValidationException;
import com.gft.commercial.swagger.dto.PriceDto;
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

    private static final String BRAND_ID = "1";
    private static final String PRODUCT_ID = "35455";

    private static final String START_DATE = "2020-06-14-00.00.00";
    private static final String END_DATE = "2020-12-31-23.59.59";

    @Autowired
    private PriceService priceService;


    @Test
    void getPrice_Successful() {
        //Given
        //When
        PriceDto price =
                priceService.getPrice(BRAND_ID, PRODUCT_ID, START_DATE);

        //Then
        assertThat(price.getBrandId()).isEqualTo(Integer.parseInt(BRAND_ID));
        assertThat(price.getProductId()).isEqualTo(Long.parseLong(PRODUCT_ID));
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
                -> priceService.getPrice(BRAND_ID, "77777", START_DATE));
    }

    @Test
    void getPrice_WrongBrandId() {
        //Given
        //When
        //Then
        Assertions.assertThrows(ValidationException.class, ()
                -> priceService.getPrice("Invalid", PRODUCT_ID, START_DATE));
    }

    @Test
    void getPrice_WrongProductId() {
        //Given
        //When
        //Then
        Assertions.assertThrows(ValidationException.class, ()
                -> priceService.getPrice(BRAND_ID, "Invalid", START_DATE));
    }

    @Test
    void getPrice_WrongDate() {
        //Given
        //When
        //Then
        Assertions.assertThrows(ValidationException.class, ()
                -> priceService.getPrice(BRAND_ID, PRODUCT_ID, "Invalid"));
    }
}
