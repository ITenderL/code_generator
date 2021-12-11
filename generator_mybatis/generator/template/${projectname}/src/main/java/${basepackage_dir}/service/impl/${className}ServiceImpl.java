<#assign myParentDir="impl">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service.impl;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basepackage}.support.${className}RequestProtocol;
import com.yqjp.data.mybatis.MybatisAbstractService;
import com.yqjp.framework.core.entity.EntityUtil;
import com.yqjp.framework.core.protocol.BaseResponseProtocol;
import com.yqjp.framework.core.exception.SystemException;
import com.yqjp.framework.core.protocol.PaginationResponseProtocol;
import com.yqjp.framework.core.protocol.ResponseMessage;
import com.yqjp.framework.core.protocol.ResponseProtocol;
import com.yqjp.framework.core.reflect.FieldValidator;
import com.yqjp.mybatis.plugin.pagination.Page;
import com.yqjp.mybatis.plugin.pagination.PageParams;
import com.yqjp.framework.core.entity.EntityUtil;
import ${basepackage}.dao.entity.${className};
import ${basepackage}.dao.mapper.${className}Mapper;
import ${basepackage}.service.I${className}Service;
import ${basepackage}.domain.${className}Example;
import com.yqjp.framework.core.page.PageInfo;

@Service
<#include "/copyright_class.include" >
public class ${className}ServiceImpl extends MybatisAbstractService<${className},${className}RequestProtocol>  implements I${className}Service  {

	private static final Logger LOG = LoggerFactory.getLogger(${className}ServiceImpl.class);

	@Autowired
	${className}Mapper ${className}Mapper;

	@Override
	public ResponseProtocol get${className}(${className}RequestProtocol ${className}RequestProtocol) throws Exception {
		FieldValidator.validate(${className}RequestProtocol,
				new String[] { ${className}.base_field_id});
		BaseResponseProtocol  response= new BaseResponseProtocol();
		try {	
//			${className} ${className} = new ${className}();
//			EntityUtil.updateEntity(${className}RequestProtocol, ${className});
			List<${className}> objbean=${className}Mapper.selectById(${className}RequestProtocol.getId());
			response.setData(objbean);
			response.setIsSuccess(true);
			response.writeMessage(ResponseMessage.CODE_SUCCESS);
			return response;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new SystemException();
		}
		//return this.findById(${className}Mapper, ${className}RequestProtocol);
	
	}
	/**
	 * 新增收货地址
	 */
	@Override
	public ResponseProtocol add${className}(${className}RequestProtocol ${className}RequestProtocol) throws Exception {
		FieldValidator.validate(${className}RequestProtocol,
				new String[] { 
						<#list table.columns as column>
						<#if !column.isNullable()&&!column.isPk()>
						${className}.MY_FIELD_${column.constantName}<#if column_has_next>,</#if>
						</#if>
						</#list>
						});
		BaseResponseProtocol  response= new BaseResponseProtocol();
		try {	
			${className} ${className} = new ${className}();
			EntityUtil.updateEntity(${className}RequestProtocol, ${className});
			int objbean=${className}Mapper.insertbean(${className});
			response.setData(objbean);
			response.setIsSuccess(true);
			response.writeMessage(ResponseMessage.CODE_SUCCESS);
			return response;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new SystemException();
		}
		//return this.saveEntity(${className}Mapper, ${className}RequestProtocol);
	}
	/**
	 * 删除地址
	 */
	@Override
	public ResponseProtocol delete${className}(${className}RequestProtocol ${className}RequestProtocol) throws Exception {
		FieldValidator.validate(${className}RequestProtocol,
				new String[] { ${className}.base_field_id });
		BaseResponseProtocol  response= new BaseResponseProtocol();
		try {	
			${className}Mapper.deleteById(${className}RequestProtocol.getId());
			response.setIsSuccess(true);
			response.writeMessage(ResponseMessage.CODE_SUCCESS);
			return response;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new SystemException();
		}
		//return this.deleteEntity(${className}Mapper,${className}RequestProtocol);
	}
	/**
	 * 修改地址
	 */
	@Override
	public ResponseProtocol update${className}(${className}RequestProtocol ${className}RequestProtocol) throws Exception {
		FieldValidator.validate(${className}RequestProtocol,
				new String[] { 
						<#list table.columns as column>
						<#if !column.isNullable()&&!column.isPk()>
						${className}.MY_FIELD_${column.constantName}<#if column_has_next>,</#if>
						</#if>
						</#list>
                         });
		BaseResponseProtocol  response= new BaseResponseProtocol();
		try {	
			${className} ${className} = new ${className}();
			EntityUtil.updateEntity(${className}RequestProtocol, ${className});
			${className}Mapper.updateById(${className});
			response.setIsSuccess(true);
			response.writeMessage(ResponseMessage.CODE_SUCCESS);
			return response;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new SystemException();
		}
//		return	this.updateEntity(${className}Mapper,${className}RequestProtocol);
	}
	/**
	 * 分页查询
	 */
	@Override
	public ResponseProtocol pageQuery(${className}RequestProtocol ${className}RequestProtocol) throws Exception {
		PaginationResponseProtocol response=new PaginationResponseProtocol();
		try {
			${className} ${classNameLower} = new ${className}();
			EntityUtil.updateEntity(${className}RequestProtocol, ${classNameLower});
			//增加查询语句
	        final ${className}Example example = new ${className}Example();
	        final ${className}Example.Criteria criteria =example.createCriteria();
	      	<#list table.columns as column>
	    	<#if column.javaType=="java.lang.String">
            if(${classNameLower}.get${column.columnNameFirstUpper}()!=null) {
            	criteria.andFieldLike("${column.sqlName}", ${classNameLower}.get${column.columnNameFirstUpper}());
			}
	        </#if>
	    	</#list>
	    	 //采用自定义分页
            int total=${className}Mapper.pageCountBy(example);
            response.setTotal((long)total);
			List<${className}> ${className}PageList=${className}Mapper.selectByPager(new PageInfo(${className}RequestProtocol.getPageNo()
					, ${className}RequestProtocol.getPageSize()
					, ${className}RequestProtocol.getSortItem()
					, ${className}RequestProtocol.getSortType()
					),example);
			if(${className}PageList!=null) {
				response.setPageNo(${className}RequestProtocol.getPageNo());
				response.setPages(total/${className}RequestProtocol.getPageSize()+1);
				response.setPageSize(${className}RequestProtocol.getPageSize());
				response.setData(${className}PageList);
				response.writeMessage(ResponseMessage.CODE_SUCCESS);
				return response;
			}
			return response;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new SystemException();
		}
	}


}
