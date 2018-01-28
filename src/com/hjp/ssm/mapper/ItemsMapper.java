package com.hjp.ssm.mapper;

import java.util.List;

import com.hjp.ssm.po.Items;
import com.hjp.ssm.po.ItemsCustom;

public interface ItemsMapper {

	Items selectByPrimaryKey(Integer Id);
	
	List<ItemsCustom> selectAllItems();
}
