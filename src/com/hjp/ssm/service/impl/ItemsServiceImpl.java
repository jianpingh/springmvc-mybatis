package com.hjp.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hjp.ssm.mapper.ItemsMapper;
import com.hjp.ssm.po.Items;
import com.hjp.ssm.po.ItemsCustom;
import com.hjp.ssm.service.ItemsService;


public class ItemsServiceImpl implements ItemsService{

	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		
		Items items = itemsMapper.selectByPrimaryKey(id);

		ItemsCustom itemsCustom = null;
		//将items的属性值拷贝到itemsCustom
		if(items!=null){
			itemsCustom = new ItemsCustom();
			BeanUtils.copyProperties(items, itemsCustom);
		}
		
		return itemsCustom;
	}
	
	@Override
    public List<ItemsCustom> findAllItems() throws Exception{
		
		List<ItemsCustom> itemsList=itemsMapper.selectAllItems();
		
		return itemsList;
	}
}
