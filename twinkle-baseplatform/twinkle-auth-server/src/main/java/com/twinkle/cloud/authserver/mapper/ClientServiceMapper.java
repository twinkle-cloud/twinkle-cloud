package com.twinkle.cloud.authserver.mapper;

import com.twinkle.cloud.authserver.data.domain.ClientService;
import tk.mybatis.mapper.common.Mapper;

public interface ClientServiceMapper extends Mapper<ClientService> {
    void deleteByServiceId(int id);
}