package com.crw.service.impl;

import com.crw.mapper.SbpUserMapper;
import com.crw.model.SbpUser;
import com.crw.service.SbpUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SbpUserServiceImpl implements SbpUserService {

    @Resource
    private SbpUserMapper sbpUserMapper;

    @Override
    public int insert(SbpUser user) {
        return sbpUserMapper.insert(user);
    }

    @Override
    public int update(SbpUser user) {
        return sbpUserMapper.updateByPrimaryKey(user);
    }

    @Override
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
}

