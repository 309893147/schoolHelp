package com.house.shop.service.impl;

import com.house.shop.mapper.UserDetailMapper;
import com.house.shop.mapper.UserDetailMapperCustom;
import com.house.shop.pojo.UserDetail;

import com.house.shop.service.UserDetailService;
import com.house.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private UserDetailMapper userDetailMapper;

    @Autowired
    private UserDetailMapperCustom userDetailMapperCustom;

    // 查询用户详情带上用户信息
    public List<UserDetail> getAllWithUser() {
        // TODO Auto-generated method stub
        return userDetailMapperCustom.selectByDetailWithUser(null);
    }

    // 查询用户自己的地址信息
    public List<UserDetail> getOwn(String userId) {

        return userDetailMapperCustom.selectByPrimaryKeyWithUserOwn(userId);
    }
//
    // 删除
    public void deleteUserDetail(Integer id) {
        // TODO Auto-generated method stub
        userDetailMapper.deleteByPrimaryKey(id);
    }
//
//    public void deleteBatch(List<Integer> ids) {
//        // TODO Auto-generated method stub
//        UserDetailExample userDetail = new UserDetailExample();// 定义一个条件
//        UserDetailExample.Criteria criteria = userDetail.createCriteria();
//        // delete from xx from exp_id in(1,2,3)
//        criteria.andUserIdIn(ids);
//        userDetailMapper.deleteByExample(userDetail);// 按照条件删除
//    }
//
    // 用户地址信息保存
    public int saveUserDetail(UserDetail userDetail) {
        return userDetailMapper.insertSelective(userDetail);

    }

    // 地址 进行修改
    public void updateUserDetail(UserDetail userDetail) {
        // TODO Auto-generated method stub
        userDetailMapper.updateByPrimaryKeySelective(userDetail);
    }

}
