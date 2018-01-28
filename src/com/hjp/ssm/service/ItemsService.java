package com.hjp.ssm.service;

import java.util.List;

import com.hjp.ssm.po.ItemsCustom;

public interface ItemsService {

	ItemsCustom findItemsById(Integer id) throws Exception;
	
	List<ItemsCustom> findAllItems() throws Exception;
}
