<#assign myParentDir="entity">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao.entity;

<#list table.columns as column>
	<#if column.isDateTimeColumn>
import java.text.ParseException;
	<#break/>
	</#if>
</#list>

import com.angogo.rpa.console.common.domain.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

<#include "/copyright_class.include" >
@Data
@Entity
@DynamicUpdate
@Table(name="${table.sqlName}")
public class ${className}  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	//columns START
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
	@Column(name = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameFirstLower};
	</#if>
	</#list>
	//columns END 数据库字段结束
	
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
	
	

