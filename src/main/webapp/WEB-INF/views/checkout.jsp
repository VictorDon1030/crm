<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%--<%@ include file="/static/common/common.jsp"%>--%>
    <link href="/static/plugins/easyuiplus/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
    <link href="/static/plugins/easyuiplus/easyui_full.css" rel="stylesheet" type="text/css">
    <link href="/static/plugins/easyuiplus/themes/insdep/icon.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/static/plugins/easyuiplus/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyuiplus/jquery.easyui-1.5.2.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyuiplus/insdep-extend.min.js"></script>
    <script type="text/javascript" src="/static/js/checkout.js"></script>
    <script language="JavaScript" type="text/javascript">
        var initializationTime = (new Date()).getTime();

        function showLeftTime() {
            var now = new Date();
            var year = now.getYear();
            var month = now.getMonth();
            var day = now.getDate();
            var hours = now.getHours();
            var minutes = now.getMinutes();
            var seconds = now.getSeconds();
            document.all.show.innerHTML = "" + (year + 1900) + "年" + (month + 1) + "月" + day + "日 " + hours + ":" + minutes + ":" + seconds + "";
            var timeID = setTimeout(showLeftTime, 1000);
        }
    </script>

</head>
<body onload="showLeftTime();">
<div id="cc" class="easyui-layout" style="width:750px;" fit="true">
    <div data-options="region:'north'">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-s',plain:true" onclick="fruits();">返回主页面</a>
    </div>
    <div data-options="region:'west',title:'收银订单明细',split:true">
        <form id="itemforms" method="post" id="checkoutItem">
            <span style="font-size: 20px;">销售时间: </span><label style="font-size: 20px" id="show">显示时间的位置</label>
            <div style="margin-left: 0px;margin-top: 10px">
                <span style="font-size: 20px;">销售单号: </span><input id="salesOdd" style="font-size: 20px;border: 0px;"
                            type="text" readonly="readonly" name="sn">
            </div>


            <div style="height: 350px" class="easyui-layout">
                <!--会员信息-->
                <div id="pd" class="pd" class="easyui-layout" data-options="title:'会员',region:'west',border:false"
                     style="width:230px;height:150px;">
                    <div style="margin-top: 50px">
                        <input id="inputId" name="member.id" type="hidden">
                        <p style="margin-top: 15px">会员卡号:<input name="member.memberNum" readonly="readonly" class="easyui-textbox"
                                       data-options="" style="width:150px;margin-top: 5px"></p>
                        <p style="margin-top: 15px">会员姓名:<input name="member.name" readonly="readonly" class="easyui-textbox" data-options=""
                                       style="width:150px;margin-top: 15px"></p>
                        <p style="margin-top: 15px">卡内余额:<input id="memberBalance" name="member.balance" readonly="readonly" class="easyui-textbox" data-options=""
                                       style="width:150px;margin-top: 15px"></p>
                        <p style="margin-top: 15px">会员积分:<input name="member.points" readonly="readonly" class="easyui-textbox" data-options=""
                                       style="width:150px;"></p>
                    </div>
                </div>
                <div class="easyui-layout" data-options="title:'商品明细',region:'center',border:false">
                    <table id="ashier_table" align="center" style="margin-top: 20px">
                        <thead>
                        <tr align="center">
                            <th>商品编码</th>
                            <th>商品名</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>小计</th>
                        </tr>
                        </thead>
                        <tbody id="cashier_item">
                        <tr align="center">
                            <td>
                                <input id="productSn" readonly="readonly" type="text"
                                       style="width:75px;border: 0px;text-align: center"
                                       tag="goodsMark"/>
                                <input id="productId" type="hidden" style="width:75px;border: 0px;text-align: center" tag="id"/>
                                <%--<input id="depot" name="depot.id" type="hidden" style="width:75px"/>--%>
                                <input type="hidden" style="width:75px;border: 0px;text-align: center" tag="purchasingPrice"/>
                                <input type="hidden" style="width:75px;border: 0px;text-align: center" tag="minPrice"/>
                                <input type="hidden" style="width:75px;border: 0px;text-align: center" tag="initialInventory"/>
                                <input type="hidden" style="width:75px;border: 0px;text-align: center" tag="integral"/>
                            </td>
                            <td>

                                 <input id="productName" readonly="readonly" type="text"
                                       style="width:75px;border: 0px;text-align: center"
                                       tag="name">
                            </td>
                            <td>
                                <input id="salePrice" readonly="readonly" type="text"
                                       style="width:75px;border: 0px;text-align: center"
                                       tag="unitpPrice">
                            </td>
                            <td>
                                <input id="number" type="text" style="width:75px;border: 0px;text-align: center" tag="number">
                            </td>
                            <td>
                                <input id="amount" type="text" readonly="readonly" style="width:75px;border: 0px;text-align: center"
                                       tag="amount"
                                       class="input">
                            </td>
                            <td>
                                <a id="remove_item" class="btn_deleteItem"></a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="p" class="p" style="width:100px;height:150px;" align="canter">
                <p style="color: #dc0000 "><span style="font-size: 18px;">&emsp;应付款: ¥</span></p>
                <p style="font-size:30px ;" align="center"><span style="color: #dc0000"><i><span id="totalNum">0</span>元</i></span></p>
                <p><span class="hfont"></span></p>
            </div>
            <div class="easyui-layout">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-guadan',width:140"
                   id="waitPayment">挂单</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-qudan',width:140"
                   id="selectOrders">取单</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-fukuan',width:140" id="payment">付款</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-fukuan',width:140" id="selectmember">选择会员</a>
            </div>
        </form>
    </div>

    <div data-options="region:'center',title:'商品栏',split:true" style="width:600px;">
        <table id="product" class="easyui-datagrid" data-options="fit:true,singleSelect:true,fitColumns:true,
            url:'/product/list.do',pagination:true,remoteSort:false,sortOrder:'desc',toolbar:'#tt,#ts'">
            <thead>
            <tr>
                <th data-options="field:'id',width:'50',sortable:true">id</th>
                <th data-options="field:'name',width:'50',sortable:true">商品名称</th>
                <th data-options="field:'goodsMark',width:'50',sortable:true">商品编号</th>
                <th data-options="field:'unitpPrice',width:'50',sortable:true">商品售价</th>
                <th data-options="field:'brand',width:'50',sortable:true">商品品牌</th>
                <th data-options="field:'purchasingPrice',width:'50',sortable:true,hidden:true" hidden="true">商品品牌</th>
                <th data-options="field:'minPrice',width:'50',sortable:true,hidden:true" hidden="true">商品品牌</th>
                <th data-options="field:'initialInventory',width:'50',sortable:true,hidden:true" hidden="true">商品品牌</th>
                <th data-options="field:'integral',width:'50',sortable:true,hidden:true" hidden="true">商品品牌</th>
                <th data-options="field:'minDiscount',width:'50',sortable:true" style="color: red">会员折扣</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<div id="dd" class="easyui-dialog" style="width: 780px;height: 400px" data-options="title:'会员表',closed:true">
    <table id="member" class="easyui-datagrid" data-options="fit:true,singleSelect:true,fitColumns:true,
            url:'/member/selectAll.do',pagination:true,remoteSort:false,sortOrder:'desc'">
        <thead>
        <tr>
            <th data-options="field:'id',width:'50',sortable:true">id</th>
            <th data-options="field:'memberNum',width:'50',sortable:true">会员卡号</th>
            <th data-options="field:'name',width:'50',sortable:true">会员名字</th>
            <th data-options="field:'phone',width:'50',sortable:true">电话</th>
            <th data-options="field:'points',width:'50',sortable:true">会员积分</th>
            <th data-options="field:'grade',width:'50',sortable:true,formatter:state" style="color: red">会员等级</th>
            <th data-options="field:'balance',width:'50',sortable:true">会员余额</th>
        </tr>
        </thead>
    </table>
</div>

<div id="dd1" class="easyui-dialog" style="width: 500px;height: 350px" data-options="title:'挂单客户',closed:true">
    <table id="waitPaymentMember" class="easyui-datagrid" data-options="fit:true,singleSelect:true,fitColumns:true,
            url:'/checkoutComeBill/selectwaitPayment.do',pagination:true,remoteSort:false,sortOrder:'desc'">
        <thead>
        <tr>
            <th data-options="field:'member',width:'50',sortable:true,formatter:memberId">id</th>
            <th data-options="field:'memberNumber',width:'50',sortable:true,formatter:memberNumber">会员卡号</th>
            <th data-options="field:'memberName',width:'50',sortable:true,formatter:memberName">会员名字</th>
            <th data-options="field:'memberBalance',width:'50',sortable:true,formatter:memberBalance">会员余额</th>
            <th data-options="field:'memberPoints',width:'50',sortable:true,formatter:memberPoints">会员积分</th>
        </tr>
        </thead>
    </table>
</div>
<div id="tt">
    <input id="keyword" class="easyui-textbox" name="beginDate" data-options="label:''
            ,width:250,labelWidth:50,prompt:'商品名称'"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-z',plain:true" onclick="searchs();"></a>
</div>
<div id="ts">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-s',plain:true" onclick="fruits();">水果</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-l',plain:true" onclick="edibleoil();">粮油</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-a',plain:true" onclick="drinks();">酒水</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-h',plain:true" onclick="food();">食品</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-c',plain:true" onclick="vegetables();">蔬菜</a>
</div>
</body>
</html>

