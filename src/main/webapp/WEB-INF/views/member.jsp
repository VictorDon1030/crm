<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp" %>
    <script type="text/css">
        .diamonds {
            width: 150px;
            height: 150px;
            float: left;
            margin-right: 20px;
        }
    </script>
    <script type="text/javascript" src="/static/js/member.js"></script>
    <title>会员管理</title>
</head>
<body>
<table id="member_datagrid"></table>
<div id="member_toolbar">
    <shiro:hasRole name="大队长"> </shiro:hasRole>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
    <input class="easyui-textbox" id="keyword" prompt="请输入会员名或电话或卡号"/>
    <input id="gradeId" class="easyui-combobox"
           data-options="prompt:'选择会员等级',valueField:'id',textField:'name',
                url:'/dictionaryItem/selectItemByDictionarySn.do?dictionarySn=grade',panelHeight:'auto'"/>
    <span style="color: #CC2222"></span>
    有效期 <input id="beginDate" class="easyui-datebox" prompt="开始时间"/>~
    <input id="endDate" class="easyui-datebox" prompt="结束时间"/>
    &nbsp;生日: <input id="birthdayBeginDate" class="easyui-datebox" prompt="开始时间"/>~

    <input id="birthdayEndDate" class="easyui-datebox" prompt="结束时间"/>

    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="searchs">确认</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-btn="reloadData">刷新</a>
    <table style="align-content: center">
        <tr style="align-content: center" >
            <td>
                      <div style="padding:10px;align-content: center;" class="easyui-panel" data-options="width:280, height:180,border:false">
                           <p style="color: black"><span style="font-size: 15px;">&emsp;今天过生日的会员</span></p>
                          <p style="color: red;font-size:30px" align="center"><i><span class="feteCount">${result.todayBirthday}</span>位</i></p>
                          <p style="color: black; margin-left: 50px ;font-size:13px">本月还有&emsp;<span style="color: #CC2222" class="feteCount" >${result.monthBirthday}</span>&emsp;位会员过生日
                              <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-btn="monthBirthday"></a>
                          </p>

                      </div>
                </span>
            </td>
            <td>
                      <div style="padding:10px;align-content: center;" class="easyui-panel" data-options="width:280, height:180,border:false">
                           <p style="color: black"><span style="font-size: 15px;">&emsp;累计消费</span></p>
                          <p style="color: red;font-size:30px" align="center"><i><span class="feteCount">${result.totalAmount}</span>元</i></p>
                          <p style="color: black; margin-left: 20px;margin-bottom: 50px ;font-size:13px">${result.totalMen}位会员,消费订单&emsp;<span style="color: #CC2222" class="feteCount" >${result.totalNumber}</span>&emsp;笔</p>

                      </div>
                </span>
            </td>
            <td>
                      <div style="padding:10px;align-content: center;" class="easyui-panel" data-options="width:280, height:180,border:false">
                           <p style="color: black"><span style="font-size: 15px;">&emsp;会员总数:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;储值卡总额:<span style="color: #CC2222">${result.sumbalance}</span></p>
                          <p style="color: red;font-size:30px" align="center">${result.sumMember}<span class="feteCount"></span>位</></p>

                      </div>
                </span>
            </td>

        </tr>


    </table>

</div>

<%--重置密码的操作--%>
<div id="reset_buttons">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="resetSaveOrUpdate"
       data-param="">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="resetCancel">取消</a>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div id="changeState"> 设置挂失</div>
    <div>
        <span>打开</span>
        <div style="width:150px;">
            <div id="resetPsw"><b>重置密码</b></div>
            <div>Excel</div>
            <div>PowerPoint</div>
        </div>
    </div>
    <div data-options="iconCls:'icon-save'">Save</div>
    <div class="menu-sep"></div>
    <div>返回</div>
</div>

<div id="reset_dialog">
    <form id="reset_form" method="post">

        <table align="center" style="margin-top: 20px">


            <input name="id" type="hidden" id="hiddenId"/>
            <input name="name" type="hidden" id="hiddenName"/>
            <tr>
                <td>密码:</td>
                <td>
                    <input id="password" name="password" validType="length[1,18]"
                           class="easyui-validatebox" required="true" type="password" value=""/>
                </td>
            </tr>
            <tr>
                <td>确认密码:</td>
                <td>
                    <input type="password" name="repassword" id="repassword" required="true" class="easyui-validatebox"
                           validType="equalTo['#password']" invalidMessage="两次输入密码不匹配"/>
                </td>
            </tr>
        </table>
    </form>

</div>

<div id="member_dialog">
    <form id="member_form" method="post">
        <table align="center">
            <tr>
                <td>
                    <input name="id" type="hidden"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">会员卡号:</font>

                </td>
                <td>
                    <input name="memberNum" class="easyui-textbox"/>
                </td>
                <td>

                    <font size="3">会员姓名:</font>

                </td>
                <td>
                    <input name="name" class="easyui-textbox"/>
                </td>
            </tr>
            <tr id="passwordId">
                <td>

                    <font size="3">密码:</font>

                </td>
                <td>
                    <input name="password" class="easyui-passwordbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">手机号码:</font>

                </td>
                <td>
                    <input name="phone" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">积分:</font>

                </td>
                <td>
                    <input name="points" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">余额:</font>

                </td>
                <td>
                    <input name="balance" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">有效期:</font>

                </td>
                <td>
                    <input name="usefullife" class="easyui-datebox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">会员生日:</font>

                </td>
                <td>
                    <input id="birthdayId" name="birthday" class="easyui-datebox"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">会员等级:</font>

                </td>
                <td>
                    <input name="grade.id" class="easyui-combobox"
                           data-options="valueField:'id',panelHeight:'auto',textField:'name',
                           url:'/dictionaryItem/selectItemByDictionarySn.do?dictionarySn=grade'"/>
                </td>
            </tr>
            <tr>
                <td>

                    <font size="3">支付方式:</font>

                </td>
                <td>
                    <input name="payment.id" class="easyui-combobox"
                           data-options="valueField:'id',textField:'name',
                               url:'/dictionaryItem/selectItemByDictionarySn.do?dictionarySn=payment',panelHeight:'auto'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="member_button">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="cancel">取消</a>
</div>


</body>
</html>
