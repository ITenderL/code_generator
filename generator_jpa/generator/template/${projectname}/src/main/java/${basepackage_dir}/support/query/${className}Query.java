<#assign myParentDir="entity">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.support.query;

<#list table.columns as column>
	<#if column.isDateTimeColumn>
import java.text.ParseException;
	<#break/>
	</#if>
</#list>

import com.angogo.rpa.console.support.annotation.Query;
import com.angogo.rpa.console.support.model.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<#include "/copyright_class.include" >
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${className}Query  extends BaseQuery {
	
	private static final long serialVersionUID = 1L;
	

	<#list table.columns as column>
	<#if column.sqlName!="id"
	 && column.sqlName!="create_time"
	 && column.sqlName!="update_time"
	 && column.sqlName!="creator"
	 && column.sqlName!="updater"
	 && column.sqlName!="is_deleted" >
	/**
	 * ${column.columnAlias}
	 */
	@ApiModelProperty("${column.columnAlias}")
	@Query
	private ${column.javaType} ${column.columnNameFirstLower};
	</#if>
	</#list>
	
}

	
	

