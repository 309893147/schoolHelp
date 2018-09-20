package com.house.shop.service;

import com.house.shop.pojo.Goods;

import java.util.List;

public interface GoodsService {
    // 联合商品类型表查询商品所有信息
    public List<Goods> getAllWithType();
    // 查询商品所有信息
    public List<Goods> getAllGoods();
    
    // 查询个人发布商品所有信息
    public List<Goods> getOwnGoods(String userId);

    // 查询商品所有信息 通过类型查询
    public List<Goods> getAllGoodsByType(Integer goodsTypeId);
    // 商品信息保存
    public int saveGoods(Goods goods);

    // 按照商品ID查询商品
    public Goods getGoodsById(Integer id);

    // 超链接的商品 进行修改
    public void updateGoods(Goods goods);

    // 删除
    public void deleteGoods(Integer id);
}
