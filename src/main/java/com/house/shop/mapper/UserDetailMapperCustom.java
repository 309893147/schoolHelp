package com.house.shop.mapper;


import com.house.shop.pojo.UserDetail;


import java.util.List;

public interface UserDetailMapperCustom {
	/*
	 * ��ѯ�����û���Ϣ
	 */
	List<UserDetail> selectByDetailWithUser(UserDetail userDetail);
	
//	UserDetail wxselectByDetailWithUser(String userId);

//	UserDetail selectByPrimaryKeyWithUser(Integer userDetailId);

	List<UserDetail> selectByPrimaryKeyWithUserOwn(String userId);

}