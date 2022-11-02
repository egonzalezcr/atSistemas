package com.inditex.test.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    private int id;
    private int brandId;
    private Date startDate;
    private Date endDate;
    private int priceList;
    private long productId;
    private int priority;
    private float price;
    private String curr;
}
