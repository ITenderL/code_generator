﻿<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Table</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="plugins/bootstrap-table/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="css/layui.css" media="all"/>
    <link rel="stylesheet" href="css/global.css" media="all">
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
</head>

<body>
<div class="admin-main">
    <form  class="layui-form layui-form-pane" onsubmit="javascript:return false;">
        <div class="layui-form-item" style="margin-bottom:0px;">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" onchange="javascript:$('#btn_query').click();" class="layui-input" id="name">
            </div>
            <button type="button" id="btn_query"  class="layui-btn layui-btn">
                <i class="fa fa-search" aria-hidden="true"></i>
                </button>
        </div>
    </form>
    <script type="text/html" id="tpl">
        {{# layui.each(d.list, function(index, item){ }}

        {{# }); }}
    </script>
    <div  id="toolbar" class="layui-btn-group">
        <button class="layui-btn layui-btn-small" id="btn_add">
            <i class="layui-icon">&#xe654;</i>
        </button>
        <button class="layui-btn layui-btn-small" id="btn_edit">
            <i class="layui-icon">&#xe642;</i>
        </button>
        <button class="layui-btn layui-btn-small" id="btn_del">
            <i class="layui-icon">&#xe640;</i>
        </button>
    </div>
    <table id="userTable">

    </table>

</div>

<script type="text/javascript" src="plugins/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap.min.js"></script>
<script type="text/javascript" src="plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="layui.js"></script>
<script type="text/javascript" src="ag/common/util.js"></script>
<script type="text/javascript" src="ag/user/user.js"></script>
</body>

</html>