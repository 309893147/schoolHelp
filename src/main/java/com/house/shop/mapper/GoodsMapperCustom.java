package com.house.shop.mapper;

import com.house.shop.pojo.Goods;
import com.house.shop.utils.MyMapper;

import java.util.List;

public interface GoodsMapperCustom  {

    /*
     * 查询带上类型信息
     */
    List<Goods> selectWithType(Goods goods);

    List<Goods> selectGoodsByType(Integer goodsTypeId);
}