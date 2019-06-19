package com.yosoro.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.yosoro.ssm.domain.Orders;
import com.yosoro.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    // 查询所有 未分页
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll();
        mv.addObject("orders",orders);
        mv.setViewName("orders-list");
        return mv;
    }*/
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")int page, @RequestParam(name = "size", required = true, defaultValue = "4")int size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll(page, size);
        // PageInfo 就是一个PageBean
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list-page");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        Orders orders = ordersService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orders-show");
        mv.addObject("orders", orders);
        System.out.println(orders);
        return mv;
    }
}
