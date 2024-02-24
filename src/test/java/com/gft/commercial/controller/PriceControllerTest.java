package com.gft.commercial.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gft.commercial.exception.ResourceNotFoundException;
import com.gft.commercial.service.PriceService;
import com.gft.commercial.swagger.dto.PriceDto;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PriceController.class)
@Import(AopAutoConfiguration.class)
@AutoConfigureMockMvc
public class PriceControllerTest {

    public static final Integer BRAND_ID = 1;
    public static final Long PRODUCT_ID = 35455L;
    public static final LocalDateTime DATE = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0);

    public static final String DATE_STRING = DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

    public static final Integer PRICE_LIST = 2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Test
    void getPrice_Success() throws Exception {
        // Given
        when(priceService.getPrice(
                eq(BRAND_ID), eq(PRODUCT_ID), eq(DATE)))
                .thenReturn(PriceDto
                        .builder()
                        .brandId(BRAND_ID)
                        .productId(PRODUCT_ID)
                        .startDate(DATE)
                        .endDate(DATE)
                        .priceList(PRICE_LIST)
                        .build());

        // When
        // Then
        this.mockMvc
                .perform(
                        get("/prices/1/35455/2020-06-14T00:00:00")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(BRAND_ID)))
                .andExpect(jsonPath("$.priceList", is(PRICE_LIST)))
                .andExpect(jsonPath("$.startDate", is(DATE_STRING)))
                .andExpect(jsonPath("$.endDate", is(DATE_STRING)));
    }

    @Test
    void getPrice_NotFound() throws Exception {
        // Given
        when(priceService.getPrice(
                eq(BRAND_ID), eq(PRODUCT_ID), eq(DATE)))
                .thenThrow(ResourceNotFoundException.class);

        // When
        // Then
        this.mockMvc
                .perform(
                        get("/prices/1/35455/2020-06-14T00:00:00")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", is("NOT_FOUND")));
    }

    @Test
    void getPriceWrongBandId_Error() throws Exception {
        // Given
        // When
        // Then
        this.mockMvc
                .perform(
                        get("/prices/error/35455/2020-06-14T00:00:00")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPriceWrongProductId_Error() throws Exception {
        // Given
        // When
        // Then
        this.mockMvc
                .perform(
                        get("/prices/1/error/2020-06-14T00:00:00")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPriceWrongDate_Error() throws Exception {
        // Given
        // When
        // Then
        this.mockMvc
                .perform(
                        get("/prices/error/35455/2020-06-14-00:00:00")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPriceWrongUrl_Error() throws Exception {
        // Given
        // When
        // Then
        this.mockMvc
                .perform(
                        get("/error/1/35455/2020-06-14-00:00:00")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
