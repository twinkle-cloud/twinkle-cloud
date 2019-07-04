package com.twinkle.cloud.authserver.service.impl;

import com.twinkle.cloud.authserver.bean.ClientInfo;
import com.twinkle.cloud.authserver.data.domain.Client;
import com.twinkle.cloud.authserver.mapper.ClientMapper;
import com.twinkle.cloud.authserver.service.AuthClientService;
import com.twinkle.cloud.authserver.util.client.ClientTokenUtil;
import com.twinkle.cloud.common.auth.exception.ClientInvalidException;
import com.twinkle.cloud.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DBAuthClientService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: Sep 30, 2016 2:56:31 PM <br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
public class DbAuthClientServiceImpl implements AuthClientService {
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientTokenUtil clientTokenUtil;
    @Autowired
    private DiscoveryClient discovery;
    private ApplicationContext context;

    @Autowired
    public DbAuthClientServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public String apply(String clientId, String secret) throws Exception {
        Client client = getClient(clientId, secret);
        return clientTokenUtil.generateToken(new ClientInfo(client.getCode(), client.getName(), client.getId().toString()));
    }

    private Client getClient(String clientId, String secret) {
        Client client = new Client();
        client.setCode(clientId);
        client = clientMapper.selectOne(client);
        if (client == null || !client.getSecret().equals(secret)) {
            throw new ClientInvalidException("Client not found or Client secret is com.twinkle.cloud.common.asm.error!");
        }
        return client;
    }

    @Override
    public void validate(String clientId, String secret) throws Exception {
        Client client = new Client();
        client.setCode(clientId);
        client = clientMapper.selectOne(client);
        if (client == null || !client.getSecret().equals(secret)) {
            throw new ClientInvalidException("Client not found or Client secret is com.twinkle.cloud.common.asm.error!");
        }
    }

    @Override
    public List<String> getAllowedClient(String clientId, String secret) {
        Client info = this.getClient(clientId, secret);
        List<String> clients = clientMapper.selectAllowedClient(info.getId() + "");
        if (clients == null) {
            new ArrayList<String>();
        }
        return clients;
    }

    @Override
    public List<String> getAllowedClient(String serviceId) {
        Client info = getClient(serviceId);
        List<String> clients = clientMapper.selectAllowedClient(info.getId() + "");
        if (clients == null) {
            new ArrayList<String>();
        }
        return clients;
    }

    private Client getClient(String clientId) {
        Client client = new Client();
        client.setCode(clientId);
        client = clientMapper.selectOne(client);
        return client;
    }

    @Override
    @Scheduled(cron = "0 0/1 * * * ?")
    public void registryClient() {
        // 自动注册节点
        discovery.getServices().forEach((name) -> {
            Client client = new Client();
            client.setName(name);
            client.setCode(name);
            Client dbClient = clientMapper.selectOne(client);
            if (dbClient == null) {
                client.setSecret(UUIDUtils.generateShortUuid());
                clientMapper.insert(client);
            }
        });
    }
}
