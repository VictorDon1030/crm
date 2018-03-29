<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/linkage.js"></script>


    <title>欢迎光临</title>

</head>
 <%--   <body>
        <div id="cc" class="easyui-layout" style="width:700px;height:350px;">
            <div data-options="region:'west'" style="width:150px;"></div>
            <div data-options="region:'center'" style="padding:20px"> </div>

            <div id="menu" data-options="region:'west'," title="系统菜单" style="width:150px; background-color: #99cdff">
                <ul class="easyui-tree"  ></ul> <!--小树苗-->
            </div>
            <div  data-options="region:'center'" style="background:#eee">
                <div id="tabs" class="easyui-tabs" data-options="fit:true, border:false">
                    <div title="首页" > <!--style="padding: 20px; display: none"-->
                        您好!
                    </div>
                </div>
            </div>

        </div>
    </body>--%>
<body>

<div id="cc" class="easyui-layout" data-options="fit:true">

    <div id="menu" data-options="region:'west',border:false" title="系统菜单" style="width:25%;  background-color: #F5F5F5;">
        <ul class="easyui-tree"  ></ul> <!--小树苗-->
    </div>
    <%--<div  data-options="region:'center'" >
        <div id="tabs" class="easyui-tabs" style="width:35%;">

        </div>
    </div>--%>
    <div data-options="region:'center'" style="padding:20px;">

        <form id="min_form" method="post">
                <div class="easyui-panel" data-options="width:'50%',border:false" style="background-color: #F5F5F5;">

                </div>

        </form>

    </div>
</div>

</body>
</html>




















