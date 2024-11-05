package com.cl.entity.view;

import com.cl.entity.ZuoyeleixingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 作业类型
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-01-26 17:31:32
 */
@TableName("zuoyeleixing")
public class ZuoyeleixingView  extends ZuoyeleixingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ZuoyeleixingView(){
	}
 
 	public ZuoyeleixingView(ZuoyeleixingEntity zuoyeleixingEntity){
 	try {
			BeanUtils.copyProperties(this, zuoyeleixingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
