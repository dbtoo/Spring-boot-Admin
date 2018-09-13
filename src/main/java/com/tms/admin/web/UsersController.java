package com.tms.admin.web;

import com.alibaba.fastjson.JSONObject;
import com.tms.admin.core.DataTablePageUtil;
import com.tms.admin.core.Result;
import com.tms.admin.core.ResultGenerator;
import com.tms.admin.dao.UsersMapper;
import com.tms.admin.model.Users;
import com.tms.admin.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by CodeGenerator on 2018/09/04.
 */
@Controller
@RequestMapping("/admin/users")
public class UsersController {
    @Resource
    private UsersService usersService;

    @RequestMapping("/add")
    public String add(Users users) {
        users.setPassword("123456");
        users.setRegisterDate(new Date());
        usersService.save(users);
        return "redirect:/admin/users/list";
    }

    @RequestMapping("/delete")
    public String delete(String id, RedirectAttributes redirectAttributes) {
        usersService.deleteByIds(id);
        //提示信息
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/users/list";
    }

    @PostMapping("/update")
    public String update(Users users) {
        usersService.update(users);
        return "redirect:/admin/users/list";
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Users users = usersService.findById(id);
        return ResultGenerator.genSuccessResult(users);
    }

    @RequestMapping("/list")
    public String list() {
        return "users/list";
    }

    //@RequestMapping("/addUsers")"/addUsers/{id}"
    @GetMapping(value = {"/addUsers/{id}", "/addUsers"})  
    public Object addUsers(@PathVariable(required = false) Integer id,Model model) {
        Users users = new Users();
        if(id!=null){
             users = usersService.findById(id);
            model.addAttribute("action", "/admin/users/update");
        }else{
            model.addAttribute("action", "/admin/users/add");
        }
        model.addAttribute("userinfo", users);
        return "users/addUsers";
    }

    @ResponseBody
    @RequestMapping("/list/data")
    public Object list(HttpServletRequest request) {


        //DataTables Request请求
        DataTablePageUtil<Users> dataTable = new DataTablePageUtil<Users>(request);

        //设置分页参数
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());

        //查询列表
        List<Users> list = usersService.searchAll(dataTable.getSearch(), dataTable.getOrder_column_name(), dataTable.getOrder_dir());
        //return JSONObject.toJSON(list);

        PageInfo pageInfo = new PageInfo(list);

        //String column_data = dataTable.getColumns_data();
        //System.out.println(column_data);
        //System.out.println(dataTable.getSearch());
        ////填充Datables数据
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());


        return JSONObject.toJSON(dataTable);
        //return ResultGenerator.genSuccessResult(pageInfo);
    }

}
