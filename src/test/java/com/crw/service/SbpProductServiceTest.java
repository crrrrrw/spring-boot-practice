package com.crw.service;

import com.crw.App;
import com.crw.model.SbpProduct;
import com.crw.model.SbpUser;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class SbpProductServiceTest {

    @Autowired
    private SbpProductService sbpProductService;

    @Test
    @Rollback
    public void insert() {
        long now = System.currentTimeMillis();
        Integer insert = sbpProductService.insert(SbpProduct.builder()
                .aliasName("thinking in java").fullName("java编程思想")
                .code("BK100001").originalPrice(new BigDecimal(86))
                .vipPrice(new BigDecimal(75)).storage(300)
                .type(SbpProduct.ProductType.BOOK.value())
                .createAt(now).updateAt(now).build());
        assert insert > 0;

    }

    @Test
    @Rollback
    public void update() {
        boolean update = sbpProductService.update(SbpProduct.builder().id(1L).storage(300).build());
        assert update;
    }

    @Test
    @Rollback
    public void getObjectById() throws InterruptedException {
        SbpProduct product1 = sbpProductService.getObjectById(1L);
        log.info("time:{} , first get product:{}", System.currentTimeMillis(), product1);
        SbpProduct product2 = sbpProductService.getObjectById(1L);
        log.info("time:{} , second get product:{}", System.currentTimeMillis(), product2);

        Thread.sleep(15000);

        SbpProduct product3 = sbpProductService.getObjectById(1L);
        log.info("time:{} , third get product:{}", System.currentTimeMillis(), product3);
    }

    @Test
    @Rollback
    public void deleteById() {
        boolean delete = sbpProductService.deleteById(1L);
        assert delete;
    }

}