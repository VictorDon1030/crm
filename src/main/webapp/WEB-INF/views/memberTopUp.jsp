<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员充值</title>
    <%@ include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/memberTopUp.js"></script>
    <%--<link rel="stylesheet" type="text/css" href="/static/css/public.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="/static/css/reset.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="/static/css/style.css">--%>
</head>
<body>


<div id="cc" class="easyui-layout" data-options="fit:true">
    <%--上--%>
    <div data-options="region:'north',split:true,border:false" style="height:120px;">
        <form id="member_loadData_form">
            <div>
                 <span>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>

            </span>
                <input id="name_textbox" class="easyui-textbox" name="name"
                       data-options="width:280,label:'会员名称:',readonly:true"/>
                <div>
                    &nbsp;
                </div>
                <div>
            <span>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>

            </span>
                    <span>
              <input id="grade_textbox" class="easyui-textbox" name="grade.id"
                     data-options="width:280,label:'会员等级:',readonly:true"/>
            </span>

                    <span>
               <input id="birthday_textbox" class="easyui-textbox" name="birthday"
                      data-options="width:280,label:'会员生日:',readonly:true"/>
            </span>
                    <span>
               <input id="balance_textbox" class="easyui-textbox" name="balance"
                      data-options="width:280,label:'会员余额:',readonly:true"/>
            </span>
                </div>
                <div>
                    &nbsp;
                </div>
                <div>
            <span>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>

            </span>
                    <span>
              <input class="easyui-textbox" name="arrearage" data-options="width:280,label:'会员欠费:',readonly:true"/>
            </span>

                    <span>
               <input class="easyui-textbox" name="expenseAll" data-options="width:280,label:'累计消费:',readonly:true"/>
            </span>
                    <span>
               <input id="points_textbox" class="easyui-textbox" name="points"
                      data-options="width:280,label:'会员积分:',readonly:true"/>
            </span>
                </div>

            </div>

        </form>
    </div>
    <%--左--%>
    <div data-options="region:'west',split:true,border:false" style="width:400px;">
        <%--<table id="member_load_datagrid"></table>--%>
        <%--表格--%>
        <table id="member_load_datagrid" class="table table-celled table-structured">
            <%-- <tbody>
             <tr>
                 <td rowspan="3"><img src="#"></td>
                 <td data-options="field:'name'"></td>
                 <td>&nbsp;</td>
                 <td data-options="field:'grade.id'"></td>

             </tr>
             <tr>
             </tr>
             <tr>
                 <td data-options="field:'balance'"></td>
                 <td>&nbsp;</td>
                 <td data-options="field:'phone"></td>
             </tr>
             </tbody>--%>
        </table>
    </div>
    <%--中间,充值--%>
    <div data-options="region:'center',border:false" style="padding:5px;background:#eee;">
        <form id="ff" method="post">
            <p> &nbsp; </p>
            <input type="hidden" name="id" id="id_hidden">
            <div>
                <span>操作类型:</span>
                <a class="easyui-linkbutton" data-options="width:80,height:46,text:'充值'" data-btn="addTotal"></a>
                <a class="easyui-linkbutton" data-options="width:80,height:46,text:'充次'" data-btn="addCount"></a>
            </div>
            <span>
            <p> &nbsp; </p>
            </span>
            <div>
                <span>选择类型:</span>
                <input id="select_topup" class="checkinput" data-recharge_type="0" checked="checked" name="addway"
                       value="" type="radio">
                <span>充值</span>
                <span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
                <input id="select_fee" class="checkinput" data-recharge_type="0" checked="checked" name="addway"
                       value="" type="radio">
                <span>扣费</span>
                <span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
                <input id="select_refund" class="checkinput" data-recharge_type="0" checked="checked" name="addway"
                       value="" type="radio">
                <span>退还</span>
            </div>
            <span>
            <p> &nbsp; </p>
            </span>
            <span>
                <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>
            </span>
            <span>
                     <input class="easyui-textbox" name="addbalance" data-options="width:280,label:'充值金额:'"/>
                          </span>
            <span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            <span>
                     <input class="easyui-textbox" name="give" data-options="width:280,label:'赠送金额:'"/>
                          </span>
            <span>
            <p> &nbsp; </p>
            </span>
            <div>
                <span>支付方式:</span>
                <a class="easyui-linkbutton" data-options="width:80,height:46,text:'现金'" data-btn="cash"></a>
                <a class="easyui-linkbutton" data-options="width:80,height:46,text:'银行卡'" data-btn="bankcard"></a>
                <a class="easyui-linkbutton" data-options="width:80,height:46,text:'支付宝'" data-btn="alipay"></a>
                <a class="easyui-linkbutton" data-options="width:80,height:46,text:'微信'" data-btn="wechat"></a>
                <span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
                <span><input class="easyui-textbox" name="username" data-options="width:280,label:'充值员工:'"/></span>
                <%--<input class="easyui-textbox" type="text" name="email" data-options="validType:'email'"/>--%>
            </div>
              <p> &nbsp; </p>
            <div>
                <span>优惠方式:</span>
                <span>
                    (活动充值满5000元赠送500元储值现金)
                </span>
            </div>
              <p> &nbsp; </p>
            <div>
                <span>备注信息:</span>
                <input class="easyui-textbox" type="text" name="email"
                       data-options="validType:'email',multiline:true,height:80,width:300"/>
            </div>
            <span>
            <p> &nbsp; </p>
            <p> &nbsp; </p>
            </span>
            <p> &nbsp; </p>
            <span>  <input id="select_print" class="checkinput" data-recharge_type="0" checked="checked"
                           name="select_print"
                           value="" type="radio">
            </span>
            <span>打印充值小票</span>
            <span>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            合计充值:<span></span>
            <span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
            <a class="easyui-linkbutton" id="btn" data-options="width:80,height:46,text:'充值'" data-btn="addData"></a>
        </form>
    </div>
</div>


</body>
</html>
