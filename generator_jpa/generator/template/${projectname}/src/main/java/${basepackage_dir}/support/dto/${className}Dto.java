<#assign myParentDir="entity">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.support.vo;

import com.angogo.rpa.console.support.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<#include "/copyright_class.include" >
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${className}Dto  extends BaseModel {
	
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
	private ${column.javaType} ${column.columnNameFirstLower};
	</#if>
	</#list>
	
}

	
<#macro generateJavaColumns>
	<#list table.columns as column>
	<#if column.sqlName!="id">
		/**
		 * ${column.columnAlias}
		 */
	public void set${column.columnNameFirstUpper}(${column.javaType} value) {
	     <#if column.javaType=="java.lang.String">
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		 </#if>
		this.${column.columnNameFirstLower} = value;
	}
	
	
	
	/**
	 * ${column.columnAlias}
	 */
	

	
	public ${column.javaType} get${column.columnNameFirstUpper}() {
		return this.${column.columnNameFirstLower};
	}
	</#if>
	</#list>
</#macro>



<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
	
	

