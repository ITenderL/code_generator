<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign gatewayname = gatewayname>

<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#include "/copyright_js.include" >


var ${className} = {
	    baseUrl: "/${gatewayname}/${classNameLowerCase}",
	    entity: "${className}",
	    tableId: "${className}Table",
	    toolbarId: "toolbar",
	    unique: "id",
	    order: "asc",
	    currentItem: {}
	};
	${className}.columns = function () {
	    return [{
	        checkbox: true
	    },
	    <#list table.columns as column>  
	    {
	        field: '${column.sqlName}',
	        title: '${column.columnAlias}'
	    }<#if column_has_next>,</#if> </#list>
	    
	  ];
	};
	${className}.queryParams = function (params) {
	    if (!params)
	        return {
	            name: $("#name").val()
	        };
	    var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	        limit: params.limit, //页面大小
	        offset: params.offset, //页码
	        name: $("#name").val()
	    };
	    return temp;
	};

	${className}.init = function () {

	    ${className}.table = $('#' + ${className}.tableId).bootstrapTable({
	        url: ${className}.baseUrl + '/list', //请求后台的URL（*）
	        method: 'get', //请求方式（*）
	        toolbar: '#' + ${className}.toolbarId, //工具按钮用哪个容器
	        striped: true, //是否显示行间隔色
	        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	        pagination: true, //是否显示分页（*）
	        sortable: false, //是否启用排序
	        sortOrder: ${className}.order, //排序方式
	        queryParams: ${className}.queryParams,//传递参数（*）
	        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
	        pageNumber: 1, //初始化加载第一页，默认第一页
	        pageSize: 10, //每页的记录行数（*）
	        pageList: [], //可供选择的每页的行数（*）
	        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	        strictSearch: false,
	        showColumns: false, //是否显示所有的列
	        showRefresh: true, //是否显示刷新按钮
	        minimumCountColumns: 2, //最少允许的列数
	        clickToSelect: true, //是否启用点击选中行
	        uniqueId: ${className}.unique, //每一行的唯一标识，一般为主键列
	        showToggle: true, //是否显示详细视图和列表视图的切换按钮
	        cardView: false, //是否显示详细视图
	        detailView: false, //是否显示父子表
	        columns: ${className}.columns()
	    });
	};
	${className}.select = function (layerTips) {
	    var rows = ${className}.table.bootstrapTable('getSelections');
	    if (rows.length == 1) {
	        ${className}.currentItem = rows[0];
	        return true;
	    } else {
	        layerTips.msg("请选中一行");
	        return false;
	    }
	};

	layui.use(['form', 'layedit', 'laydate'], function () {
	    ${className}.init();

	    var editIndex;
	    var layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
	        layer = layui.layer, //获取当前窗口的layer对象
	        form = layui.form(),
	        layedit = layui.layedit,
	        laydate = layui.laydate;
	    var addBoxIndex = -1;
	    //初始化页面上面的按钮事件
	    $('#btn_query').on('click', function () {
	        ${className}.table.bootstrapTable('refresh', ${className}.queryParams());
	    });

	    $('#btn_add').on('click', function () {
	        if (addBoxIndex !== -1)
	            return;
	        //本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
	        $.get(${className}.baseUrl  + '/edit', null, function (form) {
	            addBoxIndex = layer.open({
	                type: 1,
	                title: '添加用户',
	                content: form,
	                btn: ['保存', '取消'],
	                shade: [0.5,'#000'],
	                shadeClose:false,
	                offset: ['20px', '20%'],
	                area: ['600px', '400px'],
	                maxmin: true,
	                yes: function (index) {
	                    layedit.sync(editIndex);
	                    //触发表单的提交事件
	                    $('form.layui-form').find('button[lay-filter=edit]').click();
	                },
	                full: function (elem) {
	                    var win = window.top === window.self ? window : parent.window;
	                    $(win).on('resize', function () {
	                        var $this = $(this);
	                        elem.width($this.width()).height($this.height()).css({
	                            top: 0,
	                            left: 0
	                        });
	                        elem.children('div.layui-layer-content').height($this.height() - 95);
	                    });
	                },
	                success: function (layero, index) {
	                    var form = layui.form();
	                    editIndex = layedit.build('description_editor');
	                    form.render();
	                    form.on('submit(edit)', function (data) {
	                        $.ajax({
	                            url: ${className}.baseUrl+'/add',
	                            type: 'post',
	                            data: data.field,
	                            dataType: "json",
	                            success: function (data) {
	                            	if(data.msg=='userName repeat!'){
	                            		layerTips.msg('用户名重复！');
	                            	}else{
	                            		layerTips.msg('保存成功');
	                            	}
	                                
	                                layerTips.close(index);
	                                location.reload();
	                            }

	                        });
	                        //这里可以写ajax方法提交表单
	                        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	                    });
	                    //console.log(layero, index);
	                },
	                end: function () {
	                    addBoxIndex = -1;
	                }
	            });
	        });
	    });
	    $('#btn_edit').on('click', function () {
	        if (${className}.select(layerTips)) {
	            var id = ${className}.currentItem.id;
	            $.get(${className}.baseUrl + '/' + id, null, function (data) {
	                var result = data.result;
	                $.get(${className}.baseUrl+'/edit', null, function (form) {
	                    layer.open({
	                        type: 1,
	                        title: '编辑用户',
	                        content: form,
	                        btn: ['保存', '取消'],
	                        shade: [0.5,'#000'],
	                        shadeClose:false,
	                        offset: ['20px', '20%'],
	                        area: ['600px', '400px'],
	                        maxmin: true,
	                        yes: function (index) {
	                            //触发表单的提交事件
	                            layedit.sync(editIndex);
	                            $('form.layui-form').find('button[lay-filter=edit]').click();
	                        },
	                        full: function (elem) {
	                            var win = window.top === window.self ? window : parent.window;
	                            $(win).on('resize', function () {
	                                var $this = $(this);
	                                elem.width($this.width()).height($this.height()).css({
	                                    top: 0,
	                                    left: 0
	                                });
	                                elem.children('div.layui-layer-content').height($this.height() - 95);
	                            });
	                        },
	                        success: function (layero, index) {
	                            var form = layui.form();
	                            setFromValues(layero, result);
	                            layero.find('#description_editor').val(result.description);
	                            editIndex = layedit.build('description_editor');
	                            form.render();
	                            layero.find(":input[name='username']").attr("disabled", "");
	                            form.on('submit(edit)', function (data) {
	                                $.ajax({
	                                    url: ${className}.baseUrl + "/update" + result.id,
	                                    type: 'put',
	                                    data: data.field,
	                                    dataType: "json",
	                                    success: function () {
	                                        layerTips.msg('更新成功');
	                                        layerTips.close(index);
	                                        location.reload();
	                                    }

	                                });
	                                //这里可以写ajax方法提交表单
	                                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	                            });
	                        }
	                    });
	                });
	            });
	        }
	    });
	    $('#btn_del').on('click', function () {
	        if (${className}.select(layerTips)) {
	            var id = ${className}.currentItem.id;
	            layer.confirm('确定删除数据吗？', null, function (index) {
	                $.ajax({
	                    url: ${className}.baseUrl + "/delete",
	                    data:{id:id},
	                    type: "DELETE",
	                    success: function (data) {
	                        if (data.rel == true) {
	                            layerTips.msg("移除成功！");
	                            location.reload();
	                        } else {
	                            layerTips.msg("移除失败！")
	                            location.reload();
	                        }
	                    }
	                });
	                layer.close(index);
	            });
	        }
	    });
	});

