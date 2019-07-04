package com.twinkle.cloud.common.mybatis.controller;

import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.context.BaseContextHandler;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.common.mybatis.domain.Query;
import com.twinkle.cloud.common.mybatis.template.AbstractMapperTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/15/18 5:50 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class BaseController<BaseMapperTemplate extends AbstractMapperTemplate, Entity> {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected BaseMapperTemplate baseMapperTemplate;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public GeneralContentResult<Entity> add(@RequestBody Entity entity){
        baseMapperTemplate.insertSelective(entity);
        GeneralContentResult<Entity> tempResult = new GeneralContentResult<>();
        tempResult.setResultContent(entity);
        tempResult.setResultCode(ResultCode.OPERATION_SUCCESS);
        return tempResult;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public GeneralContentResult<Entity> get(@PathVariable int id){
        GeneralContentResult<Entity> tempResult = new GeneralContentResult<>();
        Object o = baseMapperTemplate.selectById(id);
        tempResult.setResultContent((Entity)o);
        tempResult.setResultCode(ResultCode.OPERATION_SUCCESS);
        return tempResult;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public GeneralResult update(@RequestBody Entity entity){
        baseMapperTemplate.updateSelectiveById(entity);
        return new GeneralResult(ResultCode.OPERATION_SUCCESS);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public GeneralResult remove(@PathVariable int id){
        baseMapperTemplate.deleteById(id);
        return new GeneralResult(ResultCode.OPERATION_SUCCESS);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> all(){
        return baseMapperTemplate.selectListAll();
    }
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public GeneralContentResult<List<Entity>> list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        return baseMapperTemplate.selectByQuery(query);
    }
    public String getCurrentUserName(){
        return BaseContextHandler.getUsername();
    }
}
