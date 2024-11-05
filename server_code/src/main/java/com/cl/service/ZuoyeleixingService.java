package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ZuoyeleixingEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZuoyeleixingView;


/**
 * 作业类型
 *
 * @author 
 * @email 
 * @date 2024-01-26 17:31:32
 */
public interface ZuoyeleixingService extends IService<ZuoyeleixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZuoyeleixingView> selectListView(Wrapper<ZuoyeleixingEntity> wrapper);
   	
   	ZuoyeleixingView selectView(@Param("ew") Wrapper<ZuoyeleixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZuoyeleixingEntity> wrapper);
   	
   	PageUtils queryPageGroupBy(Map<String, Object> params,Wrapper<ZuoyeleixingEntity> wrapper);

}

