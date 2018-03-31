<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/management.js"></script>
    <title>大杂烩</title>
</head>

<body>
    <div id="tt" class="easyui-tabs"
         data-options="fit:true,border:false">
        <div title="管理员管理" style="display:none;">
            <iframe src="/managerMan/view.do" width="100%" height="100%" frameborder="0"></iframe>
        </div>
        <div  title="员工管理" data-options="closable:true" style="overflow:auto;display:none;">
            <iframe id="empManage" width="100%" height="100%" frameborder="0"></iframe>
        </div>
        <div title="角色权限管理" data-options="closable:true" style="display:none;" align="center">
            <iframe id="roleManage" width="100%" height="100%" frameborder="0"></iframe>
        </div>
    </div>
</body>
</html>
