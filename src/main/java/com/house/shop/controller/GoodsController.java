package com.house.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.shop.pojo.Goods;
import com.house.shop.pojo.Msg;
import com.house.shop.pojo.UserDetail;
import com.house.shop.service.GoodsService;
import com.house.shop.utils.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GoodsController {
	// 自动注入
	@Autowired
	GoodsService goodsService;

	// 返回json数据串
	@ResponseBody
	@RequestMapping("/goodsWithType") // ajax方法
	public Msg getGoodsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 引入PageHelper分页插件
		// 查询前调用，传入页码和记录数
		PageHelper.startPage(pn, 3);
		// startPage紧跟着的这个查询就是一个分页查询
		List<Goods> goods = goodsService.getAllWithType();
		// PageInfo包装查询结果，封装了详细的分页信息和详细数据
		// 连续显示5页
		PageInfo pageInfo = new PageInfo(goods, 3);
		return Msg.success().add("pageInfo", pageInfo);
	}

	// 保存

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String saveGoods(@Valid Goods goods, HttpServletRequest request, Model model) {// Valid 后台校验 代表对象封装后 数据需要校验

		System.out.println("a" + goods.getGoodsPic());
		goodsService.saveGoods(goods);
		return "/root/backstage_product";
	}

	// 微信发布闲置物品
	@ResponseBody
	@RequestMapping(value = "/wxaddproduct", method = RequestMethod.POST)
	public JsonResult addGoods(Goods goods) {// Valid 后台校验 代表对象封装后 数据需要校验

		goodsService.saveGoods(goods);
		return JsonResult.ok();
	}

	// 单个
	@ResponseBody
	@RequestMapping(value = "/goods/{id}", method = RequestMethod.DELETE)
	public Msg deleteExp(@PathVariable("id") Integer id) {// id是从路径value 取出

		goodsService.deleteGoods(id);

		return Msg.success();

	}
	
	// 单个
		@ResponseBody
		@RequestMapping(value = "/wxDeletegoods", method = RequestMethod.DELETE)
		public JsonResult wxDeletegoods(Integer id) {// id是从路径value 取出

			goodsService.deleteGoods(id);

			return JsonResult.ok();

		}

	// 编辑,根据id查询商品信息

	@RequestMapping(value = "/goods{id}", method = RequestMethod.GET)
	public String getGoodsById(Integer id, Model model) {// id是从路径value 取出
		Goods goods = goodsService.getGoodsById(id);
		model.addAttribute(goods);
		return "root/backstage_updateproduct";

	}

	// 返回json数据串 index发出的请求
	@ResponseBody
	@RequestMapping(value = "/getAllGoods", method = RequestMethod.GET) // ajax方法
	public Msg getAllGoodsWithJson() {

		List<Goods> goods = goodsService.getAllGoods();

		return Msg.success().add("goods", goods);
	}
	
	@ResponseBody
	@RequestMapping(value = "/wxgetOwnGoods", method = RequestMethod.GET) // ajax方法
	public JsonResult getOwnGoodsWithJson(String userId) {

		List<Goods> goods = goodsService.getOwnGoods(userId);

		return JsonResult.ok(goods);
	}

	// 返回json数据串 book分页发出的请求
	@ResponseBody
	@RequestMapping(value = "/getAllGoodsByType", method = RequestMethod.GET) // ajax方法
	public JsonResult getAllGoodsByTypeWithJson(Integer goodsTypeId) {

		List<Goods> goods = goodsService.getAllGoodsByType(goodsTypeId);

		return JsonResult.ok(goods);
	}
//

	// 商品更新方法
	// 如果直接发送ajax put 请求 封装的对象 全是null
	// 要能支持直接发送put请求 需要配置 HttpPutContentFilter
	// 他将请求体重的数据解析包装成一个map.request被重新包装 request.getPARAMETER()被重写
	@ResponseBody
	@RequestMapping(value = "/updateGoods/{goodsId}", method = RequestMethod.PUT)
	public String updateGoods(@Valid Goods goods, HttpServletRequest request, Model model) {// id是从路径value 取出

		goodsService.updateGoods(goods);
		return null;

	}
	
	@ResponseBody
	@RequestMapping(value = "/wxupdateGoods", method = RequestMethod.PUT)
	public JsonResult wxupdateGoods( Goods goods) {// id是从路径value 取出

		goodsService.updateGoods(goods);
		return JsonResult.ok();
	}
}
