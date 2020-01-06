package com.twinkle.cloud.core.usermgmt.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twinkle.cloud.core.usermgmt.entity.query.OrganizationQuery;
import com.twinkle.cloud.core.usermgmt.entity.Organization;
import com.twinkle.cloud.core.usermgmt.mapper.OrganizationMapper;
import com.twinkle.cloud.core.usermgmt.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/2/20 8:15 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
@Slf4j
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Override
    @Cached(name = "organization::", key = "#_id", cacheType = CacheType.BOTH)
    public Organization get(String _id) {
        return this.getById(_id);
    }

    @Override
    public boolean add(Organization _org) {
        return this.save(_org);
    }

    @Override
    public List<Organization> query(OrganizationQuery _condition) {
        QueryWrapper<Organization> queryWrapper = _condition.build();
        queryWrapper.eq("name", _condition.getName());
        return this.list(queryWrapper);
    }

    @Override
    public List<Organization> queryByParentId(String _parentId) {
        return this.list(new QueryWrapper<Organization>().eq("parent_id", _parentId));
    }

    @Override
    @CacheInvalidate(name = "organization::", key = "#_org.id")
    public boolean update(Organization _org) {
        return this.updateById(_org);
    }

    @Override
    @CacheInvalidate(name = "organization::", key = "#_id")
    public boolean delete(String _id) {
        return this.removeById(_id);
    }
}
