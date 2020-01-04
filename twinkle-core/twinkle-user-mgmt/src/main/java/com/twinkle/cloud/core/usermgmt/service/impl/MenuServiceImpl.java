package com.twinkle.cloud.core.usermgmt.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twinkle.cloud.core.usermgmt.entity.param.MenuQueryParam;
import com.twinkle.cloud.core.usermgmt.entity.Menu;
import com.twinkle.cloud.core.usermgmt.entity.Role;
import com.twinkle.cloud.core.usermgmt.entity.RoleMenu;
import com.twinkle.cloud.core.usermgmt.entity.User;
import com.twinkle.cloud.core.usermgmt.mapper.MenuMapper;
import com.twinkle.cloud.core.usermgmt.service.MenuService;
import com.twinkle.cloud.core.usermgmt.service.RoleMenuService;
import com.twinkle.cloud.core.usermgmt.service.RoleService;
import com.twinkle.cloud.core.usermgmt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/1/20 10:37 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Override
    public boolean add(Menu _menu) {
        return this.save(_menu);
    }

    @Override
    public IPage<Menu> query(Page _page, MenuQueryParam _condition) {
        QueryWrapper<Menu> queryWrapper = _condition.build();
        queryWrapper.eq(StringUtils.isNotBlank(_condition.getName()), "name", _condition.getName());
        queryWrapper.eq(StringUtils.isNotBlank(_condition.getCode()), "name", _condition.getCode());
        queryWrapper.eq(_condition.getType() != null, "type", _condition.getType());
        return this.page(_page, queryWrapper);
    }

    @Override
    @CacheInvalidate(name = "menu::", key = "#_id")
    public boolean delete(String _id) {
        return this.removeById(_id);
    }

    @Override
    @CacheInvalidate(name = "menu::", key = "#menu.id")
    public boolean update(Menu _menu) {
        return this.updateById(_menu);
    }

    @Override
    @Cached(name = "menu::", key = "#_id", cacheType = CacheType.BOTH)
    public Menu get(String _id) {
        return this.getById(_id);
    }

    @Override
    public List<Menu> query(MenuQueryParam _condition) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != _condition.getName(), "name", _condition.getName());
        return this.list(queryWrapper);
    }

    @Override
    public List<Menu> queryByParentId(String _id) {
        return this.list(new QueryWrapper<Menu>().eq("parent_id", _id));
    }

    @Override
    @Cached(name = "menu4user::", key = "#_userName", cacheType = CacheType.BOTH)
    public List<Menu> query(String _userName) {
        //根据用户名查询到用户所拥有的角色
        User tempUser = userService.getByUniqueId(_userName);
        List<Role> tempRoles = roleService.queryByUserId(tempUser.getId());
        //提取用户所拥有角色id列表
        Set<String> tempRoleIds = tempRoles.stream().map(role -> (String)role.getId()).collect(Collectors.toSet());
        //根据角色列表查询到角色的资源的关联关系
        List<RoleMenu> tempRoleMenus = roleMenuService.queryByRoleIds(tempRoleIds);
        //根据资源列表查询出所有资源对象
        Set<String> tempMenuIds = tempRoleMenus.stream().map(roleResource -> roleResource.getMenuId()).collect(Collectors.toSet());
        //根据resourceId列表查询出resource对象
        return this.listByIds(tempMenuIds);
    }
}
