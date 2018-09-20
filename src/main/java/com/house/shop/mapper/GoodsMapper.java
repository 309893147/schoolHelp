package com.house.shop.mapper;

import java.util.List;

import com.house.shop.pojo.Goods;
import com.house.shop.utils.MyMapper;

public interface GoodsMapper extends MyMapper<Goods> {
	  List<Goods> selectByUserId(String userId);
}