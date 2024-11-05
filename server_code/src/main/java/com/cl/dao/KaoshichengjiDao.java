package com.cl.dao;

import com.cl.entity.KaoshichengjiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KaoshichengjiView;


/**
 * 考试成绩
 * 
 * @author 
 * @email 
 * @date 2024-01-26 17:31:32
 */
public interface KaoshichengjiDao extends BaseMapper<KaoshichengjiEntity> {
	
	List<KaoshichengjiView> selectListView(@Param("ew") Wrapper<KaoshichengjiEntity> wrapper);

	List<KaoshichengjiView> selectListView(Pagination page,@Param("ew") Wrapper<KaoshichengjiEntity> wrapper);
	
	KaoshichengjiView selectView(@Param("ew") Wrapper<KaoshichengjiEntity> wrapper);
	
	List<KaoshichengjiView> selectGroupBy(Pagination page,@Param("ew") Wrapper<KaoshichengjiEntity> wrapper);

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KaoshichengjiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KaoshichengjiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KaoshichengjiEntity> wrapper);

    List<Map<String, Object>> kaoshimingchengfenshuSectionStat(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KaoshichengjiEntity> wrapper);


}
