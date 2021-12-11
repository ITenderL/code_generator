<#assign myParentDir="impl">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service.impl;

import com.angogo.rpa.console.common.service.BaseService;
import com.angogo.rpa.console.common.utils.PageHelp;
import ${basepackage}.entity.${className};
import ${basepackage}.repository.${className}Repository;
import ${basepackage}.service.${className}Service;
import ${support_basepackage}.query.${className}Query;
import ${support_basepackage}.vo.${className}Vo;
import com.ybdx.bootcore.common.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

<#include "/copyright_class.include" >
@Slf4j
@Service
public class ${className}ServiceImpl extends BaseService implements ${className}Service {

    ${className}MapStruct mapStruct = ${className}MapStruct.INSTANCE;

    private final ${className}Repository ${classNameLower}Repository;

    
    @Autowired
    ${className}ServiceImpl(${className}Repository ${classNameLower}Repository) {
        this.${classNameLower}Repository = ${classNameLower}Repository;
    }

    @Override
    public ${className}Vo getById(Long id) {
        Optional<${className}> optionalT = ${classNameLower}Repository.findById(id);
        return optionalT.map(item -> mapStruct.convertTo${className}Vo(item)).orElse(null);
    }

    @Override
    public ${className}Vo save(${className}Model model) {
        ${className} ${classNameLower} = mapStruct.modelTo${className}(model);
        ${classNameLower}Repository.save(${classNameLower});
        return mapStruct.convertTo${className}Vo(${classNameLower});
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ${className}Vo update(${className}Model model) {
        ${className} ${classNameLower} = mapStruct.modelTo${className}(model);
        ${classNameLower}Repository.update(${classNameLower});
        return mapStruct.convertTo${className}Vo(${classNameLower});
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(${className}Query query) {
        ${classNameLower}Repository.batchDeleteByIds(query);
    }

    @Override
    public Page<${className}Vo> findAll(${className}Query query) {
        Page<${className}> page = ${classNameLower}Repository.findAll(query);
        List<${className}Vo> voList = mapStruct.convertToVoList(page.getContent());
        return PageHelp.toPage(query, page, voList);
    }

}
