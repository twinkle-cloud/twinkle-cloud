package com.twinkle.cloud.authserver.controller;

import com.twinkle.cloud.authserver.data.domain.Client;
import com.twinkle.cloud.authserver.data.domain.ClientService;
import com.twinkle.cloud.authserver.template.ClientMyBatisTemplate;
import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.common.mybatis.controller.BaseController;
import org.springframework.web.bind.annotation.*;

/**
 * @author ace
 * @create 2017/12/26.
 */
@RestController
@RequestMapping("service")
public class ServiceController extends BaseController<ClientMyBatisTemplate, Client> {

    @RequestMapping(value = "/{id}/client", method = RequestMethod.PUT)
    @ResponseBody
    public GeneralResult modifyUsers(@PathVariable int id, String clients){
        baseMapperTemplate.modifyClientServices(id, clients);
        return new GeneralResult(ResultCode.OPERATION_SUCCESS);
    }

    @RequestMapping(value = "/{id}/client", method = RequestMethod.GET)
    @ResponseBody
    public GeneralContentResult<ClientService> getUsers(@PathVariable int id){
        return new GeneralContentResult(ResultCode.OPERATION_SUCCESS, baseMapperTemplate.getClientServices(id));
    }
}
