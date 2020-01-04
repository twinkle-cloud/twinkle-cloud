package com.twinkle.cloud.core.usermgmt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twinkle.cloud.core.usermgmt.entity.UserOrg;
import com.twinkle.cloud.core.usermgmt.entity.UserRole;
import com.twinkle.cloud.core.usermgmt.mapper.UserOrgMapper;
import com.twinkle.cloud.core.usermgmt.mapper.UserRoleMapper;
import com.twinkle.cloud.core.usermgmt.service.UserOrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/4/20 10:32 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
@Slf4j
public class UserOrganizationServiceImpl extends ServiceImpl<UserOrgMapper, UserOrg> implements UserOrganizationService {
    @Override
    public boolean saveBatch(Long _userId, Set<Integer> _orgIds) {
        if (CollectionUtils.isEmpty(_orgIds))
            return false;
        removeByUserId(_userId);
        Set<UserOrg> userRoles = _orgIds.stream().map(item -> new UserOrg(_userId, item)).collect(Collectors.toSet());
        return saveBatch(userRoles);
    }

    @Override
    public boolean removeByUserId(Long _userId, Integer _orgId) {
        QueryWrapper<UserOrg> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserOrg::getUserId, _userId)
        .eq(UserOrg::getOrgId, _orgId);
        return remove(queryWrapper);
    }

    @Override
    public boolean removeByUserId(Long _userId) {
        QueryWrapper<UserOrg> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserOrg::getUserId, _userId);
        return remove(queryWrapper);
    }

    @Override
    public Set<Integer> queryByUserId(Long _userId) {
        QueryWrapper<UserOrg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", _userId);
        List<UserOrg> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(UserOrg::getOrgId).collect(Collectors.toSet());
    }
}
