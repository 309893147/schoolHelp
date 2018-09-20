package com.house.shop.controller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.house.shop.pojo.Goods;
import com.house.shop.pojo.User;
import com.house.shop.service.UserService;
import com.house.shop.utils.JsonResult;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	private UserService userService;
	
	@Value("${file-upload-path}")
	private String fileUploadPath;
	
	@Value("${wxfile-upload-path}")
	private String wxfileUploadPath;

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param response
	 * @param file     上传的文件，支持多文件
	 * @throws Exception
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> uploadFile(MultipartFile img, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {

		// 原始名称
		String oldFileName = img.getOriginalFilename(); // 获取上传文件的原名
//            //存到本地服务器上
//            String uploadPath = request.getSession().getServletContext().getRealPath("/");
		// 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
		// String saveFilePath =
		// "D://workspace//ssm_shoping//src//main//webapp//static//images//waterfall";
		// 上传图片
		if (img != null && oldFileName != null && oldFileName.length() > 0) {
			// 新的图片名称
			String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
			// 新图片
			File newFile = new File(fileUploadPath + "\\" + newFileName);
			System.out.println(newFile);
			// 将内存中的数据写入磁盘
			img.transferTo(newFile);

			// 将新图片名称返回到前端
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", "成功啦");
			map.put("url", newFileName);
			return map;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "图片不合法");
			return map;
		}
	}
	
	@ResponseBody
	@PostMapping("/uploadGoods")
	public JsonResult uploadGoods( @RequestParam("file") MultipartFile img) throws Exception {
		// 原始名称
				String oldFileName = img.getOriginalFilename(); // 获取上传文件的原名
				
				
					// 新的图片名称
					String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
					// 新图片
					File newFile = new File(fileUploadPath + "\\" + newFileName);
					System.out.println(newFile);
					// 将内存中的数据写入磁盘
					img.transferTo(newFile);
				
					return JsonResult.ok(newFileName);
		
	}

	@ResponseBody
	@PostMapping("/uploadFace")
	public JsonResult uploadFace(String userId, @RequestParam("file") MultipartFile[] files) throws Exception {

		if (StringUtils.isBlank(userId)) {
			return JsonResult.errorMsg("用户id不能为空...");
		}

		// 文件保存的命名空间
		String fileSpace =wxfileUploadPath;
		// 保存到数据库中的相对路径
		String uploadPathDB = "/" + userId + "/face";

		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		try {
			if (files != null && files.length > 0) {

				String fileName = files[0].getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					// 文件上传的最终保存路径
					String finalFacePath = fileSpace + uploadPathDB + "/" + fileName;
					// 设置数据库保存的路径
					uploadPathDB += ("/" + fileName);

					File outFile = new File(finalFacePath);
					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
						// 创建父文件夹
						outFile.getParentFile().mkdirs();
					}

					fileOutputStream = new FileOutputStream(outFile);
					inputStream = files[0].getInputStream();
					IOUtils.copy(inputStream, fileOutputStream);
				}

			} else {
				return JsonResult.errorMsg("上传出错...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.errorMsg("上传出错...");
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		}

		User user = new User();
		user.setUserId(userId);
		user.setFaceImage(uploadPathDB);
		userService.updateUserInfo(user);

		return JsonResult.ok(uploadPathDB);
	}
	

}