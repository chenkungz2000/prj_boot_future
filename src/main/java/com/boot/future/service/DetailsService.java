package com.boot.future.service;

import com.boot.future.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DetailsService  implements  UserDetailsService {

    @Autowired
    ILoginUserService iLoginUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LoginUser loginUser = iLoginUserService.getLoginUserByandName(s);
        if (loginUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return (UserDetails) loginUser;
    }
}
