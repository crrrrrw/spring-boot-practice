package com.crw.service;

import com.crw.model.SbpUser;
import com.github.pagehelper.Page;

import java.util.List;

public interface SbpUserService {

    int insert(SbpUser user);

    int update(SbpUser user);

    int insertList(List<SbpUser> list);

    Page<SbpUser> getListPageInfo(int pageNo, int pageSize);
}
