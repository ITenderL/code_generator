<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao.mapper;

import org.apache.ibatis.annotations.Param;
import com.yqjp.framework.core.page.PageInfo;
import java.util.List;
import com.yqjp.data.mybatis.BaseMapper;
import com.yqjp.mybatis.plugin.pagination.Page;
import com.yqjp.mybatis.plugin.pagination.PageParams;
import com.yqjp.mybatis.plugin.pagination.annotation.Pageable;
import ${basepackage}.domain.${className}Example;
import ${basepackage}.dao.entity.${className};

public interface ${className}Mapper  extends BaseMapper<${className}>{

	/**
	 * 插入一条记录
	 * @return
	 */
	int insertbean(${className} ${className}) throws Exception;
	   
	/**
	 * 插入多条记录
	 * @return
	 */
    int batchInsert(List<${className}> ${className}) throws Exception;

    /**
	 * 删除记录
	 * @return
	 */
    int deleteById(@Param("id") long id) throws Exception;

    /**
	 * 删除记录
	 * @return
	 */
	int deleteExtByExample(@Param("example") ${className}Example example) throws Exception;

	/**
	 * 删除多条记录
	 * @return
	 */
	int deleteIn(String ids) throws Exception;

	/**
	 * 更新一条记录
	 * @return
	 */
	int updateById(${className} obj) throws Exception;

	/**
	 * 通过组合条件获取分页
	 * @return
	 */
	@Pageable
	Page<${className}> pageQuery(@Param("pageParams") PageParams pageParams,@Param("record") ${className} obj) throws Exception;

	
	
	/**
	 * 通过组合条件获取分页
	 * @return
	 */

	List<${className}> selectByPager(@Param("pager") PageInfo pager,@Param("example") ${className}Example example) throws Exception;

	
	/**
	 * 更新记录
	 * @return
	 */
	int updateExtByExample(@Param("example") ${className}Example example) throws Exception;

	/**
	 * 批量更新记录
	 * @return
	 */
	int batchUpdate(List<${className}> ${className}) throws Exception;

	/**
	 * 获取记录记录
	 * @return
	 */
	List<${className}> selectById(@Param("id") long id) throws Exception;

	/**
	 * 通过组合条件获取记录
	 * @return
	 */
	List<${className}> selectExtByExample(@Param("example") ${className}Example example) throws Exception;

	/**
	 * 通过条件获取一条记录
	 * @return
	 */
	${className} selectOneByExample(@Param("example") ${className}Example example) throws Exception;

	/**
	 * 通过条件获取多条记录
	 * @return
	 */
	int selectIn(String ids) throws Exception;

	/**
	 * 通过条件获取记录数
	 * @return
	 */
	int countByExample(@Param("example") ${className}Example example) throws Exception;

	/**
	 * 通过分页条件获取记录数
	 * @return
	 */
	int pageCountBy(@Param("example") ${className}Example example) throws Exception;
	
	
}