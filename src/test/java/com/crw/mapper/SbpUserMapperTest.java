package com.crw.mapper;

import com.crw.App;
import com.crw.model.SbpUser;
import com.crw.model.SbpUserExample;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class SbpUserMapperTest {

    @Autowired
    private SbpUserMapper sbpUserMapper;

    @Test
    @Rollback
    public void insert() throws Exception {
        long now = System.currentTimeMillis();
        int id = sbpUserMapper.insert(new SbpUser(1L, "张三", "111111", "11100001001", now, now));
        Assert.assertEquals(id, 1L);
    }

    @Test
    @Rollback
    public void selectByPrimaryKey() throws Exception {
        SbpUser user = sbpUserMapper.selectByPrimaryKey(1L);
        Assert.assertEquals("张三", user.getNickName());
    }

    @Test
    @Rollback
    public void update() throws Exception {
        long now = System.currentTimeMillis();
        sbpUserMapper.updateByPrimaryKey(new SbpUser(1L, "张三改", "111111", "11100001001", now, now));
        SbpUser user = sbpUserMapper.selectByPrimaryKey(1L);
        Assert.assertEquals("张三改", user.getNickName());
    }

    @Test
    @Rollback
    public void selectByExample() throws Exception {
        SbpUserExample example = new SbpUserExample();
        SbpUserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo("11100001001");
        List<SbpUser> users = sbpUserMapper.selectByExample(example);
        Assert.assertEquals(users.size(), 1);
    }

}
