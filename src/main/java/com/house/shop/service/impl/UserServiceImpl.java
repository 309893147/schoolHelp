package com.house.shop.service.impl;

import com.house.shop.mapper.UserDetailMapperCustom;
import com.house.shop.mapper.UserMapper;
import com.house.shop.mapper.UserMapperCustom;
import com.house.shop.pojo.User;
import com.house.shop.pojo.UserDetail;
import com.house.shop.pojo.UserExample;
import tk.mybatis.mapper.entity.Example.Criteria;
import com.house.shop.service.UserService;

import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserMapperCustom userMapperCustom;

//	@Autowired
//	private UserDetailMapperCustom userDetailMapperCustom;
//	
//	// 查询用户详情带上用户信息
//    public UserDetail getAllWithUser(String userId) {
//        // TODO Auto-generated method stub
//        return userDetailMapperCustom.wxselectByDetailWithUser(userId);
//    }
	
	// 检查登陆信息
	public User checkLogin(User user) {
		// TODO Auto-generated method stub
		User userinfo = userMapperCustom.login(user);
		return userinfo;
	}

	// 注册
	public int insert(User user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}

	// 检验用户名是否可用
	// 如果等于0 return true ,代表可用
	public boolean checkUsername(String username) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		long count = userMapper.selectCountByExample(userExample);
		return count == 0;

	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userMapper.selectByExample(null);
	}

	// 删除
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		userMapper.deleteByPrimaryKey(id);
	}

	public void deleteBatch(List<String> ids) {
		// TODO Auto-generated method stub
		UserExample user = new UserExample();// 定义一个条件
		UserExample.Criteria criteria = user.createCriteria();
		// delete from xx from exp_id in(1,2,3)
		criteria.andUserIdIn(ids);
		userMapper.deleteByExample(user);// 按照条件删除
	}

	// 用户信息保存
	public int saveUser(User user) {
		return userMapper.insertSelective(user);

	}

	// 按照用户ID查询用户
	public User getUser(String id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	// 修改用户
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void updateUserInfo(User user) {
		Example userExample = new Example(User.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("userId", user.getUserId());
		userMapper.updateByExampleSelective(user, userExample);

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public User queryUserInfo(String userId) {
		Example userExample = new Example(User.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("userId", userId);
		User user = userMapper.selectOneByExample(userExample);
		return user;
	}

}
