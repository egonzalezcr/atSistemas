package com.inditex.test.application.controller;

import com.inditex.test.domain.exceptions.NotFoundException;
import com.inditex.test.domain.model.Price;
import com.inditex.test.domain.usecase.PriceUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
@Controller
@RequiredArgsConstructor
public class PriceController {


    private final PriceUseCase useCase;

    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPrices(@RequestParam
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate,
                                 @RequestParam long productId,
                                 @RequestParam int brandId) {

        try {

            Price response = useCase.getPrice(applicationDate, productId, brandId);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        } catch ( NotFoundException exp) {
            logger.debug(exp.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
