package com.twinkle.cloud.common.mybatis.entity.dto;

import com.twinkle.cloud.common.mybatis.entity.IEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 3:42 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@ApiModel
@Slf4j
@Data
public class BaseRequest<E extends IEntity> {
    /**
     * 用户名
     */
//    private String username;

    /**
     * From转化为Po，进行后续业务处理
     *
     * @param clazz
     * @return
     */
    public E toPo(Class<E> clazz) {
        E e = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, e);
        return e;
    }

    /**
     * From转化为Po，进行后续业务处理
     *
     * @param id
     * @param clazz
     * @return
     */
    public E toPo(Serializable id, Class<E> clazz) {
        E e = BeanUtils.instantiateClass(clazz);
        if(id instanceof Long) {
            e.setId((Long)id);
        } else if(id instanceof Long) {
            e.setId((Integer)id);
        } else {
            e.setId((String)id);
        }
        BeanUtils.copyProperties(this, e);
        return e;
    }
}
