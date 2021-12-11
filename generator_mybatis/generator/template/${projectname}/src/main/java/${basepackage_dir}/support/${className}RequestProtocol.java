<#assign myParentDir="support">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.support;

import java.math.BigDecimal;
import javax.persistence.Column;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.yqjp.framework.core.protocol.PaginationRequestProtocol;

public class ${className}RequestProtocol extends PaginationRequestProtocol {
	private static final long serialVersionUID = 1L;
		//排序字段
	private String sortItem;
	
	private String sortType;
	
	public String getSortItem() {
		return sortItem;
	}
	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
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
	private ${column.javaType} ${column.columnNameFirstLower};
	</#if>
	</#list>
	//columns END 数据库字段结束
	
	//concstructor
	public ${className}RequestProtocol(){
	}
	
	//get and set
	<@generateJavaColumns/>
	
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
	


	@Override
	public String toString() {
		return new StringBuilder()
		<#list table.columns as column>
			.append("${column.columnAlias}[").append(get${column.columnNameFirstUpper}()).append("],")
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
		if(obj instanceof ${className}RequestProtocol == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		${className}RequestProtocol other = (${className}RequestProtocol)obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
			.append(get${column.columnNameFirstUpper}(),other.get${column.columnNameFirstUpper}())
			</#list>
			.isEquals();
	}
}
