package com.house.shop.service;


import com.house.shop.pojo.User;
import com.house.shop.pojo.UserDetail;

import java.util.List;

public interface UserDetailService {
    // 查询用户详情带上用户信息
    public List<UserDetail> getAllWithUser();
    // 查询用户自己的地址信息
    public List<UserDetail> getOwn(String userId);
    // 删除
    public void deleteUserDetail(Integer id);
//批量删除
//    public void deleteBatch(List<Integer> ids);
//
    // 用户地址信息保存
    public int saveUserDetail(UserDetail userDetail);
//
    // 地址 进行修改
    public void updateUserDetail(UserDetail userDetail);
}


