package com.boot.future.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.future.entity.LoginUser;
import com.boot.future.mapper.LoginUserMapper;
import com.boot.future.service.ILoginUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ck
 * @since 2017-12-01
 */
@Service
public class LoginUserServiceImpl extends ServiceImpl<LoginUserMapper, LoginUser> implements ILoginUserService {
	final static Logger logger = LoggerFactory.getLogger(LoginUserServiceImpl.class);

	@Cacheable(value = "userInfo", key = "#p0")
	public LoginUser getLoginUserById(Integer id) {
		logger.info("无缓存的时候调用这里");
		LoginUser user = baseMapper.selectById(id);
		return user;
	}
	@CachePut(value = "userInfo", key = "#p0")
	public void  update(Integer id, String name, String password, String phone) {
		boolean falg = false;
		LoginUser user = super.selectById(id);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		user.setUpdatedate(new Date());
		falg = super.updateById(user);
		
	}

	//@CacheEvict(value = "userInfo", key = "#p0")
	public boolean delete(Integer id) {
		boolean falg = false;
		falg = super.deleteById(id);
		return falg;
	}
	public List<LoginUser> getLoginUserListById(Integer id) {
		List<LoginUser> userList = super.selectList(new EntityWrapper<LoginUser>().eq("id", id));
		return userList;
	}

	public boolean insert(LoginUser user) {
		boolean falg = false;
		falg = super.insert(user);
		return falg;
	}

	public boolean updateValue(LoginUser user) {
		boolean falg = false;
		try {
			super.insertOrUpdate(user);
			falg = true;
			logger.info("清理缓存");
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return falg;
	}

	@Override
	//
	// 需要缓存的方法加上手动@Cacheable(value = "userlistcache",keyGenerator = "keyGenerator")
	// @Cacheable(value = "userlistcache", keyGenerator = "keyGenerator")
	public List<LoginUser> getListBySQL() {
		logger.info("无缓存的时候调用这里");
		return baseMapper.findListBySQL();
	}

	@Override
	public LoginUser getLoginUserByPhoneAndName(String phone, String name) {
		return baseMapper.findLoginUserByPhoneAndName(phone, name);
	}

	@Override
	public LoginUser getLoginUserByPhoneAndPassword(String phone, String password) {
		EntityWrapper<LoginUser> wrapper = new EntityWrapper<LoginUser>();
		wrapper.eq("phone", phone).eq("password", password);
		LoginUser loginUser = baseMapper.selectList(wrapper).get(0);
		return loginUser;

	}

	@Override
	public LoginUser getLoginUserByNameAndPassword(String name, String password) {
		EntityWrapper<LoginUser> wrapper = new EntityWrapper<LoginUser>();
		wrapper.eq("name", name).eq("password", password);
		LoginUser loginUser = baseMapper.selectList(wrapper).get(0);
		return loginUser;
	}

	public List<LoginUser> getLoginUserListiByEqId(Integer id) {
		EntityWrapper<LoginUser> wrapper = new EntityWrapper<LoginUser>();
		wrapper.eq("id", id);
		logger.info("--getLoginUserListiByEqId-" + wrapper.getSqlSegment() + "---");
		List<LoginUser> userList = baseMapper.selectList(wrapper);
		return userList;
	}

	public List<LoginUser> getLoginUserListiByWhereId(Integer id) {
		EntityWrapper<LoginUser> wrapper = new EntityWrapper<LoginUser>();
		wrapper.where("id=" + id);
		logger.info("--getLoginUserListiByWhereId-" + wrapper.getSqlSegment() + "---");
		List<LoginUser> userList = baseMapper.selectList(wrapper);
		return userList;
	}

	public List<LoginUser> getLoginUserListiByAndId(Integer id) {
		EntityWrapper<LoginUser> wrapper = new EntityWrapper<LoginUser>();
		wrapper.and("id=" + id);
		logger.info("--getLoginUserListiByAndId-" + wrapper.getSqlSegment() + "---");
		List<LoginUser> userList = baseMapper.selectList(wrapper);
		return userList;
	}

	public LoginUser getLoginUserByandPhone(String phone) {
		EntityWrapper<LoginUser> wrapper = new EntityWrapper<LoginUser>();
		wrapper.eq("phone", phone);
		logger.info("--getLoginUserByandPhone-" + wrapper.getSqlSegment() + "---");
		LoginUser loginUser = baseMapper.selectList(wrapper).get(0);
		return loginUser;
	}

	public LoginUser getLoginUserByandName(String name) {
		EntityWrapper<LoginUser> wrapper = new EntityWrapper<LoginUser>();
		wrapper.eq("name", name);
		logger.info("--getLoginUserByandName-" + wrapper.getSqlSegment() + "---");
		LoginUser loginUser = baseMapper.selectList(wrapper).get(0);
		return loginUser;
	}

}
