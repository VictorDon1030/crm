<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/employee.js"></script>
    <title>日常支出</title>
</head>
<body>

<div class="easyui-layout" data-options="fit:true" style="width:700px;height:350px;">
    <div data-options="region:'north'" style="height:60px">
        <!--选项卡：用2个a标签-->
        <div style="margin-top: 10px;margin-bottom: 30px;">
            <a href="javascript:window.location.href='/pay/view.do'"><font style="font-size: 18px;">支出录入</font></a>
            <a href="javascript:window.location.href='/payItem/view.do'"><font style="font-size: 18px;">支出明细</font></a>
        </div>
    </div>

    <div data-options="region:'center',iconCls:'icon-ok'"  style="padding:5px">
        <div class="easyui-layout" data-options="fit:true">
            <!--东布局-->
            <div data-options="region:'east',split:true,collapsible:false" style="width:450px"></div>
            <!--西布局-->
            <div data-options="region:'west',split:true,collapsible:false"  style="width:380px">
                <form action="" method="post">
                    <table style="margin-top: 15px;margin-left:20px;width:330px;height: 60px;">
                        <tr>
                            <td>
                                <select class="easyui-combobox" name="name" data-options="url:'/maxType/selectAll.do',
                                prompt:'支出大分类',width:220, panelHeight:'auto',valueField:'id',textField:'name'" id="maxType">
                                </select>
                            </td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">分类设置</a>
                            </td>
                        </tr>
                        <!--数据表格-->
                        <tr>
                            <table id="pay_datagrid"></table>
                        </tr>
                    </table>
                </form>
            </div>
            <!--中布局-->
            <div data-options="region:'center',collapsible:false"  ></div>
        </div>
    </div>
</div>
</body>
</html>
