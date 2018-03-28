<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/plugins/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/easyui/themes/color.css">
    <link href="/static/plugins/insdep/reset.min.css" rel="stylesheet" type="text/css">
    <link href="/static/plugins/insdep/easyui_full.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/static/plugins/insdep/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugins/insdep/insdep-extend.min.js"></script>
    <script type="text/javascript" src="/static/plugins/insdep/plus/panel/user_info_standard_panel/style.css"></script>
    <script type="text/javascript" src="/static/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/bonusPoint.js"></script>
    <title>会员积分管理</title>
</head>
<body>
<div class="easyui-tabs tabs-underline tab-color-blue" data-options="plain:true,fit:true">
    <div title="积分变动" style="padding:10px">
        <table style="width: 100%">
            <tr>
                <td style="margin-top: 10px;margin-left: 5px">
                    <div class="user_info_standard_panel user_info_standard_classic">
                        <div style="margin-left: 140px" class="left">
                            <div class="panel_user_portrait">
                                <img src="/static/plugins/insdep/plus/panel/user_info_standard_panel/images/portrait_100x100.png">
                            </div>
                        </div>
                        <div style="margin-left: 30px" class="right">
                            <div class="panel_user_information">
                                <h3 id="memberName"  style="margin-left: 110px">会员姓名</h3>
                                <ul>
                                    <li><span style="font-size: medium">会员卡号：</span><span
                                            style="font-size: medium" id="memberNum"></span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium" >会员等级：</span><span
                                                style="font-size: medium" id="grade"></span></li>
                                    &emsp;
                                    <li><span style="font-size: medium;" >当前积分：</span><span
                                            style="font-size: medium;" id="points"></span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium" >会员余额：</span><span
                                                style="font-size: medium" class="font-red" id="balance"></span></li>
                                    &emsp;
                                    <li><span style="font-size: medium">累计积分：</span><span
                                            style="font-size: medium" id="sumPoints" >0</span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">会员欠款：</span><span
                                                style="font-size: medium" class="font-red">0</span></li>
                                    &emsp;
                                    <li><span style="font-size: medium">已用积分：</span><span
                                            style="font-size: medium" id="comsumPoints">0</span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">累计消费：</span><span
                                                style="font-size: medium" class="font-red">0</span></li>
                                    &emsp;
                                    <li><span style="font-size: medium">会员生日：</span><span
                                            style="font-size: medium" id="birthday"></span>
                                </ul>

                            </div>

                        </div>
                    </div>
                </td>
                <td>&emsp; </td>
                <td style="margin-top: 10px">
                    <table id="memberSimpleInfo"
                         style="width:90%;height:400px;padding:10px;"
                         data-options="singleSelect:true,border:false,striped:true,fitColumns:true,toolbar:'#tt'">
                    </table>
                    <div id="tt">
                        <input id="keyword" class="easyui-textbox" data-options="prompt:'请输入会员卡号或电话进行查询',height:38"
                               style="width:85%"/>
                        <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" data-cmd="advancedSearch"
                           style="width:80px">搜索</a>
                    </div>

                </td>
                <td style="margin-top: 10px">
                    <div class="user_info_standard_panel user_info_standard_classic">
                        <div style="margin-left: 30px" class="right">
                            <div class="panel_user_information">
                                <ul>
                                <form  method="post">
                                    <input id="hiddenMemberId" type="hidden" name="member.id" />
                                    <li><span style="font-size: medium">选择类型：</span>
                                        &emsp; <input type="radio" name="type" value="1" style="font-size: medium;"checked >充值</input>
                                        &emsp; &emsp;&emsp;<input type="radio" value="0" name="type" style="font-size: medium">扣除</input>
                                    </li>
                                    &emsp;
                                    <li><span style="font-size: medium;">变动金额：</span>
                                        <input  class="easyui-numberbox" name="amount" style="font-size: medium;"
                                               data-options="prompt:'请输入要改变的积分金额'"/>
                                    </li>
                                    &emsp;
                                    <li><span style="font-size: medium;">备&emsp;&emsp;注：</span>
                                        <select id="remark" class="easyui-combobox" name="remark" style="font-size: medium;width: 180px"
                                               data-options="editable:false,panelHeight:'auto'">
                                            <option value="订单消费">订单消费</option>
                                            <option value="订单退款">订单退款</option>
                                        </select>
                                    </li>
                                </form>
                                    &emsp;
                                    <li>
                                        <a href="#" class="easyui-linkbutton" data-cmd="clearPoints"
                                           style="width:80px;background-color: red">积分清零</a>
                                        &emsp; &emsp; &emsp; &emsp;
                                        <a  class="easyui-linkbutton"  data-cmd="changePoint"
                                           style="width:80px;background-color: green">确定</a>
                                    </li>
                                </ul>
                            </div>

                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>&emsp;</td>

            </tr>
            <tr>
                <td colspan="6">
                    <table id="memberInfo"   style="width: 100%"
                           data-options="singleSelect:true,border:false,fitColumns:true,pagination:true,striped:true">
                    </table>

                </td>
            </tr>
        </table>
    </div>
    <div title="礼品列表" data-options="" style="padding:10px">
        <table class="easyui-datagrid" title="Basic DataGrid" style="width: 100%"
               data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'post',fitColumns:true,toolbar:'#gift_toolbar'">
            <thead>
            <tr>
                <th data-options="field:'itemid',width:80">Item ID</th>
                <th data-options="field:'productid',width:100">Product</th>
                <th data-options="field:'listprice',width:80,align:'right'">List Price</th>
                <th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
                <th data-options="field:'attr1',width:250">Attribute</th>
                <th data-options="field:'status',width:60,align:'center'">Status</th>
            </tr>
            </thead>
        </table>
        <div id="gift_toolbar">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
            <a id="btn_changeState" class="easyui-linkbutton" data-cmd="deleteRow"
            data-options="plain:true,iconCls:'icon-remove'">删除</a>
            <input class="easyui-textbox" id="" prompt="请输入用户名或电话"/>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs"></a>
        </div>
    </div>
    <div title="兑换礼品" data-options="" style="padding:10px">
        <table style="width: 100%">
            <tr>
                <td style="margin-top: 10px;margin-left: 5px">
                    <div class="user_info_standard_panel user_info_standard_classic">
                        <div style="margin-left: 140px" class="left">
                            <div class="panel_user_portrait">
                                <img src="/static/plugins/insdep/plus/panel/user_info_standard_panel/images/portrait_100x100.png">
                            </div>
                        </div>
                        <div style="margin-left: 30px" class="right">
                            <div class="panel_user_information">
                                <h3 style="margin-left: 80px">会员姓名 <span class="badge color-green">已认证</span></h3>
                                &emsp;
                                <ul>
                                    <li><span style="font-size: medium">会员卡号：</span><span
                                            style="font-size: medium">examples</span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">会员等级：</span><span
                                                style="font-size: medium">examples</span></li>
                                    &emsp;
                                    <li><span style="font-size: medium;">当前积分：</span><span
                                            style="font-size: medium;">examples</span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">会员余额：</span><span
                                                style="font-size: medium" class="font-red">examples</span></li>
                                    &emsp;
                                    <li><span style="font-size: medium">累计积分：</span><span
                                            style="font-size: medium">examples</span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">会员欠款：</span><span
                                                style="font-size: medium" class="font-red">examples</span></li>
                                    &emsp;
                                    <li><span style="font-size: medium">已用积分：</span><span
                                            style="font-size: medium">examples</span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">累计消费：</span><span
                                                style="font-size: medium" class="font-red">examples</span></li>
                                    &emsp;
                                    <li><span style="font-size: medium">会员生日：</span><span
                                            style="font-size: medium">examples</span>
                                </ul>
                            </div>

                        </div>
                    </div>
                </td>
                <td>&emsp; &emsp;&emsp;&emsp;</td>
                <td style="margin-top: 10px">
                    <div id="" class="easyui-datagrid"
                         style="width:450px;height:400px;padding:10px;"
                         data-options="border:true,toolbar:'#tt2'">

                    </div>
                    <div id="tt2">
                        <input class="easyui-textbox" data-options="prompt:'Please Input Value',height:38"
                               style="width:80%"/>
                        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
                           style="width:80px">搜索</a>
                    </div>

                </td>
                <td style="margin-left: 10px">
                    <table class="easyui-datagrid" title="Basic DataGrid" style="width: 100%"
                           data-options="singleSelect:true,url:'datagrid_data1.json',method:'post',fitColumns:true,toolbar:'#giftlist_toolbar'">
                        <thead>
                        <tr>
                            <th data-options="field:'itemid',width:80">Item ID</th>
                            <th data-options="field:'productid',width:100">Product</th>
                            <th data-options="field:'listprice',width:80,align:'right'">List Price</th>
                            <th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
                            <th data-options="field:'attr1',width:250">Attribute</th>
                            <th data-options="field:'status',width:60,align:'center'">Status</th>
                        </tr>
                        </thead>
                    </table>
                    <div id="giftlist_toolbar">
                        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
                        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
                        <a id="" class="easyui-linkbutton" data-cmd="deleteRow"
                           data-options="plain:true,iconCls:'icon-remove'">删除</a>
                        <input class="easyui-textbox" id="" prompt="请输入用户名或电话"/>
                        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs"></a>
                    </div>
                </td>
            </tr>
            <tr>
                <td>&emsp;</td>

            </tr>
            <tr>
                <td colspan="6">
                    <table class="easyui-datagrid" title="Basic DataGrid" style="width: 100%"
                           data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'post',fitColumns:true">
                        <thead>
                        <tr>
                            <th data-options="field:'itemid',width:80">Item ID</th>
                            <th data-options="field:'productid',width:100">Product</th>
                            <th data-options="field:'listprice',width:80,align:'right'">List Price</th>
                            <th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
                            <th data-options="field:'attr1',width:250">Attribute</th>
                            <th data-options="field:'status',width:60,align:'center'">Status</th>
                        </tr>
                        </thead>
                    </table>

                </td>
            </tr>
        </table>
    </div>
    <div title="兑换记录" data-options="" style="padding:10px">
        <table class="easyui-datagrid" title="Basic DataGrid" style="width: 100%"
               data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'post',fitColumns:true,toolbar:'#vip_toolbar'">
            <thead>
            <tr>
                <th data-options="field:'itemid',width:80">Item ID</th>
                <th data-options="field:'productid',width:100">Product</th>
                <th data-options="field:'listprice',width:80,align:'right'">List Price</th>
                <th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
                <th data-options="field:'attr1',width:250">Attribute</th>
                <th data-options="field:'status',width:60,align:'center'">Status</th>
            </tr>
            </thead>
        </table>
        <div id="vip_toolbar">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
            <a id="" class="easyui-linkbutton" data-cmd="deleteRow"
               data-options="plain:true,iconCls:'icon-remove'">删除</a>
            <input class="easyui-textbox" id="" prompt="请输入用户名或电话"/>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs"></a>
        </div>
    </div>
</div>
</body>
</html>
