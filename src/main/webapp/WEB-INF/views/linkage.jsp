<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/linkage.js"></script>


    <title>欢迎光临</title>
    <%--声明一个样式提供--%>
    <style type="text/css">
        .myBtn{
            background-color: #ffffb6;

        }
    </style>

</head>


<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west',width:260" align="center" style="background-color: #7dc9e2">
        <div>
            <img src="/static/img/23817.png" alt="截得一手好图"/>
        </div>
        <div><font size="6.5" color="#ff7f50">德客便利店</font></div>
        <p><div><font size="5.5" color="blue">一级分类</font></div></p>
        <p style="color: #CC2222">&emsp;<a id="fruits" class="easyui-linkbutton" name="fruits" data-options="iconCls:'icon-redo', plain:true " data-cmd="fruits"><span style="font-size: 22px;" >水果类</span></a>
        <p style="color: #CC2222">&emsp;<a id="drinks"class="easyui-linkbutton" name="drinks" data-options="iconCls:'icon-redo', plain:true " data-cmd="drinks"><span style="font-size: 22px;" >酒水类</span></a>
        <p style="color: #CC2222">&emsp;<a id="foodstuff"class="easyui-linkbutton" name="foodstuff" data-options="iconCls:'icon-redo', plain:true " data-cmd="foodstuff"><span style="font-size: 22px;" >食品类</span></a>
        <p style="color: #CC2222">&emsp;<a id="vegetables"class="easyui-linkbutton" name="vegetables" data-options="iconCls:'icon-redo', plain:true "  data-cmd="vegetables"><span style="font-size: 22px;" >蔬菜类</span></a>
        <p style="color: #CC2222">&emsp;<a id="grain"class="easyui-linkbutton" name="grain" data-options="iconCls:'icon-redo', plain:true " data-cmd="grain"><span style="font-size: 22px;" >粮油类</span></a>
        <p style="color: #CC2222">&emsp;<a id="aquatic"class="easyui-linkbutton" name="aquatic" data-options="iconCls:'icon-redo', plain:true " data-cmd="aquatic"><span style="font-size: 22px;" >水产类</span></a>
        <p style="color: #CC2222">&emsp;<a id="child"class="easyui-linkbutton" name="child" data-options="iconCls:'icon-redo', plain:true " data-cmd="child"><span style="font-size: 22px;" >娃娃类</span></a>
    </div>
    <div data-options="region:'center'" >
        <table id="linkage_datagrid" class="easyui-datagrid" >
        </table>
    </div>
</div>


<%--<div id="cc" class="easyui-layout" data-options="fit:true">

    <div id="menu" data-options="region:'west',border:false" title="系统菜单" style="width:25%;  background-color: #F5F5F5;">
        <ul class="easyui-tree"  ></ul> <!--小树苗-->
    </div>
    &lt;%&ndash;<div  data-options="region:'center'" >
        <div id="tabs" class="easyui-tabs" style="width:55%;">

        </div>
    </div>&ndash;%&gt;
    <div data-options="region:'center'" style="padding:20px;">

        <form id="min_form" method="post">
                <div class="easyui-panel" data-options="width:'50%',border:false" style="background-color: #F5F5F5;">
                    <p class="changColor" style="height: 25px"><a href="javascript:manage();" style='text-decoration:none;'><font size="5" color="white">员工管理</font></a></p>
                    <p class="changColor" style="height: 25px"><a href="#" style='text-decoration:none;'><font size="5" color="white">会员配置</font></a></p>
                    <p class="changColor" style="height: 25px"><a href="#" style='text-decoration:none;'><font size="5" color="white">商品配置</font></a></p>
                    <p class="changColor" style="height: 25px"><a href="#" style='text-decoration:none;'><font size="5" color="white">销售配置</font></a></p>
                    <p class="changColor" style="height: 25px"><a href="#" style='text-decoration:none;'><font size="5" color="white">数据管理</font></a></p>
                    <p class="changColor" style="height: 25px"><a href="#" style='text-decoration:none;'><font size="5" color="white">支付设置</font></a></p>

                </div>

        </form>

    </div>


</div>--%>



















</body>
</html>




















