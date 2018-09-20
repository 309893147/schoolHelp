package com.house.shop.pojo;

import javax.persistence.*;

@Table(name = "user_detail")
public class UserDetail {
    /**
     * 用户详情id
     */
    @Id
    @Column(name = "user_detail_id")
    private Integer userDetailId;

    /**
     * 收件人姓名
     */
    private String addressee;

    /**
     * 收件人电话
     */
    @Column(name = "addressee_phone")
    private String addresseePhone;

    /**
     * 收件人地址
     */
    private String direction;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    //联合user表查询信息
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 获取用户详情id
     *
     * @return user_detail_id - 用户详情id
     */
    public Integer getUserDetailId() {
        return userDetailId;
    }

    /**
     * 设置用户详情id
     *
     * @param userDetailId 用户详情id
     */
    public void setUserDetailId(Integer userDetailId) {
        this.userDetailId = userDetailId;
    }

    /**
     * 获取收件人姓名
     *
     * @return addressee - 收件人姓名
     */
    public String getAddressee() {
        return addressee;
    }

    /**
     * 设置收件人姓名
     *
     * @param addressee 收件人姓名
     */
    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    /**
     * 获取收件人电话
     *
     * @return addressee_phone - 收件人电话
     */
    public String getAddresseePhone() {
        return addresseePhone;
    }

    /**
     * 设置收件人电话
     *
     * @param addresseePhone 收件人电话
     */
    public void setAddresseePhone(String addresseePhone) {
        this.addresseePhone = addresseePhone;
    }

    /**
     * 获取收件人地址
     *
     * @return direction - 收件人地址
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 设置收件人地址
     *
     * @param direction 收件人地址
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "UserDetail [userDetailId=" + userDetailId + ", addressee=" + addressee + ", addresseePhone="
				+ addresseePhone + ", direction=" + direction + ", userId=" + userId + ", user=" + user + "]";
	}
    
}