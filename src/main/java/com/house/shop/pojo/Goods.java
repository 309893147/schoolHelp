package com.house.shop.pojo;

import javax.persistence.*;

public class Goods {
    /**
     * 商品Id
     */
    @Id
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品价格
     */
    @Column(name = "goods_price")
    private String goodsPrice;

    /**
     * 商品描述
     */
    @Column(name = "goods_desc")
    private String goodsDesc;

    /**
     * 库存数量
     */
    @Column(name = "kc_number")
    private String kcNumber;

    /**
     * 商品图片
     */
    @Column(name = "goods_pic")
    private String goodsPic;

    /**
     * 类型id
     */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;
    
    //希望查询商品的同时类型信息一起查询
    private GoodsType goodsType;

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 获取商品Id
     *
     * @return goods_id - 商品Id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品Id
     *
     * @param goodsId 商品Id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品价格
     *
     * @return goods_price - 商品价格
     */
    public String getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置商品价格
     *
     * @param goodsPrice 商品价格
     */
    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取商品描述
     *
     * @return goods_desc - 商品描述
     */
    public String getGoodsDesc() {
        return goodsDesc;
    }

    /**
     * 设置商品描述
     *
     * @param goodsDesc 商品描述
     */
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    /**
     * 获取库存数量
     *
     * @return kc_number - 库存数量
     */
    public String getKcNumber() {
        return kcNumber;
    }

    /**
     * 设置库存数量
     *
     * @param kcNumber 库存数量
     */
    public void setKcNumber(String kcNumber) {
        this.kcNumber = kcNumber;
    }

    /**
     * 获取商品图片
     *
     * @return goods_pic - 商品图片
     */
    public String getGoodsPic() {
        return goodsPic;
    }

    /**
     * 设置商品图片
     *
     * @param goodsPic 商品图片
     */
    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    /**
     * 获取类型id
     *
     * @return type_id - 类型id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置类型id
     *
     * @param typeId 类型id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
}