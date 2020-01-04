package com.twinkle.cloud.core.usermgmt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twinkle.cloud.core.usermgmt.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 9:42 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Repository
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
