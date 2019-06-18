package com.yosoro.ssm.service;

import com.yosoro.ssm.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductService {


    public List<Product> findAll() throws Exception;
}
