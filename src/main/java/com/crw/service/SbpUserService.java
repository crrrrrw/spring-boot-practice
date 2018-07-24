package com.crw.service;

import com.crw.model.SbpUser;
import com.github.pagehelper.Page;

import java.util.List;

public interface SbpUserService {

    SbpUser insert(SbpUser user);

    SbpUser update(SbpUser user);

    int insertList(List<SbpUser> list);

    Page<SbpUser> getListPageInfo(int pageNo, int pageSize);

    boolean deleteById(Long id);

    SbpUser getObjectById(Long id);
}
