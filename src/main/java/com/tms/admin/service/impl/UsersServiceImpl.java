package com.tms.admin.service.impl;

import com.tms.admin.dao.UsersMapper;
import com.tms.admin.model.Users;
import com.tms.admin.service.UsersService;
import com.tms.admin.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/09/04.
 */
@Service
@Transactional
public class UsersServiceImpl extends AbstractService<Users> implements UsersService {
    @Resource
    private UsersMapper usersMapper;

}
