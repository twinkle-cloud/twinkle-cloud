package com.twinkle.cloud.core.usermgmt.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twinkle.cloud.core.usermgmt.entity.RoleMenu;
import com.twinkle.cloud.core.usermgmt.mapper.RoleMenuMapper;
import com.twinkle.cloud.core.usermgmt.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/1/20 10:18 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
@Slf4j
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    @Transactional
    public boolean saveBatch(Serializable _roleId, Set<String> _menuIds) {
        if (CollectionUtils.isEmpty(_menuIds))
            return false;
        removeByRoleId(_roleId);
        Set<RoleMenu> roleMenus = _menuIds.stream().map(item -> new RoleMenu((String)_roleId, item)).collect(Collectors.toSet());
        return saveBatch(roleMenus);
    }

    @Override
    @Transactional
    public boolean removeByRoleId(Serializable _roleId) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenu::getRoleId, _roleId);
        return remove(queryWrapper);
    }

    @Override
    @Cached(area = "shortTime", name = "menu4role::", key = "#roleId", cacheType = CacheType.BOTH)
    public Set<String> queryByRoleId(String _roleId) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", _roleId);
        List<RoleMenu> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
    }

    @Override
    public List<RoleMenu> queryByRoleIds(Set<String> _roleIds) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", _roleIds);
        return this.list(queryWrapper);
    }
}
