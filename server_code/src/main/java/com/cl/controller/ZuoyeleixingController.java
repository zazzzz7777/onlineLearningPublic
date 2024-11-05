package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.ZuoyeleixingEntity;
import com.cl.entity.view.ZuoyeleixingView;

import com.cl.service.ZuoyeleixingService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 作业类型
 * 后端接口
 * @author 
 * @email 
 * @date 2024-01-26 17:31:32
 */
@RestController
@RequestMapping("/zuoyeleixing")
public class ZuoyeleixingController {
    @Autowired
    private ZuoyeleixingService zuoyeleixingService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZuoyeleixingEntity zuoyeleixing,
		HttpServletRequest request){
        EntityWrapper<ZuoyeleixingEntity> ew = new EntityWrapper<ZuoyeleixingEntity>();

		PageUtils page = zuoyeleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoyeleixing), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZuoyeleixingEntity zuoyeleixing, 
		HttpServletRequest request){
        EntityWrapper<ZuoyeleixingEntity> ew = new EntityWrapper<ZuoyeleixingEntity>();

		PageUtils page = zuoyeleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoyeleixing), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZuoyeleixingEntity zuoyeleixing){
       	EntityWrapper<ZuoyeleixingEntity> ew = new EntityWrapper<ZuoyeleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zuoyeleixing, "zuoyeleixing")); 
        return R.ok().put("data", zuoyeleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZuoyeleixingEntity zuoyeleixing){
        EntityWrapper< ZuoyeleixingEntity> ew = new EntityWrapper< ZuoyeleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zuoyeleixing, "zuoyeleixing")); 
		ZuoyeleixingView zuoyeleixingView =  zuoyeleixingService.selectView(ew);
		return R.ok("查询作业类型成功").put("data", zuoyeleixingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZuoyeleixingEntity zuoyeleixing = zuoyeleixingService.selectById(id);
		zuoyeleixing = zuoyeleixingService.selectView(new EntityWrapper<ZuoyeleixingEntity>().eq("id", id));
        return R.ok().put("data", zuoyeleixing);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZuoyeleixingEntity zuoyeleixing = zuoyeleixingService.selectById(id);
		zuoyeleixing = zuoyeleixingService.selectView(new EntityWrapper<ZuoyeleixingEntity>().eq("id", id));
        return R.ok().put("data", zuoyeleixing);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZuoyeleixingEntity zuoyeleixing, HttpServletRequest request){
    	zuoyeleixing.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zuoyeleixing);
        zuoyeleixingService.insert(zuoyeleixing);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZuoyeleixingEntity zuoyeleixing, HttpServletRequest request){
    	zuoyeleixing.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zuoyeleixing);
        zuoyeleixingService.insert(zuoyeleixing);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZuoyeleixingEntity zuoyeleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoyeleixing);
        zuoyeleixingService.updateById(zuoyeleixing);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zuoyeleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
