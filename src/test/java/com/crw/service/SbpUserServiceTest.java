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
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate redisTemplate;

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

    @Test
    public void insert() {
        long now = System.currentTimeMillis();
        SbpUser user = SbpUser.builder().mobile("11100008888")
                .nickName("陈插入").password("111111")
                .createAt(now).updateAt(now).build();
        user = sbpUserService.insert(user);
        assert user.getId() > 0;
    }

    @Test
    public void getObjectById() {
        SbpUser user = sbpUserService.getObjectById(1L);
        Assert.assertEquals("11100001001", user.getMobile());
    }

    @Test
    public void deleteById() {
        boolean flag = sbpUserService.deleteById(10L);
        Assert.assertEquals(flag, true);
    }

    @Test
    public void update() {
        long now = System.currentTimeMillis();
        SbpUser user = SbpUser.builder().id(11L).mobile("11100009999")
                .nickName("陈修改").password("111111")
                .createAt(now).updateAt(now).build();
        SbpUser user2 = sbpUserService.update(user);
        Assert.assertEquals(user2.getNickName(), user.getNickName());
    }

    @Test
    public void testRedisCache() {
        long now = System.currentTimeMillis();
        SbpUser user1 = SbpUser.builder().id(666L).mobile("11100006666")
                .nickName("老A").password("111111")
                .createAt(now).updateAt(now).build();
        sbpUserService.insert(user1);
        log.info("~~~~~~~~~~~~~insert data~~~~~~~~~~~~~~~~~~~~~~");

        log.info("=============first select start===============");
        SbpUser user2 = sbpUserService.getObjectById(666L);
        log.info("=============first select end=================,user:{}", user2);

        user2.setNickName("老B");
        sbpUserService.update(user2);
        log.info("~~~~~~~~~~~~~update data~~~~~~~~~~~~~~~~~~~~~~");

        log.info("=============second select start===============");
        SbpUser user3 = sbpUserService.getObjectById(666L);
        log.info("=============second select end=================,user:{}", user3);

        sbpUserService.deleteById(666L);
        log.info("~~~~~~~~~~~~~delete data~~~~~~~~~~~~~~~~~~~~~~");

        log.info("=============third select start===============");
        SbpUser user4 = sbpUserService.getObjectById(666L);
        log.info("=============third select end=================,user:{}", user4);


        assert 666L == sbpUserService.insert(SbpUser.builder().id(666L).mobile("11100006666")
                .nickName("老A").password("111111")
                .createAt(now).updateAt(now).build()).getId();
        redisTemplate.delete("userCache:id:666"); // 手动删除redis缓存
        log.info("~~~~~~~~~~~~~insert data and delete cache~~~~~~~~~~~~~~~~~~~~~~");

        log.info("=============fourth select start===============");
        SbpUser user5 = sbpUserService.getObjectById(666L);
        log.info("=============fourth select end=================,user:{}", user5);

    }

}