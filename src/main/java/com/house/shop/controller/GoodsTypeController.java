package com.house.shop.controller;


import com.house.shop.pojo.GoodsType;
import com.house.shop.pojo.Msg;
import com.house.shop.service.GoodsTypeService;
import com.house.shop.utils.JsonResult;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class GoodsTypeController {

	// 自动注入
	@Autowired
	GoodsTypeService goodsTypeService;

	

	@RequestMapping(value = "/getGoodsType", method = RequestMethod.GET)
	public String getGoodsType(Model model) {
		//得到商品类型集合
		List<GoodsType> goodsType = goodsTypeService.getGoodsType();
		model.addAttribute("goodsType", goodsType);
		return "root/backstage_type";
	}
	@ResponseBody
	@RequestMapping(value = "/wxgetGoodsType", method = RequestMethod.GET)
	public JsonResult wxgetGoodsType() {
		//得到商品类型集合
		List<GoodsType> goodsType = goodsTypeService.getGoodsType();
		return JsonResult.ok(goodsType);
	}


	//添加
	@RequestMapping(value="/addtype",method=RequestMethod.POST)
	public String saveGoodsType(@Valid GoodsType goodsType) {//Valid 后台校验 代表对象封装后 数据需要校验

		goodsTypeService.saveGoodsType(goodsType);
		return "root/backstage_addtypesuccess";
	}

	//编辑,根据id查询商品信息
	
	@RequestMapping("/selectType{goodsTypeId}")
	public String getGoodsTypeById(Integer goodsTypeId,Model model) {//id是从路径value 取出
		GoodsType goodsType= goodsTypeService.getGoodsTypeById(goodsTypeId);
		model.addAttribute("goodsType",goodsType);
		return "root/backstage_updatetype";

	}
	//修改
	@ResponseBody
	@RequestMapping(value="/updateGoodsType/{goodsTypeId}",method=RequestMethod.PUT)
	public String updateGoods(@Valid GoodsType goodsType) {//id是从路径value 取出

		goodsTypeService.updateGoodsType(goodsType);
		return null;
	}
	
	// 单个和批量二合一 批量删除:1-2-3
	@ResponseBody
	@RequestMapping(value="/deletetype",method=RequestMethod.DELETE)
	public Msg deleteGoodsType(Integer goodsTypeId) {// id是从路径value 取出 // 批量删除

		goodsTypeService.deleteGoodsType(goodsTypeId);

		return Msg.success();

	}
}
