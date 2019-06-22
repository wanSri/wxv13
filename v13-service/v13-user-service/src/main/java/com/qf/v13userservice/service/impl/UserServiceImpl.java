package com.qf.v13userservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.common.base.BaseServiceImpl;
import com.qf.common.base.IBaseDao;
import com.qf.entity.TUser;
import com.qf.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.IUserService;

/**
 * @author: WangXi
 * @Date: 2019/6/21
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<TUser> implements IUserService {

    @Autowired
    private TUserMapper userMapper;

    @Override
    public IBaseDao<TUser> getBaseDao() {
        return userMapper;
    }
}
