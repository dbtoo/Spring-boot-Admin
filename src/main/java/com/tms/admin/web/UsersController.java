package com.tms.admin.web;
import com.alibaba.fastjson.JSONObject;
import com.tms.admin.core.DataTablePageUtil;
import com.tms.admin.core.Result;
import com.tms.admin.core.ResultGenerator;
import com.tms.admin.model.Users;
import com.tms.admin.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public Result add(Users users) {
        usersService.save(users);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        usersService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Users users) {
        usersService.update(users);
        return ResultGenerator.genSuccessResult();
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

    @ResponseBody
    @RequestMapping("/list/data")
    public Object list(HttpServletRequest request) {
        //public Object list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        int page = 0;
        int size = 0;
        try{
            page = Integer.parseInt(request.getParameter("page"));
            size = Integer.parseInt(request.getParameter("size"));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());

        }

        PageHelper.startPage(page, size);
        List<Users> list = usersService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        DataTablePageUtil<Users> dataTable= new DataTablePageUtil<Users>(request);

        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());




        return JSONObject.toJSON(dataTable);
        //return ResultGenerator.genSuccessResult(pageInfo);
    }

}
