package com.house.shop.service;


import com.house.shop.pojo.User;
import com.house.shop.pojo.UserDetail;

import java.util.List;

public interface UserService {
//	//wx详情页查询
//	 public UserDetail getAllWithUser(String userId);
	
    public User checkLogin(User user);
    public int insert(User user);
    public List<User> getAll();
    // 删除
    public void deleteUser(String id);
    // 用户信息保存
    public int saveUser(User user);
    // 按照用户ID查询用户
    public User getUser(String id);

    // 修改用户
    public void updateUser(User user);

    public boolean checkUsername(String username);

    public void deleteBatch(List<String> ids);
    
    public void updateUserInfo(User user);
    
    //查询用户信息

    public User queryUserInfo(String userId);

}


