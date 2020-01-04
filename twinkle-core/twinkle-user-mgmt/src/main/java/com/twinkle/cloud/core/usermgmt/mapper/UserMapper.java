package com.twinkle.cloud.core.usermgmt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twinkle.cloud.core.usermgmt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 9:40 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * Update the password for the given user.
     *
     * @param _userId
     * @param _password
     * @return
     */
    @Update("UPDATE U_USER U SET U.PASSWORD = #{_password} WHERE U.ID = #{_userId}")
    int updatePasswordById(@Param("_userId") Long _userId, @Param("_password") String _password);
}
