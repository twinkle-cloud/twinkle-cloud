package com.twinkle.cloud.core.usermgmt.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twinkle.cloud.common.data.usermgmt.SecurityUser;
import com.twinkle.cloud.common.exception.GeneralException;
import com.twinkle.cloud.core.usermgmt.entity.UserInfo;
import com.twinkle.cloud.core.usermgmt.entity.dto.PasswordUpdateRequest;
import com.twinkle.cloud.core.usermgmt.entity.query.UserQuery;
import com.twinkle.cloud.core.usermgmt.entity.User;
import com.twinkle.cloud.core.usermgmt.entity.otd.UserResponse;
import com.twinkle.cloud.core.usermgmt.mapper.UserMapper;
import com.twinkle.cloud.core.usermgmt.service.UserInfoService;
import com.twinkle.cloud.core.usermgmt.service.UserOrganizationService;
import com.twinkle.cloud.core.usermgmt.service.UserRoleService;
import com.twinkle.cloud.core.usermgmt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-27 16:46<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserOrganizationService userOrganizationService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${twinkle.security.password.reset-default}")
    private String resetDefaultPassword;

    @Override
    @Cached(name = "user::", key = "#_id", cacheType = CacheType.BOTH)
    public UserResponse get(Long _id) {
        User tempUser = this.getById(_id);
        if (Objects.isNull(tempUser)) {
            throw new GeneralException("user not found with id:" + _id);
        }
        tempUser.setRoleIds(this.userRoleService.queryByUserId(_id));
        tempUser.setOrgIds(this.userOrganizationService.queryByUserId(_id));

        UserResponse tempUserVo = new UserResponse(tempUser);
        tempUserVo.setUserInfo(this.userInfoService.getUserInfoResponseByUserId(_id));
        return tempUserVo;
    }

    @Override
    @Cached(name = "user::", key = "#_id", cacheType = CacheType.BOTH)
    public SecurityUser getByUniqueId(String _id) {
        User tempUser = this.getOne(new QueryWrapper<User>()
                .eq("login_name", _id)
                .or()
                .eq("phone", _id));
        if (Objects.isNull(tempUser)) {
            throw new GeneralException("user not found with uniqueId:" + _id);
        }
        SecurityUser tempSecurityUser = new SecurityUser();
        tempSecurityUser.setId(tempUser.getId());
        tempSecurityUser.setLoginName(tempUser.getLoginName());
        tempSecurityUser.setPassword(tempUser.getPassword());
        tempSecurityUser.setPhone(tempUser.getPhone());
        tempSecurityUser.setRoleIds(this.userRoleService.queryByUserId(tempUser.getId()));
        tempSecurityUser.setManagedOrgIds(this.userOrganizationService.queryByUserId(tempUser.getId()));

        UserInfo tempUserInfo = this.userInfoService.getByUserId(tempUser.getId());
        tempSecurityUser.setName(tempUserInfo.getName());
        tempSecurityUser.setOrgId(tempUserInfo.getOrgId());
        tempSecurityUser.setTenantId(tempUserInfo.getTenantId());
        
        return tempSecurityUser;
    }

    @Override
    @Transactional
    public boolean add(User _user) {
        if (StringUtils.isNotBlank(_user.getPassword()))
            _user.setPassword(this.passwordEncoder.encode(_user.getPassword()));
        boolean tempFlag = this.save(_user);
        UserInfo tempUserInfo = _user.getUserInfo();
        tempUserInfo.setUserId(_user.getId());
        this.userRoleService.saveBatch(_user.getId(), _user.getRoleIds());
        this.userOrganizationService.saveBatch(_user.getId(), _user.getOrgIds());
        this.userInfoService.add(tempUserInfo);
        return tempFlag;
    }

    @Override
    public IPage<UserResponse> query(Page<User> _page, UserQuery _queryParam) {
        QueryWrapper<User> queryWrapper = _queryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(_queryParam.getName()), "name", _queryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(_queryParam.getLoginName()), "loginname", _queryParam.getLoginName());
        queryWrapper.eq(StringUtils.isNotBlank(_queryParam.getPhone()), "phone", _queryParam.getPhone());
        // 转换成VO
        IPage<User> iPageUser = this.page(_page, queryWrapper);
        return iPageUser.convert(UserResponse::new);
    }

    @Override
    @Transactional
    @CacheInvalidate(name = "user::", key = "#user.id")
    public boolean update(User _user) {
        if (StringUtils.isNotBlank(_user.getPassword()))
            _user.setPassword(this.passwordEncoder.encode(_user.getPassword()));
        boolean isSuccess = this.updateById(_user);
        UserInfo tempUserInfo = _user.getUserInfo();
        tempUserInfo.setUserId(_user.getId());
        this.userInfoService.updateByUserId(tempUserInfo, _user.getId());
        this.userRoleService.saveBatch(_user.getId(), _user.getRoleIds());
        this.userOrganizationService.saveBatch(_user.getId(), _user.getOrgIds());
        return isSuccess;
    }

    @Override
    @Transactional
    @CacheInvalidate(name = "user::", key = "#_id")
    public boolean delete(Long _id) {
        boolean isSuccess = this.removeById(_id);
        this.userInfoService.deleteByUserId(_id);
        this.userRoleService.removeByUserId(_id);
        this.userOrganizationService.removeByUserId(_id);
        return isSuccess;
    }

    @Override
    public boolean updatePassword(PasswordUpdateRequest _password) {
        if (StringUtils.isNotBlank(_password.getPassword())) {
            throw new GeneralException("The password is not allowed to be empty.");
        }
        String tempPassword = this.passwordEncoder.encode(_password.getPassword());
        boolean isSuccess = this.retBool(this.baseMapper.updatePasswordById(_password.getUserId(), tempPassword));
        return isSuccess;
    }

    @Override
    public boolean resetPassword(Long _userId) {
        String tempPassword = this.passwordEncoder.encode(resetDefaultPassword);
        boolean isSuccess = this.retBool(this.baseMapper.updatePasswordById(_userId, tempPassword));
        return isSuccess;
    }
}
