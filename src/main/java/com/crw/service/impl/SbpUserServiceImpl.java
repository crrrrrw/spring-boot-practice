package com.crw.service.impl;

import com.crw.mapper.SbpUserMapper;
import com.crw.model.SbpUser;
import com.crw.service.SbpUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "userCache")
public class SbpUserServiceImpl implements SbpUserService {

    @Resource
    private SbpUserMapper sbpUserMapper;

    @Override
    @CachePut(key = "'id:'+ #p0.id")
    public SbpUser insert(SbpUser user) {
        sbpUserMapper.insert(user);
        return user;
    }

    @Override
    @CachePut(key = "'id:'+ #p0.id")
    public SbpUser update(SbpUser user) {
        sbpUserMapper.updateByPrimaryKey(user);
        return user;
    }

    @Override
    @CacheEvict(allEntries = true, beforeInvocation = true)// 清空 userCache 缓存
    public int insertList(List<SbpUser> list) {
        return sbpUserMapper.insertList(list);
    }

    @Override
    public Page<SbpUser> getListPageInfo(int pageNo, int pageSize) {
        // 开启分页
        Page<SbpUser> page = PageHelper.startPage(pageNo, pageSize);
        sbpUserMapper.getAll();
        return page;
    }

    @Override
    @CacheEvict(key = "'id:'+#id", beforeInvocation = true)
    public boolean deleteById(Long id) {
        return sbpUserMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    @Cacheable(key = "'id:'+ #id")
    public SbpUser getObjectById(Long id) {
        return sbpUserMapper.selectByPrimaryKey(id);
    }
}
