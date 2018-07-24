package com.crw.service;

import com.crw.model.SbpProduct;

public interface SbpProductService {

    Long insert(SbpProduct sbpProduct);

    boolean update(SbpProduct sbpProduct);

    boolean deleteById(Long id);

    SbpProduct getObjectById(Long id);
}
