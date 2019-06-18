package com.yosoro.ssm.dao;

import com.yosoro.ssm.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    @Select("select * from product")
    List<Product> findAll() throws Exception;
}
