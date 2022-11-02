package com.inditex.test.domain.repository;

import com.inditex.test.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

     List<Price> findPrice(LocalDateTime applicationDate, long productId, int brandId);
}
