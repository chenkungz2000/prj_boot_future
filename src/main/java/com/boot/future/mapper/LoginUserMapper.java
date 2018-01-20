package com.boot.future.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.future.entity.LoginUser;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ck
 * @since 2017-12-01
 */
public interface LoginUserMapper extends BaseMapper<LoginUser> {

	@Select("select * from login_user")
	List<LoginUser> findListBySQL();

	@Select("select * from login_user where phone=#{phone} and name=#{name}")
	LoginUser findLoginUserByPhoneAndName(String phone, String name);


}
