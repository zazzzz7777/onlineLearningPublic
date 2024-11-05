package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.KaoshichengjiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KaoshichengjiView;


/**
 * 考试成绩
 *
 * @author 
 * @email 
 * @date 2024-01-26 17:31:32
 */
public interface KaoshichengjiService extends IService<KaoshichengjiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KaoshichengjiView> selectListView(Wrapper<KaoshichengjiEntity> wrapper);
   	
   	KaoshichengjiView selectView(@Param("ew") Wrapper<KaoshichengjiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KaoshichengjiEntity> wrapper);
   	
   	PageUtils queryPageGroupBy(Map<String, Object> params,Wrapper<KaoshichengjiEntity> wrapper);

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<KaoshichengjiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<KaoshichengjiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<KaoshichengjiEntity> wrapper);

    List<Map<String, Object>> kaoshimingchengfenshuSectionStat(Map<String, Object> params,Wrapper<KaoshichengjiEntity> wrapper);


}

