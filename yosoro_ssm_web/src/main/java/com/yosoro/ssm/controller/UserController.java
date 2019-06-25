package com.yosoro.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.yosoro.ssm.domain.Role;
import com.yosoro.ssm.domain.UserInfo;
import com.yosoro.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iuserService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")Integer page, @RequestParam(name = "size", required = true, defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> users = iuserService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(users);
        mv.addObject("pageInfo", pageInfo);
        System.out.println(pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo user) throws Exception {
        iuserService.save(user);

        return "redirect:findAll.do";
    }

    /**
     * 修改角色
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/update.do")
    public String update(UserInfo userInfo) throws Exception {
        iuserService.update(userInfo);
        return "redirect:findAll.do";
    }

    // 查询指定id 的用户
    @RequestMapping("/updateFindById.do")
    public ModelAndView updateFindById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo users = iuserService.findById(id);
        mv.addObject("users",users);
        System.out.println(users);
        mv.setViewName("update-add");
        return mv;
    }

    // 查询指定id 的用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo users = iuserService.findById(id);
        mv.addObject("users",users);
        mv.setViewName("user-show");
        return mv;
    }

    // 给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userID,
                                @RequestParam(name = "ids", required = true) String[] ids) throws Exception {
        iuserService.addRoleToUser(userID,ids);
        return "redirect:findAll.do";
    }

    // 查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAllRole.do")
    public ModelAndView findUserByIdAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        // 根据用户ID查询用户
        UserInfo user = iuserService.findById(userId);
        // 根据用户id查询可以添加的角色
        List<Role> otherRoles = iuserService.findOtherRole(userId);
        mv.addObject("user",user);
        mv.addObject("otherRoles",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/deleteUsers.do")
    public String deleteUsers(String id) throws Exception {
        iuserService.deleteUsers(id);
        return "redirect:findAll.do";
    }
}
