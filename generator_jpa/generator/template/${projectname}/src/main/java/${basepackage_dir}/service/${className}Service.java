<#assign myParentDir="service">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service;

import ${support_basepackage}.query.${className}Query;
import ${support_basepackage}.vo.${className}Vo;
import org.springframework.data.domain.Page;

<#include "/copyright_class.include" >
public interface ${className}Service {
	
	${className}Vo getById(Long id);

    Page<${className}Vo> findAll(${className}Query query);

    ${className}Vo save(${className}Model model);

    ${className}Vo update(${className}Model model);

    void deleteByIds(${className}Query query);

}

