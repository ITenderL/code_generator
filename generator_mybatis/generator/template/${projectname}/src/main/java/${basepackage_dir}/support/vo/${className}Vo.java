<#assign myParentDir="vo">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.support.vo;

<#list table.columns as column>
	<#if column.isDateTimeColumn>
import java.text.ParseException;
	<#break/>
	</#if>
</#list>

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Date;

<#include "/copyright_class.include" >

public class ${className}Vo {
	
	private static final long serialVersionUID = 1L;


	
	//columns START
	<#list table.columns as column>

	/**
	 * ${column.columnAlias}
	 */
	private ${column.javaType} ${column.columnNameFirstLower};

	</#list>
	//columns END 数据库字段结束
	
	//concstructor
		public ${className}Vo(){
		}

	//get and set
	<@generateJavaColumns/>
	
<#macro generateJavaColumns>
	<#list table.columns as column>
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
	</#list>
</#macro>
	
	
	
	
	@Override
	public String toString() {
		return new StringBuilder()
		<#list table.columns as column>
		<#if !column.isNullable()>
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
		if(obj instanceof ${className}Vo == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		${className}Vo other = (${className}Vo)obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
			.append(get${column.columnNameFirstUpper}(),other.get${column.columnNameFirstUpper}())
			</#list>
			.isEquals();
	}
}

	




