package com.house.shop.pojo;

import javax.persistence.*;

public class User {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 我的头像
     */
    @Column(name = "face_image")
    private String faceImage;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 取值0：普通用户，1：管理员，默认为0
     */
    @Column(name = "user_type")
    private String userType;

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

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取我的头像
     *
     * @return face_image - 我的头像
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * 设置我的头像
     *
     * @param faceImage 我的头像
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取取值0：普通用户，1：管理员，默认为0
     *
     * @return user_type - 取值0：普通用户，1：管理员，默认为0
     */
    public String getUserType() {
        return userType;
    }
    /**
     * 设置取值0：普通用户，1：管理员，默认为0
     *
     * @param userType 取值0：普通用户，1：管理员，默认为0
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", faceImage=" + faceImage + ", nickname=" + nickname + ", userType=" + userType + "]";
	}
    
    
}