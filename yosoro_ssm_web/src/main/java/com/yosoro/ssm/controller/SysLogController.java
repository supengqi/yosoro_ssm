package com.yosoro.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yosoro.ssm.domain.SysLog;
import com.yosoro.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/syslog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "page",required = true,defaultValue = "1") Integer page, @RequestParam(value = "size",required = true, defaultValue = "10") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll(page,size);
        PageInfo sysLogPage = new PageInfo(sysLogs);
        mv.addObject("sysLogPage",sysLogPage);
        mv.setViewName("syslog-list");
        return mv;
    }

    @RequestMapping("/truncateAllData.do")
    public String truncateAllData() throws Exception {
        sysLogService.truncateAllData();
        return "redirect:findAll.do";
    }




}
