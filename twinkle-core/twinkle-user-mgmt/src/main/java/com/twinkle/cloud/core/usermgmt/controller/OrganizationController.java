package com.twinkle.cloud.core.usermgmt.controller;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.core.usermgmt.entity.form.OrganizationForm;
import com.twinkle.cloud.core.usermgmt.entity.form.OrganizationQueryForm;
import com.twinkle.cloud.core.usermgmt.entity.param.OrganizationQueryParam;
import com.twinkle.cloud.core.usermgmt.entity.Organization;
import com.twinkle.cloud.core.usermgmt.service.OrganizationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/2/20 8:04 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@RestController
@Api("Organization Management")
@Slf4j
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @ApiOperation(value = "新增组织机构", notes = "新增一个组织机构")
    @ApiImplicitParam(name = "_org", value = "新增组织机构form表单", required = true, dataType = "OrganizationForm")
    @PostMapping(value = "/authsec/organization")
    public GeneralResult add(@Valid @RequestBody OrganizationForm _org) {
        log.debug("Name:{}", _org);
        return GeneralResult.success(organizationService.add(_org.toPo(Organization.class)));
    }

    @ApiOperation(value = "删除组织机构", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "_id", value = "组织机构ID", required = true, dataType = "long")
    @DeleteMapping(value = "/authsec/organization/{_id}")
    public GeneralResult delete(@PathVariable String _id) {
        return GeneralResult.success(this.organizationService.delete(_id));
    }

    @ApiOperation(value = "修改组织机构", notes = "修改指定组织机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "_id", value = "组织机构ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "_org", value = "组织机构实体", required = true, dataType = "OrganizationForm")
    })
    @PutMapping(value = "/authsec/organization/{_id}")
    public GeneralResult update(@PathVariable String _id, @Valid @RequestBody OrganizationForm _org) {
        Organization tempOrg = _org.toPo(Organization.class);
        tempOrg.setId(_id);
        return GeneralResult.success(this.organizationService.update(tempOrg));
    }

    @ApiOperation(value = "获取组织机构", notes = "获取指定组织机构信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "组织机构ID", required = true, dataType = "long")
    @GetMapping(value = "/authsec/organization/{_id}")
    public GeneralResult get(@PathVariable String _id) {
        log.debug("Get with id:{}", _id);
        return GeneralResult.success(this.organizationService.get(_id));
    }

    @ApiOperation(value = "查询组织机构", notes = "根据条件查询组织机构信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "_name", value = "组织机构名称", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 100, message = "处理成功", response = GeneralResult.class)
    )
    @GetMapping(value = "/authsec/organization")
    public GeneralResult query(@RequestParam String _name) {
        log.debug("Query with name:{}", _name);
        OrganizationQueryParam groupQueryParam = new OrganizationQueryParam();
        groupQueryParam.setName(_name);
        return GeneralResult.success(this.organizationService.query(groupQueryParam));
    }

    @ApiOperation(value = "搜索组织机构", notes = "根据条件查询组织机构信息")
    @ApiImplicitParam(name = "_condition", value = "组织机构查询参数", required = true, dataType = "OrganizationQueryForm")
    @ApiResponses(
            @ApiResponse(code = 100, message = "处理成功", response = GeneralResult.class)
    )
    @PostMapping(value = "/authsec/organization/conditions")
    public GeneralResult search(@Valid @RequestBody OrganizationQueryForm _condition) {
        log.debug("Search with groupQueryForm:{}", _condition);
        return GeneralResult.success(this.organizationService.query(_condition.toParam(OrganizationQueryParam.class)));
    }

    @ApiOperation(value = "根据父id查询组织机构", notes = "根据父id查询组织机构列表")
    @ApiImplicitParam(paramType = "path", name = "_id", value = "组织机构父ID", required = true, dataType = "long")
    @GetMapping(value = "/authsec/organization/parent/{_id}")
    public GeneralResult search(@PathVariable String _id) {
        log.debug("Query with parent id:{}", _id);
        return GeneralResult.success(this.organizationService.queryByParentId(_id));
    }
}
