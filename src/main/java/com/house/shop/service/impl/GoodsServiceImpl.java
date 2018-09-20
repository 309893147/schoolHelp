package com.house.shop.service.impl;

import com.house.shop.mapper.GoodsMapper;
import com.house.shop.mapper.GoodsMapperCustom;
import com.house.shop.mapper.UserMapper;
import com.house.shop.pojo.Goods;
import com.house.shop.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    GoodsMapperCustom goodsMapperCustom;
    @Autowired
    UserMapper userMapper;

    // 联合商品类型表查询商品所有信息
    public List<Goods> getAllWithType() {

        return goodsMapperCustom.selectWithType(null);

    }

    // 查询商品所有信息
    public List<Goods> getAllGoods() {

        return goodsMapper.selectByExample(null);
    }
    
    // 查询个人发布商品信息
    public List<Goods> getOwnGoods(String userId) {

        return goodsMapper.selectByUserId(userId);
    }



    // 查询商品所有信息 通过类型查询
    public List<Goods> getAllGoodsByType(Integer goodsTypeId) {

     return goodsMapperCustom.selectGoodsByType(goodsTypeId);
      
    }

    // 商品信息保存
    public int saveGoods(Goods goods) {
        return goodsMapper.insertSelective(goods);
    }

    // 按照商品ID查询商品
    public Goods getGoodsById(Integer id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }

    // 超链接的商品 进行修改
    public void updateGoods(Goods goods) {

        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    // 删除
    public void deleteGoods(Integer id) {
        // TODO Auto-generated method stub
        goodsMapper.deleteByPrimaryKey(id);
    }

//    public void deleteBatch(List<Integer> ids) {
//        // TODO Auto-generated method stub
//        GoodsExample goods = new GoodsExample();// 定义一个条件
//        Criteria criteria = goods.createCriteria();
//        // delete from xx from exp_id in(1,2,3)
//        criteria.andGoodsIdIn(ids);
//        goodsMapper.deleteByExample(goods);// 按照条件删除
//    }
}