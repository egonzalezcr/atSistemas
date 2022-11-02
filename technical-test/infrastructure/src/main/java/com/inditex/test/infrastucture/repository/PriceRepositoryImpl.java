package com.inditex.test.infrastucture.repository;

import com.inditex.test.domain.model.Price;
import com.inditex.test.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements  PriceRepository{

    private final NamedParameterJdbcTemplate template;

    @Override
    public List<Price> findPrice(LocalDateTime applicationDate, long productId, int brandId) {

        String query = "SELECT * FROM PRICES WHERE START_DATE <= :applicationDate " +
                "AND END_DATE >= :applicationDate " +
                "AND PRODUCT_ID = :productId " +
                "AND BRAND_ID = :brandId " +
                "ORDER BY PRIORITY DESC";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
        sqlParameters.addValue("applicationDate", applicationDate.format(formatter));
        sqlParameters.addValue("productId", productId);
        sqlParameters.addValue("brandId", brandId);

        List<Price> prices = template.query(query, sqlParameters, rs -> {
            final List<Price> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(new Price(rs.getInt("ID"),
                        rs.getInt("BRAND_ID"), rs.getDate("START_DATE"), rs.getDate("END_DATE"),
                        rs.getInt("PRICE_LIST"), rs.getInt("PRODUCT_ID"), rs.getInt("PRIORITY"),
                        rs.getFloat("PRICE"), rs.getString("CURR")));
            }
            return resultList;
        });

        return prices;
    }
}
