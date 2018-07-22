package com.crw.service.impl;

import com.crw.mapper.SbpProductMapper;
import com.crw.model.SbpProduct;
import com.crw.service.SbpProductService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@CacheConfig(cacheNames = "product")
public class SbpProductServiceImpl implements SbpProductService {

    @Resource
    private SbpProductMapper sbpProductMapper;

    @Override
    @CachePut
    public Integer insert(SbpProduct sbpProduct) {
        return sbpProductMapper.insert(sbpProduct);
    }

    @Override
    @CachePut
    public boolean update(SbpProduct sbpProduct) {
        return sbpProductMapper.updateByPrimaryKeySelective(sbpProduct) > 0;
    }

    @Override
    @CacheEvict
    public boolean deleteById(Long id) {
        return sbpProductMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    @Cacheable
    public SbpProduct getObjectById(Long id) {
        return sbpProductMapper.selectByPrimaryKey(id);
    }
}
