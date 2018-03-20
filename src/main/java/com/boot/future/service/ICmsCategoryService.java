package com.boot.future.service;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.boot.future.entity.CmsCategory;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ck
 * @since 2018-02-24
 */
public interface ICmsCategoryService extends IService<CmsCategory> {
	
	List<CmsCategory> getListByName(String name);

	List<CmsCategory> getListByNameAndParentId(String name,Integer parentid );
	
	List<CmsCategory> getList();

	Map<String, Object> insertCmsCategory(String name, String keyword, String nickname, String parentid);

	Map<String, Object> insertCmsCategory2(String name, String keyword, String nickname, String parentid);
}
