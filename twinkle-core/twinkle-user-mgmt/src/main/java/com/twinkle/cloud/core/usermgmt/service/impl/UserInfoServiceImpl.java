package com.twinkle.cloud.core.usermgmt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twinkle.cloud.common.constant.CommonConstant;
import com.twinkle.cloud.common.exception.GeneralException;
import com.twinkle.cloud.core.usermgmt.entity.UserInfo;
import com.twinkle.cloud.core.usermgmt.entity.otd.UserInfoResponse;
import com.twinkle.cloud.core.usermgmt.mapper.UserInfoMapper;
import com.twinkle.cloud.core.usermgmt.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/4/20 11:59 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public UserInfo getByUserId(Long _userId) {
        UserInfo tempUser = this.getOne(new QueryWrapper<UserInfo>()
            .eq("user_id", _userId)
            .eq("status", CommonConstant.DIC_GLOBAL_STATUS_ENABLE));
        if (Objects.isNull(tempUser)) {
            throw new GeneralException("user not found with id:" + _userId);
        }
        return tempUser;
    }

    @Override
    public UserInfoResponse getUserInfoResponseByUserId(Long _userId) {
        return new UserInfoResponse(this.getByUserId(_userId));
    }

    @Override
    public boolean add(UserInfo _profile) {
        if (_profile.getUserId() != null) {
            boolean tempFlag = this.save(_profile);
            return tempFlag;
        }
        log.warn("The user is empty for the profile [{}]", _profile);
        return true;
    }

    @Override
    public boolean updateByUserId(UserInfo _profile, Long _userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", _userId);
        return this.update(_profile, queryWrapper);
    }

    @Override
    public boolean deleteByUserId(Long _userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getUserId, _userId);
        return remove(queryWrapper);
    }
}
