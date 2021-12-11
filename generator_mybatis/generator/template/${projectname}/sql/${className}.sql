<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign targetpackage = targetpackage>


<#assign pid="business_manager" />
<#if table.sqlName?starts_with("t_")>
<#assign pid="system_manager" />
</#if>


INSERT INTO `t_base_element` VALUES ('1', '${classNameLowerCase}Manage:btn_add', 'button', '新增', '/${gatewayname}/${classNameLowerCase}/add', '9', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_base_element` VALUES ('2', '${classNameLowerCase}Manage:btn_update', 'button', '修改', '/${gatewayname}/${classNameLowerCase}/update', '9', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_base_element` VALUES ('3', '${classNameLowerCase}Manage:btn_del', 'button', '删除', '/${gatewayname}/${classNameLowerCase}/delete', '9', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_base_element` VALUES ('4', '${classNameLowerCase}Manage:btn_list', 'button', '查询', '/${gatewayname}/${classNameLowerCase}/list', '9', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_base_element` VALUES ('5', '${classNameLowerCase}Manage:btn_edit', 'button', '编辑', '/${gatewayname}/${classNameLowerCase}/edit', '10', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Records of t_base_menu
-- ----------------------------

INSERT INTO `t_base_menu` VALUES ('2', '${classNameLowerCase}', '${classNameLowerCase}管理', '1', '', 'fafa-user', null, '0', '', '/${gatewayname}', null, null, null, null, null, '2017-08-30 19:03:54', null, null, null, null, null, null, null, null, null, null, null);

-- Records of t_base_resource_authority
-- ----------------------------
INSERT INTO `t_base_resource_authority` VALUES ('1', '1', 'group', '1', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_base_resource_authority` VALUES ('2', '1', 'group', '2', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_base_resource_authority` VALUES ('3', '1', 'group', '3', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_base_resource_authority` VALUES ('4', '1', 'group', '4', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_base_resource_authority` VALUES ('5', '1', 'group', '5', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_base_resource_authority` VALUES ('6', '1', 'group', '2', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);