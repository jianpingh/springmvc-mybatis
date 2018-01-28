package com.hjp.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hjp.ssm.exception.CustomException;
import com.hjp.ssm.po.ItemsCustom;
import com.hjp.ssm.po.ItemsQueryVo;
import com.hjp.ssm.po.UserInfo;
import com.hjp.ssm.service.ItemsService;

@Controller
public class itemsController {

	@Autowired
	private ItemsService itemsService;

	// 商品分类
	// itemtypes表示最终将方法返回值放在request中的key
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes() {

		Map<String, String> itemTypes = new HashMap<String, String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		itemTypes.put("103", "图书");
		
		return itemTypes;
	}

	// 方法一、返回ModelAndView
	/*
	 * @RequestMapping("/itemsView") public ModelAndView
	 * itemsView(@RequestParam(value = "id", required = true) Integer items_id)
	 * throws Exception {
	 * 
	 * ItemsCustom item=itemsService.findItemsById(items_id); ModelAndView
	 * modelAndView = new ModelAndView();
	 * 
	 * //将商品信息放到model modelAndView.addObject("itemsCustom", item);
	 * modelAndView.setViewName("items/item");
	 * 
	 * return modelAndView; }
	 */

	// 方法二、返回逻辑视图名
	@RequestMapping("/itemsView")
	// public String itemView(Model model,@RequestParam(value = "id", required =
	// true) Integer items_id) throws Exception{
	public String itemView(Model model, @RequestParam(value = "id", required = true) Integer items_id)
			throws Exception {
		ItemsCustom item = itemsService.findItemsById(items_id);

		model.addAttribute("itemsCustom", item);
		return "items/item";
	}

	// 查询商品信息，输出json
	/// itemsView/{id}里边的{id}表示占位符，通过@PathVariable获取占位符中的参数，
	// 如果占位符中的名称和形参名一致，在@PathVariable可以不指定名称
	@RequestMapping("/itemsViewJson/{id}")
	public @ResponseBody ItemsCustom itemsViewJson(@PathVariable("id") Integer id) throws Exception {

		// 调用service查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(id);

		return itemsCustom;
	}

	// 查询所有商品信息，输出json
	@RequestMapping("/queryItems")
	@ResponseBody
	public List<ItemsCustom> queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {

		// 调用service查询所有商品信息
		List<ItemsCustom> itemsList = itemsService.findAllItems();

		return itemsList;
	}

	// 查询所有商品信
	@RequestMapping("/queryList")
	public String queryItemsPage(Model model) throws Exception {

		// 调用service查询所有商品信息
		List<ItemsCustom> itemsList = itemsService.findAllItems();

		model.addAttribute("itemsList" , itemsList);
		return "items/list";
	}
	
	// 查询所有商品信-批量修改商品信息用
	@RequestMapping("/queryListAll")
	public String queryItemsAll(Model model,HttpServletRequest request,
			ItemsQueryVo itemsQueryVo) throws Exception {

		// 调用service查询所有商品信息
		List<ItemsCustom> itemsList = itemsService.findAllItems();

		model.addAttribute("itemsList" , itemsList);
		return "items/editItemsQuery";

	}
	
	// 查询所有商品信
    @RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_id) throws Exception {

    	if(items_id!=null&&items_id.length>0){
			//刪除所有商品信息...
			return "success";
    	}else{
    		throw new CustomException("删除时，未选中商品！");
    	}
	}
	
	// 批量修改商品页面，将商品信息查询出来，在页面中可以编辑商品信息
	/*@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(HttpServletRequest request,
			ItemsQueryVo itemsQueryVo) throws Exception {

		// 调用service查找 数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);

		modelAndView.setViewName("items/editItemsQuery");

		return modelAndView;

	}*/
	
	// 修改商品信息
	@RequestMapping("/editItem")
	public String editItem(Model model, @RequestParam(value = "items_id", required = true) Integer id) throws Exception {
		// 调用service查询所有商品信息
		
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		model.addAttribute("item",itemsCustom);
		return "items/edititem";
	}	
	
	@RequestMapping("/editItemSubmit")
	public String editItemSubmit(
			Model model,
			HttpServletRequest request,
			@Validated ItemsCustom itemsCustom,BindingResult bindingResult,
			Integer id){
		    
		//获取校验错误信息
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			//输出错误信息
			for(ObjectError objectError :allErrors){
				//输出错误信息
				System.out.println(objectError.getDefaultMessage());
			}
			
			//将错误信息传递到页面
			model.addAttribute("errors", allErrors);
			
			//出错重新到商品修改页面
			return "items/edititem";
		}
		
		return "success";
	}	
	
	//批量修改商品提交
	//通过ItemsQueryVo接收批量商品提交信息，将商品信息保存到	ItemsQueryVo的itemsList的属性中
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
		
		return "success";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
