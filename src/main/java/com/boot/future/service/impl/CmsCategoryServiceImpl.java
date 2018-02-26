package com.boot.future.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.future.entity.CmsCategory;
import com.boot.future.entity.LoginUser;
import com.boot.future.mapper.CmsCategoryMapper;
import com.boot.future.service.ICmsCategoryService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boot.future.swagger.result.Result;
import com.boot.future.tools.PingYinUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ck
 * @since 2018-02-24
 */
@Service
@Transactional
@CacheConfig(cacheNames = "cmscategory")
public class CmsCategoryServiceImpl extends ServiceImpl<CmsCategoryMapper, CmsCategory> implements ICmsCategoryService {

    final static Logger logger = LoggerFactory.getLogger(CmsCategoryServiceImpl.class);

    public boolean insert(CmsCategory cmsCategory) {
        boolean falg = false;
        falg = super.insert(cmsCategory);
        return falg;
    }

    public List<CmsCategory> getList() {
        Wrapper<CmsCategory> wrapper = new EntityWrapper<CmsCategory>();
        return baseMapper.selectList(wrapper);
    }


    public List<CmsCategory> getListByName(String name) {
        Wrapper<CmsCategory> wrapper = new EntityWrapper<CmsCategory>();
        wrapper.eq("name", name);
        return baseMapper.selectList(wrapper);
    }

    public List<CmsCategory> getListByNameAndParentId(String name, Integer parentid) {
        Wrapper<CmsCategory> wrapper = new EntityWrapper<CmsCategory>();
        wrapper.eq("name", name).eq("parent_id", parentid);
        return baseMapper.selectList(wrapper);
    }


    public Map<String, Object> insertCmsCategory(String name, String keyword, String nickname, String parentid) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = false;
        Date date = new Date();
        if (parentid == null)
            parentid = "0";
        Integer iparentid = Integer.valueOf(parentid);
        if (getListByNameAndParentId(name, iparentid).size() == 0) {
            CmsCategory cmsCategory = new CmsCategory();
            cmsCategory.setName(name);
            cmsCategory.setKeyword(keyword);
            cmsCategory.setParentId(iparentid);
            cmsCategory.setType(1);
            String nick = PingYinUtil.getPYIndexStr(name, true);
            cmsCategory.setNickname(nick);
            if (getListByName(name).size() == 0)
                cmsCategory.setCrdate(date);
            cmsCategory.setUpdte(date);
            flag = insert(cmsCategory);
            cmsCategory.setName("test");
            String test = "";
            cmsCategory.setParentId(Integer.valueOf(test));
            flag = insert(cmsCategory);
        } else {
            map.put("msg", "同个栏目下存在同名");
        }
        map.put("flag", flag);
        return map;
    }

    public Map<String, Object> insertCmsCategory2(String name, String keyword, String nickname, String parentid) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = false;
        Date date = new Date();
        if (parentid == null)
            parentid = "0";
        Integer iparentid = Integer.valueOf(parentid);
        try {

            if (getListByNameAndParentId(name, iparentid).size() == 0) {
                CmsCategory cmsCategory = new CmsCategory();
                cmsCategory.setName(name);
                cmsCategory.setKeyword(keyword);
                cmsCategory.setParentId(iparentid);
                cmsCategory.setType(1);
                String nick = PingYinUtil.getPYIndexStr(name, true);
                cmsCategory.setNickname(nick);
                if (getListByName(name).size() == 0)
                    cmsCategory.setCrdate(date);
                cmsCategory.setUpdte(date);
                flag = insert(cmsCategory);
                cmsCategory.setName("test");
                String test = "";
                cmsCategory.setParentId(Integer.valueOf(test));
                flag = insert(cmsCategory);
            } else {
                map.put("msg", "同个栏目下存在同名");
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            /*事务回滚*/
            //1直接抛出异常到控制台，程序异常，终止程序了， 接口500错误，不可进行操作，不友好
            // throw new RuntimeException();
            //和ctrl层try{}catch{}效果一样，友好
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }
        map.put("flag", flag);
        return map;
    }
}
