package com.inditex.test.domain.usecase;

import com.inditex.test.domain.model.Price;

import java.time.LocalDateTime;


public interface PriceUseCase {

    Price getPrice(LocalDateTime applicationDate, long productId, int brandId);

}
