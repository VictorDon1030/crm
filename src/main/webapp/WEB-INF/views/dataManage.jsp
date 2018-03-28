<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <title>数据管理</title>
    <script type="text/javascript">
        $(function () {
            $('#btn-clean').click(function () {
            var checks = $('input[type=checkbox]');
            //sssssssssssssssssssssssssssssssssssssssssssssssssssss
            for (var i in checks){

            }
            });
        });
    </script>
    <style type="text/css">
        .bg{
            font-weight: bolder;
            font-family: "STSong";
        }
    </style>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',width:'auto',height:160,border:false">
            <p style="margin-top: 10px;margin-left: 20%"><span><font size="4">数据初始化</font></span></p>
            <div style="background-color: lightpink;width: 60%;margin-left: 25%">
                <p style="margin-top: 10px">
                    <span class="bg"><font size="4">
                        *注意！数据是您最宝贵的财富。您不要随便初始化数据。初始化的店铺数据将永远无法恢复。</br>
                        误删,错删,请联系110或120</br>
                        坦白从宽,牢底坐穿
                        </font>
                    </span></p>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <p style="margin-top: 10px;margin-left: 20%"><span><font size="4">选择初始化内容</font></span></p>
            <div style="background-color: whitesmoke;width: 60%;margin-left: 25%">
                <p style="margin-top: 10px">
                <input type="checkbox" data-id="1"/>
                    清除员工信息 | 删除全部员工信息、员工储值、积分、优惠券等所有记录
                </p>
                <p style="margin-top: 10px">
                <input type="checkbox" data-id="2"/>
                    清除商品信息 | 删除全部商品信息（保留商品分类）、信息
                </p>
                <p style="margin-top: 10px">
                <input type="checkbox" data-id="3"/>
                    清除商品出入库信息 删除所有商品出入库信息
                </p>
            </div>

            <div style="margin-top: 40px;margin-left: 20%">
                <button id="btn-clean" style="background-color: limegreen;width: 120px;height: 50px"><font size="3">初始化数据</font></button>
            </div>
        </div>
    </div>
</body>
</html>
