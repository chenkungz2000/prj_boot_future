package com.boot.future.controller.cms;

import org.springframework.web.bind.annotation.*;

import com.boot.future.entity.CmsCategory;
import com.boot.future.service.ICmsCategoryService;
import com.boot.future.swagger.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ck
 * @since 2018-02-24
 */
@Controller
@RequestMapping("/cmscategory")
public class CmsCategoryController {
    @Autowired
    ICmsCategoryService service;

    @ApiOperation(value = "创建栏目")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    @GetMapping(value = "/test")
    @ResponseBody
    public Map<String, Object> test(
            @ApiParam(required = true, value = "姓名") String name,
            @ApiParam(required = true, value = "关键字") String keyword,
            @ApiParam(required = true, value = "別名") String nickname,
            @ApiParam(required = true, value = "父id") String parentid) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = service.insertCmsCategory(name, keyword, nickname, parentid);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @ApiOperation(value = "创建栏目")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    @GetMapping(value = "/in2")
    @ResponseBody
    public Map<String, Object> insert2(
            @ApiParam(required = true, value = "姓名") String name,
            @ApiParam(required = true, value = "关键字") String keyword,
            @ApiParam(required = true, value = "別名") String nickname,
            @ApiParam(required = true, value = "父id") String parentid) {
        return service.insertCmsCategory2(name, keyword, nickname, parentid);
    }

    @ApiOperation(value = "创建栏目")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    @PostMapping(value = "/in")
    @ResponseBody
    public Map<String, Object> insert(
            @ApiParam(required = true, value = "姓名") String name,
            @ApiParam(required = true, value = "关键字") String keyword,
            @ApiParam(required = true, value = "別名") String nickname,
            @ApiParam(required = true, value = "父id") String parentid) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = service.insertCmsCategory(name, keyword, nickname, parentid);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @ApiOperation(value = "获取栏目")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    @GetMapping(value = "/get")
    @ResponseBody
    public Map<String, Object> getList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = false;
        try {
            List<CmsCategory> list = service.getList();
            map.put("list", list);
            flag = true;
        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        map.put("flag", flag);
        return map;
    }
}
