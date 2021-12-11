<#assign myParentDir="service">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service;


import ${basepackage}.support.${className}RequestProtocol;
import com.yqjp.framework.core.protocol.ResponseProtocol;
import com.yqjp.framework.core.service.StandardService;

<#include "/copyright_class.include" >
public interface I${className}Service  extends StandardService<${className}RequestProtocol,ResponseProtocol>{
	
	public ResponseProtocol get${className}(${className}RequestProtocol ${className}RequestProtocol) throws Exception;
	
    public ResponseProtocol add${className}(${className}RequestProtocol ${className}RequestProtocol) throws Exception;
	
	public ResponseProtocol delete${className}(${className}RequestProtocol ${className}RequestProtocol) throws Exception;
	
	public ResponseProtocol update${className}(${className}RequestProtocol ${className}RequestProtocol) throws Exception;
	
	public ResponseProtocol pageQuery(${className}RequestProtocol ${className}RequestProtocol) throws Exception;

}

