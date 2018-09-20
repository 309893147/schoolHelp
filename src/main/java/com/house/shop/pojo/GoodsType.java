package com.house.shop.pojo;

import javax.persistence.*;

@Table(name = "goods_type")
public class GoodsType {
    /**
     * 类型id
     */
    @Id
    @Column(name = "goods_type_id")
    private Integer goodsTypeId;

    /**
     * 类型名称
     */
    @Column(name = "goods_type_name")
    private String goodsTypeName;

    /**
     * 类型描述
     */
    @Column(name = "goods_type_desc")
    private String goodsTypeDesc;

    /**
     * 获取类型id
     *
     * @return goods_type_id - 类型id
     */
    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    /**
     * 设置类型id
     *
     * @param goodsTypeId 类型id
     */
    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    /**
     * 获取类型名称
     *
     * @return goods_type_name - 类型名称
     */
    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    /**
     * 设置类型名称
     *
     * @param goodsTypeName 类型名称
     */
    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    /**
     * 获取类型描述
     *
     * @return goods_type_desc - 类型描述
     */
    public String getGoodsTypeDesc() {
        return goodsTypeDesc;
    }

    /**
     * 设置类型描述
     *
     * @param goodsTypeDesc 类型描述
     */
    public void setGoodsTypeDesc(String goodsTypeDesc) {
        this.goodsTypeDesc = goodsTypeDesc;
    }

	@Override
	public String toString() {
		return "GoodsType [goodsTypeId=" + goodsTypeId + ", goodsTypeName=" + goodsTypeName + ", goodsTypeDesc="
				+ goodsTypeDesc + "]";
	}
    
    
    
}