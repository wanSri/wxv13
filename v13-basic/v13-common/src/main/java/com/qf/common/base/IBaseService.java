package com.qf.common.base;

import java.util.List;

/**
 * @author: WangXi
 * @Date: 2019/6/11
 */
public interface IBaseService<T> {

    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> queryAll();
}
