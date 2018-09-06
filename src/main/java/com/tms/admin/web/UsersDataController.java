package com.tms.admin.web;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tms.admin.core.DataTablePageUtil;
import com.tms.admin.model.Users;
import com.tms.admin.service.UsersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* Created by CodeGenerator on 2018/09/04.
*/
@RestController
@RequestMapping("/admin/users")
public class UsersDataController {
    @Resource
    private UsersService usersService;


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
