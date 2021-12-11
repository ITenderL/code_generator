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
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.yqjp.framework.core.entity.BaseEntity;
<#include "/copyright_class.include" >
@Table(name="${table.sqlName}")
public class ${className}  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "${table.tableAlias}";
	<#list table.columns as column>
	public static final String ALIAS_${column.constantName} = "${column.columnAlias}";
	</#list>
    */
	//date formats
	<#list table.columns as column>
	
	<#if !column.isNullable()>
	public static final String MY_FIELD_${column.constantName}="${column.columnNameFirstLower}";
	</#if>
	</#list>
	
	//columns START
	<#list table.columns as column>
	<#if column.sqlName!="id">
	/**
	 * ${column.columnAlias}
	 */
	@Column(name = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameFirstLower};
	</#if>
	</#list>
	//columns END 数据库字段结束
	
	//concstructor
	public ${className}(){
	}


	//get and set
	<@generateJavaColumns/>
	

	
	@Override
	public String toString() {
		return new StringBuilder()
		<#list table.columns as column>
		<#if column.sqlName!="id">
			.append("${column.columnAlias}[").append(get${column.columnNameFirstUpper}()).append("],")
		</#if>	
		</#list>
			.toString();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		<#list table.pkColumns as column>
			.append(get${column.columnNameFirstUpper}())
		</#list>
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ${className} == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
			.append(get${column.columnNameFirstUpper}(),other.get${column.columnNameFirstUpper}())
			</#list>
			.isEquals();
	}
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
	
	

