package com.twinkle.cloud.core.usermgmt.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twinkle.cloud.common.data.usermgmt.SecurityRole;
import com.twinkle.cloud.common.exception.GeneralException;
import com.twinkle.cloud.core.usermgmt.entity.query.RoleQuery;
import com.twinkle.cloud.core.usermgmt.entity.Role;
import com.twinkle.cloud.core.usermgmt.mapper.RoleMapper;
import com.twinkle.cloud.core.usermgmt.service.RoleMenuService;
import com.twinkle.cloud.core.usermgmt.service.RoleService;
import com.twinkle.cloud.core.usermgmt.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/1/20 9:53 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public boolean add(Role role) {
        boolean isSuccess = this.save(role);
        this.roleMenuService.saveBatch(role.getId(), role.getMenuIds());
        return isSuccess;
    }

    @Override
    @CacheInvalidate(name = "role::", key = "#id")
    public boolean delete(String id) {
        this.roleMenuService.removeByRoleId(id);
        return this.removeById(id);
    }

    @Override
    @CacheInvalidate(name = "role::", key = "#role.id")
    public boolean update(Role role) {
        boolean isSuccess = this.updateById(role);
        this.roleMenuService.saveBatch(role.getId(), role.getMenuIds());
        return isSuccess;
    }

    @Override
    @Cached(name = "role::", key = "#id", cacheType = CacheType.BOTH)
    public Role get(String id) {
        Role tempRole = this.getById(id);
        if (Objects.isNull(tempRole)) {
            throw new GeneralException("role not found with id:" + id);
        }
        tempRole.setMenuIds(this.roleMenuService.queryByRoleId(id));
        return tempRole;
    }

    @Override
    public List<Role> getAll() {
        return this.list();
    }

    @Override
    @Cached(name = "role4user::", key = "#_userId", cacheType = CacheType.BOTH)
    public Set<SecurityRole> queryByUserId(Long _userId) {
        Set<String> roleIds = this.userRoleService.queryByUserId(_userId);

        List<Role> tempRoles = this.listByIds(roleIds);
        if(CollectionUtils.isEmpty(tempRoles)) {
            return new HashSet<>();
        }
        return tempRoles.stream().map(item -> this.convertToSecurityRole(item)).collect(Collectors.toSet());
    }

    private SecurityRole convertToSecurityRole(Role _role){
        SecurityRole tempRole = new SecurityRole();
        tempRole.setId(_role.getId().toString());
        tempRole.setCode(_role.getCode());
        tempRole.setName(_role.getName());
        tempRole.setType(_role.getType());
        tempRole.setStatus(_role.getStatus());
        return tempRole;
    }

    @Override
    public IPage<Role> queryByUserId(Page page, RoleQuery roleQueryParam) {
        QueryWrapper<Role> queryWrapper = roleQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryParam.getName()), "name", roleQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryParam.getCode()), "code", roleQueryParam.getCode());
        return this.page(page, queryWrapper);
    }
}
