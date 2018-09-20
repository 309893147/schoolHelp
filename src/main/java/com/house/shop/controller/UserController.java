package com.house.shop.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.shop.pojo.Msg;
import com.house.shop.pojo.User;
import com.house.shop.pojo.UserDetail;
import com.house.shop.service.UserService;
import com.house.shop.utils.JsonResult;

import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
	// 自动注入
	@Autowired
	UserService userService;

	@Autowired
	private Sid sid;
	// 正常访问login页面
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// 正常访问login页面
	@RequestMapping("/register")
	public String register() {
		return "register";
	}



	// 登陆表单提交过来的路径
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public String checkLogin(HttpServletRequest request) throws Exception {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String smi = convertMD5(password);
		User user = new User();

		user.setUsername(username);
		user.setPassword(smi);
		User resultUser = userService.checkLogin(user);

		if (resultUser == null) {
			// 返回登录界面
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "请认真核对账号、密码！");
			return "login";
		} else {
			String type = resultUser.getUserType();
			// 跳到主界面
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", resultUser);
			if (type.equals("1")) {
				// type为1 跳转到管理员界面
				return "root/backstage_admin";
			} else {
				// 跳转到商城页面
				return "index";

			}

		}
	}
	@ResponseBody
	@PostMapping("/wxlogin")
    public JsonResult login(@RequestBody User user) throws java.lang.Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        String smi = convertMD5(password);
        user.setPassword(smi);
//		Thread.sleep(3000);

        // 1. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JsonResult.ok("用户名或密码不能为空...");
        }

        // 2. 判断用户是否存在
        User userResult = userService.checkLogin(user);

        // 3. 返回
        if (userResult != null) {
            userResult.setPassword("");

            return JsonResult.ok(userResult);
        } else {
            return JsonResult.errorMsg("用户名或密码不正确, 请重试...");
        }
    }

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String insertUser(User user, HttpServletRequest request) throws Exception {
		String userId = sid.nextShort();
		String password = user.getPassword();
		String smi = convertMD5(password);
		user.setPassword(smi);
		String userType = request.getParameter("userType");
		user.setUserType("0");

		user.setUserId(userId);
		userService.insert(user);
		return "login";
	}
	
	@ResponseBody
	@PostMapping("/wxregist")
    public JsonResult regist(@RequestBody User user) throws java.lang.Exception {
        //1.判断用户名和密码必须不为空
        if(StringUtils.isBlank(user.getUsername() ) || StringUtils.isBlank(user.getPassword())){
            return JsonResult.errorMsg("用户名和密码不能为空");
        }
        //2.判断用户名是否存在
        boolean usernameIsExist=userService.checkUsername(user.getUsername());
        //3,保存用户,注册信息
        if(usernameIsExist){
        	String userId = sid.nextShort();
            user.setNickname(user.getUsername());
            String password = user.getPassword();
    		String smi = convertMD5(password);
            user.setPassword(smi);
            user.setEmail(user.getEmail());
            user.setUserId(userId);
            user.setUserType("0");
            userService.saveUser(user);
        }else {
            return JsonResult.errorMsg("用户名已经存在");
        }
        user.setPassword("");
        return JsonResult.ok(user);
    }
	@ResponseBody
	@PostMapping("/logout")
    public JsonResult logout(String userId,HttpServletRequest request) {
        // 移除session
        HttpSession session = request.getSession();

        session.removeAttribute(userId);
        return JsonResult.ok();
    }

	// 保存
	@ResponseBody
	@RequestMapping(value = "/useradd", method = RequestMethod.POST)
	public Msg saveUser(@Valid User user, BindingResult result) {// Valid 后台校验 代表对象封装后 数据需要校验
		// BindingResult封装校验的结果
		if (result.hasErrors()) {
			// 校验失败,返回失败,在模态框中显示校验失败的错误信息 getFieldErrors拿到所有的错误信息
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名" + fieldError.getField());
				System.out.println("错误信息" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		} else {
			String userId = sid.nextShort();
			user.setUserId(userId);
			userService.saveUser(user);
			return Msg.success();
		}

	}

	// 检查用户名是否可用
	@ResponseBody
	@RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
	public Msg checkUsername(@RequestParam("username") String username) {

		// 数据库用户名重复校验
		boolean b = userService.checkUsername(username);
		if (b) {
			return Msg.success();
		} else {
			return Msg.fail().add("va_msg", "用户名已存在");
		}

	}

	// 返回json数据串
	@ResponseBody
	@RequestMapping("/userList") // ajax方法
	public Msg getUserWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 引入PageHelper分页插件
		// 查询前调用，传入页码和记录数
		PageHelper.startPage(pn, 7);
		// startPage紧跟着的这个查询就是一个分页查询
		List<User> user = userService.getAll();
		// PageInfo包装查询结果，封装了详细的分页信息和详细数据
		// 连续显示9页
		PageInfo pageInfo = new PageInfo(user, 7);
		return Msg.success().add("pageInfo", pageInfo);
	}

	// 单个和批量二合一 批量删除:1-2-3
	@ResponseBody
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
	public Msg deleteExp(@PathVariable("ids") String ids) {// id是从路径value 取出
		// 批量删除
		if (ids.contains("-")) {
			List<String> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			// 组装id的集合
			for (String string : str_ids) {
				del_ids.add(string);
			}
			userService.deleteBatch(del_ids);
		} else {

			userService.deleteUser(ids);
		}

		return Msg.success();

	}

	// 修改用户信息,回显,根据id查询 用户
	@ResponseBody
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
	public Msg getUser(@PathVariable("id") String id) {// id是从路径value 取出
		User user = userService.getUser(id);
		return Msg.success().add("user", user);

	}

	// 用户更新方法
	// 如果直接发送ajax put 请求 封装的对象 全是null
	// 要能支持直接发送put请求 需要配置 HttpPutContentFilter
	// 他将请求体重的数据解析包装成一个map.request被重新包装 request.getPARAMETER()被重写
	@ResponseBody
	@RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.PUT)
	public Msg updateUser(User user) {// id是从路径value 取出

		userService.updateUser(user);
		return Msg.success().add("user", user);

	}
	@ResponseBody
	@PostMapping("/query")
	public JsonResult query(String userId) throws Exception {

		if (StringUtils.isBlank(userId)) {
			return JsonResult.errorMsg("用户id不能为空...");
		}

		User userInfo = userService.queryUserInfo(userId);


		return JsonResult.ok(userInfo);
	}


	// MD5加密算法
	public static String convertMD5(String inStr) throws Exception {

		String MD5 = "";

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = inStr.getBytes();
		byte[] digest = md5.digest(bytes);
		for (int i = 0; i < digest.length; i++) {
			// 摘要字节数组中各个字节的"十六进制"形式.
			String s1 = Integer.toHexString(digest[i]);

			// 如果是8个长度的,把前面的6个f去掉,只获取后面的
			if (s1.length() == 8) {
				s1 = s1.substring(6);
			}
			if (s1.length() == 1) {
				s1 = "0" + s1;
			}
			MD5 += s1;
		}
		return MD5;
	}

}