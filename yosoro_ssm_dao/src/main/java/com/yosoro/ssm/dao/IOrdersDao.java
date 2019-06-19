package com.yosoro.ssm.dao;

import com.yosoro.ssm.domain.Member;
import com.yosoro.ssm.domain.Orders;
import com.yosoro.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    /**
     * 查询所有
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,
            one = @One(select = "com.yosoro.ssm.dao.IProductDao.findById")),
    })
    List<Orders> findAll() throws Exception;


    /**
     * 根据Id查询订单
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product", javaType = Product.class,
                    one = @One(select = "com.yosoro.ssm.dao.IProductDao.findById")),
            @Result(column = "id",property = "travellers",javaType = java.util.List.class,
                    many = @Many(select = "com.yosoro.ssm.dao.ITravellerDao.findByOrdersId")),
            @Result(column = "memberId",property = "member",javaType = Member.class,
                    one = @One(select = "com.yosoro.ssm.dao.IMemberDao.findById")),
    })
    Orders findById(String id) throws Exception;
}
