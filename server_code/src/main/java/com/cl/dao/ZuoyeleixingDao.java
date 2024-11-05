package com.cl.dao;

import com.cl.entity.ZuoyeleixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZuoyeleixingView;


/**
 * 作业类型
 * 
 * @author 
 * @email 
 * @date 2024-01-26 17:31:32
 */
public interface ZuoyeleixingDao extends BaseMapper<ZuoyeleixingEntity> {
	
	List<ZuoyeleixingView> selectListView(@Param("ew") Wrapper<ZuoyeleixingEntity> wrapper);

	List<ZuoyeleixingView> selectListView(Pagination page,@Param("ew") Wrapper<ZuoyeleixingEntity> wrapper);
	
	ZuoyeleixingView selectView(@Param("ew") Wrapper<ZuoyeleixingEntity> wrapper);
	
	List<ZuoyeleixingView> selectGroupBy(Pagination page,@Param("ew") Wrapper<ZuoyeleixingEntity> wrapper);

}
