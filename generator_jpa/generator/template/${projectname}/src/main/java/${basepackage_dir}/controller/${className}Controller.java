<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign myParentDir="controller">
package  ${basepackage}.controller;


import ${basepackage}.entity.${className};
import ${basepackage}.service.${className}Service;

import com.angogo.rpa.console.audit.annotation.SysLog;
import com.angogo.rpa.console.audit.enums.LogType;
import com.angogo.rpa.console.audit.enums.OperationType;
import com.angogo.rpa.console.common.controller.BaseExtController;
import com.angogo.rpa.console.common.domain.model.ApiPageResult;
import ${basepackage}.entity.${className};
import ${basepackage}.service.${className}Service;
import ${support_basepackage}.query.${className}Query;
import ${support_basepackage}.vo.${className}Vo;
import com.ybdx.bootcore.common.api.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

<#include "/copyright_class.include" >
@Slf4j
@Api
@RestController
@RequestMapping(value="/${classNameLower}")
public class ${className}Controller extends BaseExtController{
	
	private final ${className}Service ${classNameLower}Service;


    @Autowired
    public ${className}Controller(${className}Service ${classNameLower}Service) {
        this.${classNameLower}Service = ${classNameLower}Service;
    }

    @ApiOperation("新增")
    @SysLog(title = "新增", logType = LogType.OPERATION, operationType = OperationType.ADD)
    @PostMapping()
    public ApiResult<${className}Vo> save(@RequestBody ${className}Model model) {
        setCreatorAndUpdater(model);
        log.info("新增：{}", model);
        return ApiResult.ok(${classNameLower}Service.save(model));
    }

    @ApiOperation("删除")
    @SysLog(title = "删除", logType = LogType.OPERATION, operationType = OperationType.LOGIC_DELETE)
    @DeleteMapping()
    public ApiResult<Boolean> deleteByIds(@RequestBody Set<Long> ids) {
        log.info("deleteByIds = {}", ids);
        ${classNameLower}Service.deleteByIds(setIdsAndUpdater(new ${className}Query(), ids));
        return ApiResult.ok();
    }

    @ApiOperation("更新")
    @SysLog(title = "更新", logType = LogType.OPERATION, operationType = OperationType.UPDATE)
    @PutMapping(value = "/{id}")
    public ApiResult<${className}Vo> update(@RequestBody ${className}Model model) {
        setUpdater(model);
    	log.info("update ${classNameLower}:{}", model);
        return ApiResult.ok(${classNameLower}Service.update(model));
    }

    @GetMapping(value = "/{id}")
    public ApiResult<${className}Vo> getById(@NonNull @PathVariable Long id) {
        return ApiResult.ok(${classNameLower}Service.getById(id));
    }

    @ApiOperation("分页列表")
    @GetMapping()
    public ApiPageResult<${className}Vo> findAll(${className}Query query) {
        setOrgCode(query);
        return ApiPageResult.success(${classNameLower}Service.findAll(query));
    }
	
}
