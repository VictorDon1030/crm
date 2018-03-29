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
                                <h3 id="memberName" style="margin-left: 110px">会员姓名</h3>
                                <ul>
                                    <li><span style="font-size: medium">会员卡号：</span><span
                                            style="font-size: medium" id="memberNum"></span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">会员等级：</span><span
                                                style="font-size: medium" id="grade"></span></li>
                                    &emsp;
                                    <li><span style="font-size: medium;">当前积分：</span><span
                                            style="font-size: medium;" id="points"></span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">会员余额：</span><span
                                                style="font-size: medium" class="font-red" id="balance"></span></li>
                                    &emsp;
                                    <li><span style="font-size: medium">累计积分：</span><span
                                            style="font-size: medium" id="sumPoints">0</span>
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
                <td>&emsp;</td>
                <td style="margin-top: 10px">
                    <table id="memberSimpleInfo"
                           style="width:90%;height:400px;padding:10px;"
                           data-options="singleSelect:true,border:false,striped:true,fitColumns:true,toolbar:'#tt'">
                    </table>
                    <div id="tt">
                        <input id="keyword" class="easyui-textbox" data-options="prompt:'请输入会员卡号或电话进行查询',height:38"
                               style="width:85%"/>
                        <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" data-cmd="advancedSearch"
                           style="width:80px">搜索</a>
                    </div>

                </td>
                <td style="margin-top: 10px">
                    <div class="user_info_standard_panel user_info_standard_classic">
                        <div style="margin-left: 30px" class="right">
                            <div class="panel_user_information">
                                <ul>
                                    <form method="post">
                                        <input id="hiddenMemberId" type="hidden" name="member.id"/>
                                        <li><span style="font-size: medium">选择类型：</span>
                                            &emsp; <input type="radio" name="type" value="1" style="font-size: medium;"
                                                          checked>充值</input>
                                            &emsp; &emsp;&emsp;<input type="radio" value="0" name="type"
                                                                      style="font-size: medium">扣除</input>
                                        </li>
                                        &emsp;
                                        <li><span style="font-size: medium;">变动金额：</span>
                                            <input class="easyui-numberbox" name="amount" style="font-size: medium;"
                                                   data-options="prompt:'请输入要改变的积分金额'"/>
                                        </li>
                                        &emsp;
                                        <li><span style="font-size: medium;">备&emsp;&emsp;注：</span>
                                            <select id="remark" class="easyui-combobox" name="remark"
                                                    style="font-size: medium;width: 180px"
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
                                        <a class="easyui-linkbutton" data-cmd="changePoint"
                                           style="width:80px;background-color: green">确定</a>
                                    </li>
                                </ul>
                            </div>

                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <table id="memberInfo" style="width: 100%"
                           data-options="singleSelect:true,border:false,fitColumns:true,pagination:true,striped:true">
                    </table>

                </td>
            </tr>
        </table>
    </div>
    <div title="礼品列表" data-options="" style="padding:10px">
        <table id="giftList" class="easyui-datagrid" style="width: 100%"
               data-options="singleSelect:true,pagination:true,url:'/gift/list.do',method:'post',fitColumns:true,toolbar:'#gift_toolbar'">
            <thead>
            <tr>
                <th data-options="field:'name',width:80, align: 'center'">礼品名称</th>
                <th data-options="field:'sn',width:100, align: 'center'">礼品编码</th>
                <th data-options="field:'points',width:80, align: 'center'">所需积分</th>
                <th data-options="field:'totalQuantity',width:80, align: 'center'">全部数量</th>
                <th data-options="field:'inventory',width:250, align: 'center'">剩余数量</th>
                <th data-options="field:'unit',width:250, align: 'center'">礼品单位</th>
            </tr>
            </thead>
        </table>
        <div id="gift_toolbar">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="addData">新增</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="editData">编辑</a>
            <a id="btn_removeData" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-remove'" data-cmd="removeData">删除</a>
            <input class="easyui-textbox" id="giftKeyword" prompt="请输入礼品名称或编码" data-options="width:280"/>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchGifts"></a>
        </div>
        <div id="giftEdit">
            <form id="gift_edit_form" method="post">
                <input type="hidden" name="id"/>
                <div style="margin-left: 30px;margin-top: 20px">
                    <input name="name" class="easyui-textbox" data-options="width:250,label:'礼品名称:',labelWidth:60"/>
                </div>
                <div style="margin-left: 30px;margin-top: 20px">
                    <input name="sn" class="easyui-textbox" data-options="width:250,label:'礼品编码:',labelWidth:60"/>
                </div>
                <div style="margin-left: 30px;margin-top: 20px">
                    <input name="points" class="easyui-textbox" data-options="width:250,label:'所需积分:',labelWidth:60"/>
                </div>
                <div style="margin-left: 30px;margin-top: 20px">
                    <input name="inventory" class="easyui-textbox"
                           data-options="width:250,label:'礼品数量:',labelWidth:60"/>
                </div>
                <div style="margin-left: 30px;margin-top: 20px">
                    <select name="unit" class="easyui-combobox" data-options="width:250,label:'礼品单位:',labelWidth:60">
                        <option value="个">个</option>
                        <option value="盒">盒</option>
                        <option value="块">块</option>
                        <option value="箱">箱</option>
                        <option value="千克">千克</option>
                        <option value="条">条</option>
                        <option value="只">只</option>
                    </select>
                </div>
            </form>
        </div>
        <div id="gift_edit_buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" data-cmd="submitData">提交</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" data-cmd="cancelDialog">取消</a>
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
                                <h3 id="memberName_gift" style="margin-left: 110px">会员姓名</h3>
                                <ul>
                                    <li><span style="font-size: medium">会员卡号：</span><span
                                            style="font-size: medium" id="memberNum_gift"></span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">会员等级：</span><span
                                                style="font-size: medium" id="grade_gift"></span></li>
                                    &emsp;
                                    <li><span style="font-size: medium;">当前积分：</span><span
                                            style="font-size: medium;" id="points_gift"></span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">会员余额：</span><span
                                                style="font-size: medium" class="font-red" id="balance_gift"></span>
                                    </li>
                                    &emsp;
                                    <li><span style="font-size: medium">累计积分：</span><span
                                            style="font-size: medium" id="sumPoints_gift">0</span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">会员欠款：</span><span
                                                style="font-size: medium" class="font-red">0</span></li>
                                    &emsp;
                                    <li><span style="font-size: medium">已用积分：</span><span
                                            style="font-size: medium" id="comsumPoints_gift">0</span>
                                        &emsp; &emsp;&emsp;&emsp; <span style="font-size: medium">累计消费：</span><span
                                                style="font-size: medium" class="font-red">0</span></li>
                                    &emsp;
                                    <li><span style="font-size: medium">会员生日：</span><span
                                            style="font-size: medium" id="birthday_gift"></span>
                                </ul>

                            </div>

                        </div>
                    </div>
                </td>
                <td style="margin-top: 10px;width:65%">
                    <table id="memberSimpleInfo_gift"
                           style="width:75%;height:400px;padding:10px;"
                           data-options="singleSelect:true,border:false,striped:true,fitColumns:true,toolbar:'#gift_tt'">
                    </table>
                    <div id="gift_tt">
                        <input id="keyword_gift" class="easyui-textbox" data-options="prompt:'请输入会员卡号或电话进行查询',height:38"
                               style="width:80%"/>
                        <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" data-cmd="advancedSearch_gift"
                           style="width:80px">搜索</a>
                    </div>
                </td>
                <td valign="top">
                    <form method="post">
                        <input id="hiddenMemberId_gift" type="hidden" name="member.id"/>
                        <table border="0" cellspacing="0" cellpadding="0">
                            <thead>
                            <tr>
                                <th>&emsp;礼品名称&emsp;</th>
                                <th>&emsp;兑换积分&emsp;</th>
                                <th>&emsp;剩余数据&emsp;</th>
                                <th>&emsp;数&emsp;&emsp;量&emsp;</th>
                                <th>&emsp;操&emsp;&emsp;作&emsp;</th>
                            </tr>
                            <tr id="detail" align="center">
                                <td>&emsp;<span id="giftName"></span>&emsp;</td>
                                <td>&emsp;<span id="needed_point"></span>&emsp;</td>
                                <td>&emsp;<span id="quantity_remain"></span>&emsp;</td>
                                <td>&emsp;<span id="quantity"><input id="ss" align="center" type="number"
                                                                     style="width:60px;border: 0px" min="1"
                                                                     data-options="editable:false">
                                    </span>&emsp;</td>
                                <td>&emsp;<span><a id="operate"
                                                   data-cmd="deleteChoose"></a></span>&emsp;</td>
                            </tr>
                            </thead>
                            <tbody id="gift_4_exchange">

                            </tbody>
                        </table>
                        <div id="exchange_btn" style="margin:120px;margin-left: 20px">
                            <a class="easyui-linkbutton " style="width:80px;height:40px;background-color: green"
                               data-cmd="chooseGift">选择礼品</a>
                            &emsp;&emsp;
                            <a id="conform_exchange"  class="easyui-linkbutton " style="width:80px;height:40px; "
                               disabled="true"
                               data-cmd="conformExchange">确定兑换</a>
                            &emsp;&emsp;<span id="neededPoints_text"></span>&emsp;<span style="color: red" id="neededPoints_num"></span>
                        </div>
                        <div id="gift_dialog" >
                            <table id="giftList4choose" class="easyui-datagrid" style="width: 100%"
                                   data-options="singleSelect:true,pagination:false,url:'/gift/list.do',method:'post',fitColumns:true">
                                <thead>
                                <tr>
                                    <th data-options="field:'name',width:80, align: 'center'">礼品名称</th>
                                    <th data-options="field:'sn',width:100, align: 'center'">礼品编码</th>
                                    <th data-options="field:'points',width:80, align: 'center'">所需积分</th>
                                    <th data-options="field:'inventory',width:250, align: 'center'">剩余数量</th>
                                    <th data-options="field:'unit',width:250, align: 'center'">礼品单位</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                            <div id="choose_buttons">
                                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" data-cmd="submitChoose">确定</a>
                                <a class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" data-cmd="cancelChoose">取消</a>
                            </div>
                    </form>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <table id="memberInfo_gift" style="width: 100%"
                           data-options="singleSelect:true,border:false,fitColumns:true,pagination:true,striped:true">
                    </table>

                </td>
            </tr>
        </table>
    </div>
    <div title="兑换记录" data-options="" style="padding:10px">
        <table id="exchange_record" class="easyui-datagrid"  style="width: 100%"
               data-options="singleSelect:true,collapsible:false,fitColumns:true,toolbar:'#toolbar_record'">
        </table>
        <div id="toolbar_record">
            <input id="exchange_keyword" class="easyui-textbox" prompt="请输入会员卡号或名称" data-options="width:250,height:30"/>
            <input id="beginDate" class="easyui-datebox" prompt="请选择交易时间" data-options="width:250,height:30,editable:false"/>至
            <input id="endDate" class="easyui-datebox" prompt="请选择交易时间" data-options="width:250,height:30,editable:false"/>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true,height:30" data-cmd="searchExchangeRecord"></a>
        </div>
    </div>
</div>
</body>
</html>
