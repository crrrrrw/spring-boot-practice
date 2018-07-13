package com.crw.service;

import com.crw.App;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class SbpUserServiceTest {

    @Autowired
    private SbpUserService sbpUserService;

    @Test
    @Rollback
    public void insertList() throws Exception {
        long now = System.currentTimeMillis();
        List<SbpUser> list = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            SbpUser user = SbpUser.builder().mobile("1110000100" + i)
                    .nickName("陈" + i).password("111111")
                    .createAt(now).updateAt(now).build();
            list.add(user);
        }

        int count = sbpUserService.insertList(list);
        Assert.assertEquals(20, count);
    }

    @Test
    @Rollback
    public void getListPageIo() throws Exception {
        Page<SbpUser> page = sbpUserService.getListPageInfo(1, 3);
        log.info("page:{},{}", page.getPages(), page.getTotal());
    }

}