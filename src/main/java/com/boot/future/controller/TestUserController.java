package com.boot.future.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.future.entity.LoginUser;
import com.boot.future.entity.LoginUserData;
import com.boot.future.service.ILoginUserDataService;
import com.boot.future.service.ILoginUserService;
import com.boot.future.swagger.result.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import redis.clients.jedis.Jedis;
import springfox.documentation.annotations.ApiIgnore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ck
 * @since 2017-12-01
 */
@Controller
@RequestMapping("/testuser")
@Api(value = "测试api", description = "用户测试")
public class TestUserController extends BaseController {
    final static Logger logger = LoggerFactory.getLogger(TestUserController.class);
    @Autowired
    ILoginUserService service;
    @Autowired
    ILoginUserDataService iLoginUserDataService;

    @ApiOperation(value = "获取个人信息")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    @GetMapping(value="/get/{id}")
    @ResponseBody
	public Map<String, Object> get( @ApiParam(required = true, value = "唯一标识") @PathVariable Integer id) throws Exception {
    	TestRedisFlag();
    	logger.info("查询开始");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = false;
		try {
			List<LoginUser> list = service.getLoginUserListiByEqId(id);
			logger.info("list="+list);
			flag=true;
			map.put("list", list);
		} catch (Exception e) {
			map.put("flag", flag);
			map.put("msg", e.getMessage());
		}
		logger.info("查询结束 状态："+flag+"");
		return map;
	}

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            String id = request.getParameter("id");
            logger.info(id);
            logger.info("BB");
            LoginUser user = service.getLoginUserById(Integer.valueOf(id));
            logger.info("没执行查询，讀取緩存");
            flag = true;
            map.put("name", user.getName());
        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        map.put("flag", flag);
        return map;

    }
	@ApiOperation(value = "修改个人信息")
	@ApiResponse(code = 200, message = "success", response = Result.class)
	@GetMapping(value = "/set/{id}/{name}")
	@ResponseBody
	public Map<String, Object> setLoginUser(@ApiParam(required = true, value = "唯一标识") @PathVariable("id") Integer id,
			@ApiParam(required = true, value = "姓名") @PathVariable("name") String name) throws Exception {
		TestRedisFlag();
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "查询开始");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = false;
		try {
			LoginUser user=service.getLoginUserById(id);
			if(user!=null)
			user.setName(name);
			service.updateValue(user);
			flag = true;
		} catch (Exception e) {
			map.put("msg", e.getMessage());
		}
		map.put("flag", flag);
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "查询结束 状态：" + flag + "");
		return map;
	}
    @GetMapping("/getlisto")
    @ResponseBody
    @ApiOperation(value = "获取信息")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    public Object getUserListBySQL() {
        JSONObject result = new JSONObject();
        List<LoginUser> list = service.getListBySQL();
        logger.info("AA");
        result.put("list", list);
        logger.info("BB");
        return result;

    }
    
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取信息")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    public Map<String, Object> getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            List<LoginUser> list = service.getListBySQL();
            logger.info("没执行查询，讀取緩存");
            flag = true;
            map.put("list", list);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        map.put("flag", flag);
        return map;

    }

    public void TestRedisFlag(){
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        logger.info("连接成功");
        //查看服务是否运行
        logger.info("服务正在运行: "+jedis.ping()+"");
	}
}
