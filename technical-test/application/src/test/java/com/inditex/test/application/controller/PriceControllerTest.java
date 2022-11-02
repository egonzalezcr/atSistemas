package com.inditex.test.application.controller;

import com.inditex.test.domain.exceptions.NotFoundException;
import com.inditex.test.domain.model.Price;
import com.inditex.test.domain.usecase.PriceUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceUseCase useCase;

    @Test
    @DisplayName("getPrices with valid args")
    void getPrices() {
        // Arrange
        Price priceMock = new Price();
        priceMock.setBrandId(1);
        try {
            priceMock.setStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 00:00:00"));
            priceMock.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-12-31 23:59:59"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        priceMock.setPriceList(1);
        priceMock.setProductId(35455);
        priceMock.setPriority(0);
        priceMock.setPrice(Float.valueOf("35.50"));
        priceMock.setCurr("EUR");

        when(useCase.getPrice(any(), anyLong(), anyInt())).thenReturn(priceMock);

        // Act
        ResponseEntity response = priceController.getPrices(LocalDateTime.parse("2020-06-15 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 35455, 1);

        // Assert

        Price body = (Price) response.getBody();
        assertEquals(35.50f, body.getPrice());
        assertEquals(1, body.getPriceList());
        assertEquals(35455, body.getProductId());
        assertEquals(0, body.getPriority());
    }

    @Test
    @DisplayName("getPrices with not found price list")
    void getPrices404() {

        // Arrange
        when(useCase.getPrice(any(), anyLong(), anyInt())).thenThrow(new NotFoundException("Price not Found"));

        // Act
        ResponseEntity response = priceController.getPrices(LocalDateTime.parse("2020-06-15 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 35455, 1);

        // Assert
        assertTrue(response.getStatusCode().isError());
        assertEquals(404, response.getStatusCodeValue());

    }

    @Test
    @DisplayName("getPrices throw exception")
    void getPricesException() {

        // Arrange
        when(useCase.getPrice(any(), anyLong(), anyInt())).thenThrow(new RuntimeException(""));

        // Act
        ResponseEntity response = priceController.getPrices(LocalDateTime.parse("2020-06-15 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 35455, 1);

        // Assert
        assertTrue(response.getStatusCode().isError());
        assertEquals(500, response.getStatusCodeValue());

    }

}
