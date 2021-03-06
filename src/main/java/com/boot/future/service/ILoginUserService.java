package com.boot.future.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.boot.future.entity.LoginUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ck
 * @since 2017-12-01
 */
public interface ILoginUserService extends IService<LoginUser>{
	//检测登录
	boolean detectionLogin(String value ,String password);

	boolean updateValue(LoginUser user);

	boolean insert(LoginUser user);

	void update(Integer id, String name, String password, String phone);

	boolean delete(Integer id);
	

	List<LoginUser> getLoginUserListById(Integer id);

	List<LoginUser> getListBySQL();

	LoginUser getLoginUserByPhoneAndName(String phone, String name);

	LoginUser getLoginUserByPhoneAndPassword(String phone, String password);

	LoginUser getLoginUserByNameAndPassword(String name, String password);

	LoginUser getLoginUserById(Integer id);

	List<LoginUser> getLoginUserListiByEqId(Integer id);

	List<LoginUser> getLoginUserListiByWhereId(Integer id);

	List<LoginUser> getLoginUserListiByAndId(Integer id);

	LoginUser getLoginUserByandName(String name);

	LoginUser getLoginUserByandPhone(String phone);

}
