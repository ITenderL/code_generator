<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign gatewayname = gatewayname>

<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#include "/copyright_js.include" >
		<div style="margin: 15px;">
			<form class="layui-form" action="">
													<#list table.columns as column>
														    <#if !column.pk>
																<#assign columnValue = "("+classNameLower+"."+column.columnNameFirstLower+")!''">
																<#if column.isDateTimeColumn>
																	<!--日期型-->
																	<#assign columnDataValue = column.columnNameFirstLower+")?string('yyyy-MM-dd'))!'' ">
																	<div class="layui-form-item col-lg-6">
																		<label class="layui-form-label">${column.columnAlias}*</label>
																		<div class="layui-inline col-lg-5">  
																			${r"${((returnDatas.data."}${columnDataValue}${r"}"}
																		</div>
																		<div class="layui-inline valid-info"></div>
																	</div>
																<#else>
																	<div class="layui-form-item col-lg-6">
																		<label class="layui-form-label">${column.columnAlias}*</label>
																		<div class="layui-inline col-lg-5">  
																			${r"${(returnDatas.data."}${column.columnNameFirstLower}${r")!''}"}
																		</div>
																		<div class="layui-inline valid-info"></div>
																	</div>
																</#if>
															</#if>
													</#list>

				
				<!-- <div class="layui-form-item">
					<label class="layui-form-label">手机</label>
					<div class="layui-input-block">
						<input type="tel" name="mobilePhone"  autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱</label>
					<div class="layui-input-block">
						<input type="text" name="email" autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">出生日期</label>
					<div class="layui-input-block">
						<input type="text" name="birthday" id="date"  placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">单选框</label>
					<div class="layui-input-block">
						<input type="radio" name="sex" value="男" title="男" checked="">
						<input type="radio" name="sex" value="女" title="女">
						<input type="radio" name="sex" value="禁" title="禁用" disabled="">
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<textarea class="layui-textarea layui-hide" name="description"  id="description_editor"></textarea>
					</div>
				</div> -->

				<button lay-filter="edit" lay-submit style="display: none;"></button>
			</form>
		</div>