package com.house.shop.controller;

import com.house.shop.pojo.User;
import com.house.shop.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;

@Controller
@RequestMapping("/root")
public class RootController {

	// 正常访问backstage_user页面
	@RequestMapping("/backstage_user")
	public String backstage_user() {
		return "root/backstage_user";
	}

	// 正常访问backstage_user页面
	@RequestMapping("/backstage_userdetail")
	public String backstage_userdetail() {
		return "root/backstage_userdetail";
	}

	// 正常访问backstage_addtype页面
	@RequestMapping("/backstage_addtype")
	public String backstage_addtype() {
		return "root/backstage_addtype";
	}

	// 正常访问backstage_addtype页面
	@RequestMapping("/backstage_product")
	public String backstage_product() {
		return "root/backstage_product";
	}

	// 正常访问backstage_addtype页面
	@RequestMapping("/backstage_updateproduct")
	public String backstage_updateproduct() {
		return "root/backstage_updateproduct";
	}

	// 正常访问backstage_addproduct页面
	@RequestMapping("/backstage_addproduct")
	public String backstage_addproduct() {
		return "root/backstage_addproduct";
	}

	// 正常访问backstage_type页面
	@RequestMapping("/backstage_type")
	public String backstage_type() {
		return "root/backstage_type";
	}
	

	

//	// 正常访问backstage_updatetype页面
//	@RequestMapping("/backstage_updatetype")
//	public String backstage_updatetype() {
//		return "root/backstage_updatetype";
//	}

}