package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.BanjiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BanjiView;


/**
 * 班级
 *
 * @author 
 * @email 
 * @date 2024-01-26 17:31:31
 */
public interface BanjiService extends IService<BanjiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BanjiView> selectListView(Wrapper<BanjiEntity> wrapper);
   	
   	BanjiView selectView(@Param("ew") Wrapper<BanjiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BanjiEntity> wrapper);
   	
   	PageUtils queryPageGroupBy(Map<String, Object> params,Wrapper<BanjiEntity> wrapper);

}

