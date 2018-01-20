package com.boot.future.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.future.entity.LoginUserData;
import com.boot.future.mapper.LoginUserDataMapper;
import com.boot.future.service.ILoginUserDataService;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ck
 * @since 2017-12-11
 */
@Service
public class LoginUserDataServiceImpl extends ServiceImpl<LoginUserDataMapper, LoginUserData> implements ILoginUserDataService {

}
