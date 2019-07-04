package com.twinkle.cloud.common.mybatis.service.impl;

import com.twinkle.cloud.common.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/15/18 6:05 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class BaseServiceImpl<M extends Mapper<T>, T> implements BaseService<T>  {
    @Autowired
    protected M mapper;


    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

//    @Override
//    public List<T> selectListByIds(List<Object> ids) {
//        return mapper.selectByIds(ids);
//    }

    @Override
    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }

    @Override
    public List<T> selectListAll() {
        return mapper.selectAll();
    }

//    @Override
//    public Long selectCountAll() {
//        return mapper.selectCount();
//    }

    @Override
    public Long selectCount(T entity) {
        return Long.valueOf(mapper.selectCount(entity));
    }

    @Override
    public void insert(T entity) {
        mapper.insert(entity);
    }

    @Override
    public void insertSelective(T entity) {
        mapper.insertSelective(entity);
    }

    @Override
    public void delete(T entity) {
        mapper.delete(entity);
    }

    @Override
    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById(T entity) {
        mapper.updateByPrimaryKey(entity);
    }

    @Override
    public void updateSelectiveById(T entity) {
        mapper.updateByPrimaryKeySelective(entity);
    }

//    @Override
//    public void deleteBatchByIds(List<Object> ids) {
//        mapper.batchDeleteByIds(ids);
//    }
//
//    @Override
//    public void updateBatch(List<T> entitys) {
//        mapper.batchUpdate(entitys);
//    }
}
