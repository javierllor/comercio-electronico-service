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

    public static final String BRAND_ID = "1";
    public static final String PRODUCT_ID = "35455";
    public static final String DATE = "2020-06-14-00.00.00";

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
                        .brandId(Integer.parseInt(BRAND_ID))
                        .productId(Long.parseLong(PRODUCT_ID))
                        .startDate(DATE)
                        .endDate(DATE)
                        .priceList(PRICE_LIST)
                        .build());

        // When
        // Then
        this.mockMvc
                .perform(
                        get("/prices/1/35455/2020-06-14-00.00.00")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(Integer.parseInt(BRAND_ID))))
                .andExpect(jsonPath("$.priceList", is(PRICE_LIST)))
                .andExpect(jsonPath("$.startDate", is(DATE)))
                .andExpect(jsonPath("$.endDate", is(DATE)));
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
                        get("/prices/1/35455/2020-06-14-00.00.00")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", is("NOT_FOUND")));
    }

    @Test
    void getPriceWrongUrl_Error() throws Exception {
        // Given
        // When
        // Then
        this.mockMvc
                .perform(
                        get("/error/1/35455/2020-06-14-00.00.00")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
