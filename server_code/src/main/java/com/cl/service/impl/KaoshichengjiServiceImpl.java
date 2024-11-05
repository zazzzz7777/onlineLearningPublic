package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.KaoshichengjiDao;
import com.cl.entity.KaoshichengjiEntity;
import com.cl.service.KaoshichengjiService;
import com.cl.entity.view.KaoshichengjiView;

@Service("kaoshichengjiService")
public class KaoshichengjiServiceImpl extends ServiceImpl<KaoshichengjiDao, KaoshichengjiEntity> implements KaoshichengjiService {
	
	@Override
	public PageUtils queryPageGroupBy(Map<String, Object> params, Wrapper<KaoshichengjiEntity> wrapper) {
		Page<KaoshichengjiView> page =new Query<KaoshichengjiView>(params).getPage();
        page.setRecords(baseMapper.selectGroupBy(page,wrapper));
    	PageUtils pageUtil = new PageUtils(page);
    	return pageUtil;
	}
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KaoshichengjiEntity> page = this.selectPage(
                new Query<KaoshichengjiEntity>(params).getPage(),
                new EntityWrapper<KaoshichengjiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<KaoshichengjiEntity> wrapper) {
		  Page<KaoshichengjiView> page =new Query<KaoshichengjiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<KaoshichengjiView> selectListView(Wrapper<KaoshichengjiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KaoshichengjiView selectView(Wrapper<KaoshichengjiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<KaoshichengjiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<KaoshichengjiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<KaoshichengjiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> kaoshimingchengfenshuSectionStat(Map<String, Object> params, Wrapper<KaoshichengjiEntity> wrapper) {
        return baseMapper.kaoshimingchengfenshuSectionStat(params, wrapper);
    }



}
