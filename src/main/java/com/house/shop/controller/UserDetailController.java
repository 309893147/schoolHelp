package com.house.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.shop.pojo.Msg;
import com.house.shop.pojo.UserDetail;
import com.house.shop.service.UserDetailService;
import com.house.shop.utils.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserDetailController { // 自动注入
	@Autowired
	UserDetailService userDetailService;

	// 返回json数据串
	@ResponseBody
	@RequestMapping("/userDetailList") // ajax方法
	public Msg getUserDetailWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 引入PageHelper分页插件
		// 查询前调用，传入页码和记录数
		PageHelper.startPage(pn, 7);
		// startPage紧跟着的这个查询就是一个分页查询
		List<UserDetail> userDetail = userDetailService.getAllWithUser();
		// PageInfo包装查询结果，封装了详细的分页信息和详细数据
		// 连续显示9页
		PageInfo pageInfo = new PageInfo(userDetail, 7);
		return Msg.success().add("pageInfo", pageInfo);
	}

	// 单个和批量二合一 批量删除:1-2-3
	@ResponseBody
	@RequestMapping(value = "/deleteUserDetail/{id}", method = RequestMethod.DELETE)
	public Msg deleteUserDetail(@PathVariable("id") Integer id) {// id是从路径value 取出 // 批量删除

		userDetailService.deleteUserDetail(id);

		return Msg.success();

	}

	@ResponseBody
	@RequestMapping(value = "/deleteDirection", method = RequestMethod.DELETE)
	public JsonResult wxdeleteUserDetail(Integer id) {// id是从路径value 取出 // 批量删除

		userDetailService.deleteUserDetail(id);

		return JsonResult.ok();

	}

	// 添加地址
	@ResponseBody
	@RequestMapping(value = "/addDirection", method = RequestMethod.POST)
	public JsonResult saveUser(UserDetail userDetail) {
		userDetailService.saveUserDetail(userDetail);
		return JsonResult.ok();
	}

	// 返回json数据串
	@ResponseBody
	@RequestMapping("/userOwnDetailByjson") // ajax方法
	public JsonResult getUserOwnDetailWithJson(String userId) {
		List<UserDetail> userOwnDetail = userDetailService.getOwn(userId);

		return JsonResult.ok(userOwnDetail);
	}

//    @RequestMapping("/userOwnDetail/{userId}")
//    public String getUserOwnDetail(@PathVariable("userId") Integer userId, Model model) {
//        List<UserDetail> userOwnDetail = userDetailService.getOwn(userId);
//        model.addAttribute("userOwnDetail", userOwnDetail);
//        return "/user/user_direction";
//    }
//
	// 修改地址
	// 返回json数据串
	@ResponseBody
	@RequestMapping(value = "/updateUserDetail", method = RequestMethod.PUT)
	public JsonResult updateUserDetail(UserDetail userDetail) {// id是从路径value 取出

		userDetailService.updateUserDetail(userDetail);
		return JsonResult.ok();

	}

}