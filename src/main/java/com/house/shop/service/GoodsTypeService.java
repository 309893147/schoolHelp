package com.house.shop.service;

import com.house.shop.pojo.GoodsType;

import java.util.List;

public interface GoodsTypeService {
    // 查询商品类型
    public List<GoodsType> getGoodsType();
    // 商品类型信息保存
    public int saveGoodsType(GoodsType goodsType);
    // 超链接的商品类型 进行修改
    public void updateGoodsType(GoodsType goodsType);

    // 按照商品类型ID查询商品类型
    public GoodsType getGoodsTypeById(Integer goodsTypeId) ;

    // 删除
    public void deleteGoodsType(Integer goodsTypeId);

    }


