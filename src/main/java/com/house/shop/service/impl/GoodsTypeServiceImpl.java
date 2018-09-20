package com.house.shop.service.impl;


import com.house.shop.mapper.GoodsTypeMapper;
import com.house.shop.pojo.GoodsType;
import com.house.shop.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService{
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;

	// 查询商品类型
	public List<GoodsType> getGoodsType() {
		List<GoodsType> list = goodsTypeMapper.selectByExample(null);
		return list;
	}

	// 商品类型信息保存
	public int saveGoodsType(GoodsType goodsType) {
		return goodsTypeMapper.insertSelective(goodsType);
	}

	// 超链接的商品类型 进行修改
	public void updateGoodsType(GoodsType goodsType) {

		goodsTypeMapper.updateByPrimaryKeySelective(goodsType);
	}

	@Override
	public GoodsType getGoodsTypeById(Integer goodsTypeId) {
		GoodsType goodsType = goodsTypeMapper.selectByPrimaryKey(goodsTypeId);
		return goodsType;
	}

	@Override
	public void deleteGoodsType(Integer goodsTypeId) {
		// TODO Auto-generated method stub
		goodsTypeMapper.deleteByPrimaryKey(goodsTypeId);
	}

}
