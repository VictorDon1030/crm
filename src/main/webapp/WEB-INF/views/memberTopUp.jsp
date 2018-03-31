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
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>

            </span>
                <input id="name_textbox" class="easyui-textbox" name="name"
                       data-options="width:280,label:'会员名称:',readonly:true"/>
                <div>
                    &nbsp;
                </div>
                <div>
            <span>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>

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
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%--<input class="easyui-validatebox" type="text" name="name" data-options="required:true,label:操作类型" />--%>

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
        <%--表格--%>
        <table id="member_load_datagrid" class="table table-celled table-structured">
        </table>
    </div>
    <%--中间,充值--%>
    <div data-options="region:'center',border:false" style="padding:5px;background:#eee;">
        <div>
            <span>操作类型:</span>
            <a class="easyui-linkbutton" data-options="width:60,height:36,text:'充值'" data-state="1"></a>
            <span>
            &nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            <a class="easyui-linkbutton" data-options="width:60,height:36,text:'充次'" data-state="0"></a>
        </div>

        <%--充值的表单--%>
        <span id="totalResult" style="display:block;">
        <form id="ff" method="post">
            <input type="hidden" name="member.id" id="id_hidden">
            <span>
            <p> &nbsp; </p>
            </span>
            <div id="chooseway" style="display:block;  ">
                <span>选择类型:</span>
                <input id="select_topup" class="checkinput" data-recharge_type="0" checked="checked" name="addway"
                       value="1" type="radio">
                <span>充值</span>
                <span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
                <input id="select_fee" class="checkinput" data-recharge_type="0" name="addway"
                       value="-1" type="radio">
                <span>扣费</span>
                <span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
                <input id="select_refund" class="checkinput" data-recharge_type="0" name="addway"
                       value="0" type="radio">
                <span>退还</span>
            </div>
              <span>
            <p> &nbsp; </p>
            </span>
            <span>
                     <input id="addbalance_textbox" class="easyui-textbox btn_add" name="addbalance"
                            data-options="width:280,label:'充值金额:',prompt: '请输入充值金额'" data-cmd="add"/>
                          </span>
            <span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            <%--充值--%>
            <span id="addWay" style="display:block;">


            <span>
                     <input class="easyui-textbox" name="give" data-options="width:280,label:'赠送金额:',prompt: '请输入赠送金额'"
                            data-cmd="give"/>
                          </span>
            <span>
            <p> &nbsp; </p>
            </span>
            <div>
                <span>支付方式:</span>
                <a class="easyui-linkbutton" data-options="width:60,height:36,text:'现金'" data-btn="cash"></a>
                <a class="easyui-linkbutton" data-options="width:60,height:36,text:'银行卡'" data-btn="bankcard"></a>
                <a class="easyui-linkbutton" data-options="width:60,height:36,text:'支付宝'" data-btn="alipay"></a>
                <a class="easyui-linkbutton" data-options="width:60,height:36,text:'微信'" data-btn="wechat"></a>
                <span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
                <span>操作员工:</span>
                <span><input class="easyui-validatebox" data-options="width:280,readonly:true"
                             value="<shiro:principal property="username"/>"/></span>
                </div>
                  </span>
                <p id="p">
                   &nbsp;
                </p>

                <div id="discounts">
                <span>优惠方式:</span>
                <span id="msg">
                    (活动充值满5000元赠送500元储值现金)
                </span>
                </div>
              &nbsp;
            <div>
                <span>备注信息:</span>
                <input class="easyui-textbox" type="text" name="intro"
                       data-options="multiline:true,height:80,width:300"/>
            </div>
            <p> &nbsp; </p>
            <span>  <input id="select_print" data-recharge_type="0"
                           name="select_print" data-print="print"
                           value="" type="checkbox">
            </span>
            <span>打印充值小票</span>
            <span>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>


        <%--扣费--%>
      <%--  <span id="feeWay" style="display:none ">
            </span>--%>

        <%--退还--%>
        <%--<span id="retreatWay" style="display:none;  "> </span>--%>


        合计充值:<span id="totalMoney" style="color: red"></span>
        <span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
        <a class="easyui-linkbutton" id="btn" data-options="width:80,height:46,text:'确认'" data-btn="addData"></a>
              <table class="topup_datagrid"></table>
        </form>
        </span>
        <%--充次的表单--%>
        <span id="countResult" >
        <form id="count_from" method="post">
            <input type="hidden" name="member.id" id="id_hiddenCount">
            <span>
            <p> &nbsp; </p>
            </span>
            <span>
                     <input id="productId" class="easyui-combobox" name="product.id" data-options="width:280,label:'选择商品:',valueField:'id',textField:'name',
                     url:'/product/countId.do'"/>
                          </span>
            <span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>

            <span>
                <input class="easyui-textbox" name="addbalance" data-options="width:280,label:'充值金额:'" data-cmd="addBalance"/>
            </span>
            <span>
            <p> &nbsp; </p>
            </span>
            <div>
                <span>支付方式:</span>
                <a class="easyui-linkbutton" data-options="width:60,height:36,text:'现金'" data-btn="cash"></a>
                <a class="easyui-linkbutton" data-options="width:60,height:36,text:'银行卡'" data-btn="bankcard"></a>
                <a class="easyui-linkbutton" data-options="width:60,height:36,text:'支付宝'" data-btn="alipay"></a>
                <a class="easyui-linkbutton" data-options="width:60,height:36,text:'微信'" data-btn="wechat"></a>
                <span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
                <span><input class="easyui-textbox" name="addcount" data-options="width:280,label:'充值次数:'"
                             data-cmd="addCount"/></span>
            </div>
              &nbsp;
            <div>

            </div>
             <span><input class="easyui-datebox" name="usefullife" data-options="width:280,label:'有效期:'"/></span>
                <span><input class="easyui-textbox" data-options="width:280,label:'充值员工:',readonly:true"
                             value="<shiro:principal property="username"/>"/></span>
            <div>
                 <p> &nbsp; </p>
                <span>优惠方式:</span>
                <span>
                    (活动冲次满10次送1次)
                </span>
            </div>
                &nbsp;
            <div>
                <span>备注信息:</span>
                <input class="easyui-textbox" type="text" name="intro"
                       data-options="multiline:true,height:80,width:300"/>
            </div>
            <span>  <input id="select_printCount" data-recharge_type="0"
                           name="select_print" data-printCount="print"
                           value="" type="checkbox">
            </span>
            <span>打印充值小票</span>
            <span>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            合计充值:<span id="totalCount" style="color: red"></span>
            <span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
            <a class="easyui-linkbutton" id="btnCount" data-options="width:80,height:46,text:'充值'"
               data-btnCount="addData"></a>
            <table id="count_datagrid" ></table>
        </form>
        </span>
        <%----%>
    </div>
</div>

<script type="text/javascript">

</script>
</body>
</html>
