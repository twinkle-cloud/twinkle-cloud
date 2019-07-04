package com.twinkle.cloud.common.mybatis.template;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.common.data.GeneralPagingResult;
import com.twinkle.cloud.common.data.PageResult;
import com.twinkle.cloud.common.mybatis.domain.Query;
import com.twinkle.cloud.common.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 11:06 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public abstract class AbstractMapperTemplate <M extends Mapper<T>, T>{
    @Autowired
    protected M mapper;

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }


    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }


    public List<T> selectListAll() {
        return mapper.selectAll();
    }


    public Long selectCount(T entity) {
        return new Long(mapper.selectCount(entity));
    }


    public void insert(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insert(entity);
    }


    public void insertSelective(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insertSelective(entity);
    }


    public void delete(T entity) {
        mapper.delete(entity);
    }


    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }


    public void updateById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKey(entity);
    }


    public void updateSelectiveById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKeySelective(entity);

    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    public GeneralContentResult<List<T>> selectByQuery(Query query) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if(query.entrySet().size()>0) {
            Example.Criteria criteria = example.createCriteria();
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            }
        }
        List<T> list = mapper.selectByExample(example);
        return new GeneralContentResult<>(ResultCode.OPERATION_SUCCESS, list);
    }
    public GeneralPagingResult<List<T>> selectByQuery(Query query, PageRequest _page) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if(query.entrySet().size()>0) {
            Example.Criteria criteria = example.createCriteria();
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            }
        }
        Page<Object> result = PageHelper.startPage(_page.getPageNumber(), _page.getPageSize());
        List<T> list = mapper.selectByExample(example);
        PageResult tempPage = new PageResult();
        tempPage.setCurrentPage(_page.getPageNumber());
        tempPage.setPageSize(_page.getPageSize());
        tempPage.setTotalRecords(result.getTotal());
        tempPage.setTotalPage(result.getPageSize());
        return new GeneralPagingResult<>(ResultCode.OPERATION_SUCCESS, list, tempPage);
    }
}
