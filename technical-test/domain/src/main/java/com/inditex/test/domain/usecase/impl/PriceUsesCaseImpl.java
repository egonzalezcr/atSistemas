package com.inditex.test.domain.usecase.impl;

import com.inditex.test.domain.exceptions.NotFoundException;
import com.inditex.test.domain.model.Price;
import com.inditex.test.domain.repository.PriceRepository;
import com.inditex.test.domain.usecase.PriceUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceUsesCaseImpl implements PriceUseCase {

    private final PriceRepository priceRepository;

    private static final Logger logger = LoggerFactory.getLogger(PriceUseCase.class);

    @Override
    public Price getPrice(LocalDateTime applicationDate, long productId, int brandId) {
        logger.debug("getPrice in uses cases method reached");

        List<Price> prices = priceRepository.findPrice(applicationDate, productId, brandId);

        if (prices == null || prices.isEmpty()) {
            logger.debug("Price not found");
            throw new NotFoundException("Price not found");
        }

        logger.debug("found price");
        return prices.get(0);

    }
}
