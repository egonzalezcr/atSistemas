import com.inditex.test.MainApplication;
import com.inditex.test.application.controller.PriceController;
import com.inditex.test.domain.model.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes  = MainApplication.class)
public class ApplicationTest {

    @Autowired
    private PriceController priceController;

    @Test
    void getPriceTestOneOk() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        long productId = 35455;
        int brandId = 1;

        ResponseEntity response = priceController.getPrices(applicationDate, productId, brandId);

        Price body = (Price) response.getBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(productId, body.getProductId());
        Assertions.assertEquals(brandId, body.getBrandId());
        Assertions.assertEquals(0, body.getPriority());
        Assertions.assertEquals(Float.valueOf("35.50"), body.getPrice());
        Assertions.assertEquals(1, body.getPriceList());
        Assertions.assertEquals("EUR", body.getCurr());
    }

    @Test
    void getPriceTestTwoOK() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 16:00:00", formatter);
        long productId = 35455;
        int brandId = 1;

        ResponseEntity response = priceController.getPrices(applicationDate, productId, brandId);

        Price body = (Price) response.getBody();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(productId, body.getProductId());
        Assertions.assertEquals(brandId, body.getBrandId());
        Assertions.assertEquals(1, body.getPriority());
        Assertions.assertEquals(Float.valueOf("25.45"), body.getPrice());
        Assertions.assertEquals(2, body.getPriceList());
        Assertions.assertEquals("EUR", body.getCurr());
    }

    @Test
    void getPriceTestThreeOK() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 21:00:00", formatter);
        long productId = 35455;
        int brandId = 1;

        ResponseEntity response = priceController.getPrices(applicationDate, productId, brandId);

        Price body = (Price) response.getBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(productId, body.getProductId());
        Assertions.assertEquals(brandId, body.getBrandId());
        Assertions.assertEquals(0, body.getPriority());
        Assertions.assertEquals(Float.valueOf("35.50"), body.getPrice());
        Assertions.assertEquals(1, body.getPriceList());
        Assertions.assertEquals("EUR", body.getCurr());

    }

    @Test
    void getPriceTestFourOK() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15 10:00:00", formatter);
        long productId = 35455;
        int brandId = 1;

        ResponseEntity response = priceController.getPrices(applicationDate, productId, brandId);
        Price body = (Price) response.getBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(productId, body.getProductId());
        Assertions.assertEquals(brandId, body.getBrandId());
        Assertions.assertEquals(1, body.getPriority());
        Assertions.assertEquals(Float.valueOf("30.50"), body.getPrice());
        Assertions.assertEquals(3, body.getPriceList());
        Assertions.assertEquals("EUR", body.getCurr());

    }

    @Test
    void getPriceTestFiveOK() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15 10:00:00", formatter);
        long productId = 35455;
        int brandId = 1;

        ResponseEntity response = priceController.getPrices(applicationDate, productId, brandId);
        Price body = (Price) response.getBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(productId, body.getProductId());
        Assertions.assertEquals(brandId, body.getBrandId());
        Assertions.assertEquals(1, body.getPriority());
        Assertions.assertEquals(Float.valueOf("30.50"), body.getPrice());
        Assertions.assertEquals(3, body.getPriceList());
        Assertions.assertEquals("EUR", body.getCurr());

    }

    @Test
    void getNotFoundException()  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15 10:00:00", formatter);
        long productId = 55468;
        int brandId = 1;

        ResponseEntity response = priceController.getPrices(applicationDate, productId, brandId);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
}
