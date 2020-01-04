package com.twinkle.cloud.core.usermgmt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twinkle.cloud.core.usermgmt.entity.UserRole;
import com.twinkle.cloud.core.usermgmt.mapper.UserRoleMapper;
import com.twinkle.cloud.core.usermgmt.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 10:27 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public boolean saveBatch(Long _userId, Set<String> _roleIds) {
        if (CollectionUtils.isEmpty(_roleIds))
            return false;
        removeByUserId(_userId);
        Set<UserRole> userRoles = _roleIds.stream().map(roleId -> new UserRole(_userId, roleId)).collect(Collectors.toSet());
        return saveBatch(userRoles);
    }

    @Override
    public boolean removeByUserId(Long _userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId, _userId);
        return remove(queryWrapper);
    }

    @Override
    public Set<String> queryByUserId(Long _userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", _userId);
        List<UserRole> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
    }
}
