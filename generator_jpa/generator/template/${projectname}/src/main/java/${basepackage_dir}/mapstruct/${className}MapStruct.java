<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * MapStruct转换工具类
 *
 * @Author: Aoheng
 * @Date: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@Mapper
public interface ${className}MapStruct {

    ${className}MapStruct INSTANCE = Mappers.getMapper(${className}MapStruct.class);


    ${className}Vo convertTo${className}Vo(${className} ${classNameLower});

    ${className} convertTo${className}(${className}Query query);

    ${className} modelTo${className}(${className}Model model);
    
    List<${className}Vo> convertToVoList(List<${className}> list);

}