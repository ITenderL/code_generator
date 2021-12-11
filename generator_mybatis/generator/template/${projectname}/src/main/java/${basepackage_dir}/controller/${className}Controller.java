<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign myParentDir="controller">
package  ${basepackage}.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ${basepackage}.support.${className}RequestProtocol;
import com.yqjp.framework.core.protocol.ResponseProtocol;
import com.yqjp.mybatis.plugin.pagination.Page;
import ${basepackage}.dao.entity.${className};
import ${basepackage}.service.I${className}Service;

<#include "/copyright_class.include" >
@RestController
@RequestMapping(value="/${classNameLower}",method= {RequestMethod.GET,RequestMethod.POST})
@RefreshScope
public class ${className}Controller {
	
	@Autowired
	private I${className}Service service;
	
	private static final Logger LOG = LoggerFactory.getLogger(${className}Controller.class);

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseProtocol add(@RequestBody ${className}RequestProtocol ${className}RequestProtocol) throws Exception {
		return service.add${className}(${className}RequestProtocol);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ResponseProtocol delete(@RequestBody ${className}RequestProtocol ${className}RequestProtocol) throws Exception {
		return service.delete${className}(${className}RequestProtocol);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseProtocol update(@RequestBody ${className}RequestProtocol ${className}RequestProtocol) throws Exception {
		return service.update${className}(${className}RequestProtocol);
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ResponseProtocol list(${className}RequestProtocol ${className}RequestProtocol) throws Exception {
		return service.pageQuery(${className}RequestProtocol);
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ResponseProtocol edit(@RequestBody ${className}RequestProtocol ${className}RequestProtocol) throws Exception {
		return service.get${className}(${className}RequestProtocol);
	}
	
}
