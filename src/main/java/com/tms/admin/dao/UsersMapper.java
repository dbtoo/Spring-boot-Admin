package com.tms.admin.dao;

import com.tms.admin.core.Mapper;
import com.tms.admin.model.Users;
import java.util.List;

public interface UsersMapper extends Mapper<Users> {

     List<Users> searchAll (String key);
}