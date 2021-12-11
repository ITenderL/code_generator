<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${basepackage}.dao.mapper.${className}Mapper">
    <!-- CodeBuilder  Generated-->
    <resultMap id="BaseResultMap" type="${basepackage}.dao.entity.${className}">
    <#list table.columns as column>
    <#if column.sqlName=="id">
	<id column="${column.sqlName}" jdbcType="${column.jdbcSqlTypeName}" property="${column.columnNameFirstLower}" />
	<#else>
	<result column="${column.sqlName}" property="${column.columnNameFirstLower}" jdbcType="${column.jdbcSqlTypeName}"/>
	</#if>
	</#list>

    </resultMap>
    <sql id="Example_Where_Clause">
        <where>
            <foreach collection="example.oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" suffix=")" prefixOverrides="and">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${r"${criterion.condition}"}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${r"${criterion.condition}"} ${r"#{criterion.value}"}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${r"${criterion.condition}"} ${r"#{criterion.value}"} and ${r"#{criterion.secondValue}"}
                                </when>
                                <when test="criterion.listValue">
                                    and ${r"${criterion.condition}"}
                                    <foreach collection="criterion.value" item="listItem" open="(" close=")"
                                             separator=",">
                                        ${r"#{listItem}"}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>
    <sql id="Table_Name">${table.sqlName}</sql>
    <sql id="Base_Column_List">
        <#list table.columns as column>  ${column.sqlName} <#if column_has_next>,</#if> </#list>
    </sql>
    <sql id="Insert_Columns">
    <#list table.columns as column>   
    <if test="${column.columnNameFirstLower} != null">${column.sqlName},</if>
    </#list>
    </sql>
    <sql id="Insert_Values">
    <#list table.columns as column>   
    <if test="${column.columnNameFirstLower} != null">${r"#{"}${column.columnNameFirstLower}${r"}"},</if>
    </#list>
        
    </sql>
    <sql id="Batch_Insert_Values">
       
     <#list table.columns as column>   
     ${r"#{"}${column.columnNameFirstLower}${r"}"},
    </#list>
    </sql>
    <sql id="Update_Set_From_Bean">
      <#list table.columns as column> 
      <#if !column.isPk()>  
     <if test="${column.columnNameFirstLower} != null">${column.sqlName} = ${r"#{"}${column.columnNameFirstLower}${r"}"},</if>
     </#if>
    </#list>

    </sql>
    <!-- insert -->
    <insert id="insertbean" parameterType="java.util.Map">
        insert into
        <include refid="Table_Name"/>
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="Insert_Columns"/>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <include refid="Insert_Values"/>
        </trim>
    </insert>
    <insert id="batchInsert" parameterType="java.util.Map">
        insert into
        <include refid="Table_Name"/>
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="Base_Column_List"/>
        </trim>
        values
        <foreach collection="records" item="record" index="index" separator=",">
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <include refid="Batch_Insert_Values"/>
            </trim>
        </foreach>
    </insert>
    <!-- end insert -->
    <!-- delete -->
    <delete id="deleteById" parameterType="java.util.Map">
        delete from
        <include refid="Table_Name"/>
        where id = ${r"#{id}"}
    </delete>
   
    <delete id="deleteExtByExample" parameterType="java.util.Map">
        delete from
        <include refid="Table_Name"/>
        <if test="example != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </delete>
   
    <delete id="deleteIn" parameterType="java.util.Map">
        delete from
        <include refid="Table_Name"/>
        where id in
        <foreach collection="records" item="record" index="index" open="(" separator="," close=")">
            ${r"#{id}"}
        </foreach>
    </delete>
    <!-- end delete -->
  
    <update id="updateById" parameterType="java.util.Map">
        update
        <include refid="Table_Name"/>
        <set>
            <include refid="Update_Set_From_Bean"/>
        </set>
        where id = ${r"#{id}"}
    </update>
    
    <update id="updateExtByExample" parameterType="java.util.Map">
        update
        <include refid="Table_Name"/>
        <set>
            <include refid="Update_Set_From_Bean"/>
        </set>
        <if test="example != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </update>
    
    <update id="batchUpdate" parameterType="java.util.Map">
        <foreach collection="records" item="record" index="index" open="" close="" separator=";">
            update
            <include refid="Table_Name"/>
            <set>
                <include refid="Update_Set_From_Bean"/>
            </set>
            where id=${r"#{id}"}
        </foreach>
    </update>
    <!-- end update -->
    <!-- select -->
    <select id="selectById" resultMap="BaseResultMap" parameterType="java.util.Map">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="Table_Name"/>
        where id = ${r"#{id}"}
    </select>
      
    <select id="selectExtByExample" resultMap="BaseResultMap" parameterType="java.util.Map">
        select
        <if test="example != null and example.distinct">
            distinct
        </if>
        <include refid="Base_Column_List"/>
        from
        <include refid="Table_Name"/>
        <if test="example != null">
            <include refid="Example_Where_Clause"/>
        </if>
        <if test="example != null and example.orderByClause != null">
            order by ${r"${example.orderByClause}"}
        </if>
    </select>
    
    <select id="selectOneByExample" resultMap="BaseResultMap" parameterType="java.util.Map">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="Table_Name"/>
        <if test="example != null">
            <include refid="Example_Where_Clause"/>
        </if>
        limit 1
    </select>
    <select id="selectIn" resultMap="BaseResultMap" parameterType="java.util.Map">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="Table_Name"/>
        where id IN
        <foreach collection="records" item="record" index="index" open="(" separator="," close=")">
            ${r"#{id}"}
        </foreach>
    </select>
    <select id="countByExample" resultType="java.lang.Integer" parameterType="java.util.Map">
        select count(*) as total from
        <include refid="Table_Name"/>
        <if test="example != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </select>
    <select id="pageCountBy" resultType="java.lang.Integer" parameterType="java.util.Map">
        select count(*) as total from
        <include refid="Table_Name"/>
        <if test="example != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </select>
    <!-- 自定义分页 依赖core包-->
     <select id="countByPager" resultType="java.lang.Long" parameterType="java.util.Map">
        select count(*) as total from
        <include refid="Table_Name"/>
        <if test="example != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </select>
    <select id="selectByPager" resultMap="BaseResultMap" parameterType="java.util.Map">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="Table_Name"/>
        <if test="example != null">
            <include refid="Example_Where_Clause"/>
        </if>
        <if test="pager.sortItem != null and pager.sortItem != '' ">
            order by ${r"${pager.sortItem}"} ${r"${pager.sortType}"}
        </if>
        limit ${r"#{pager.startIndex}"} , ${r"#{pager.pageSize}"}
    </select>
    
     <!-- 拦截分页 -->
    <select id="pageQuery" resultMap="BaseResultMap" parameterType="java.util.Map">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="Table_Name"/>
        where 1=1
        <#list table.columns as column>   
        <if test="record.${column.columnNameFirstLower} !=null and record.${column.columnNameFirstLower} !=''">
			and ${column.sqlName} =${r"#{record."}${column.columnNameFirstLower}${r"}"} 
		</if>
       </#list>
    </select>
    <!-- end select -->
    <!-- My Custom Interfaces -->
</mapper>
