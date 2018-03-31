<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
 <%--   <script type="text/javascript" src="/static/plugins/jiaoben5714/js/ai.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/plugins/jiaoben5714/css/ai.css">--%>

    <script type="text/javascript" src="/static/js/member.js"></script>
    <title>会员管理</title>
</head>
<body>
<table id="member_datagrid"></table>
<div id="member_toolbar">
    <shiro:hasRole name="大队长"> </shiro:hasRole>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-btn="addData">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-btn="editData">编辑</a>
    <%--<a id="changeState" class="easyui-linkbutton"--%>
       <%--data-options="iconCls:'icon-remove',plain:true" data-btn="changeState">设置挂失</a>--%>

    <%--<a id="changePassword" class="easyui-linkbutton" data-options="iconCls:'icon-bqt',plain:true" data-btn="changePassword">重置密码</a>--%>
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
    <%--
    <li id="nav-header-serv" class="top-cat hassub" data-cat="engine">
        <h2><a href="javascript:void(0);">技术引擎</a><i class="top-cat-arrow"></i></h2>
    </li>

    <div class="cat">
        <div class="cat-box" data-cat="engine">
            <div class="layout">
                <div class="cat-group">
                    <h3 class="cat-tit"><div class="cat-ico"><i class="ico-lan"></i></div>自然语言处理</h3>
                    <ul class="cat-list">
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <span>基础文本分析</span>
                            </div>
                            <div class="cat-item-sub">
                                <div class="cat-item-row">
                                    <a href="#participle">分词/词性</a>
                                    <a href="#proper">专有名词</a>
                                    <a href="#synonym">同义词</a>
                                </div>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <span>语义解析</span>
                            </div>
                            <div class="cat-item-sub">
                                <div class="cat-item-row">
                                    <a href="#">意图/成分<i class="cat-tag is-beta"></i></a>
                                </div>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <a href="#">情感分析</a>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <span>机器翻译</span>
                            </div>
                            <div class="cat-item-sub">
                                <div class="cat-item-row">
                                    <a href="#">文本翻译</a>
                                </div>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <a href="#">智能闲聊<i class="cat-tag is-hot"></i></a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="cat-group">
                    <h3 class="cat-tit"><div class="cat-ico"><i class="ico-image"></i></div>计算机视觉</h3>
                    <ul class="cat-list">
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <span>OCR<i class="cat-tag is-hot"></i></span>
                            </div>
                            <div class="cat-item-sub">
                                <div class="cat-item-row">
                                    <a href="#identify">身份证识别</a>
                                    <a href="#card">名片识别</a>
                                    <a href="#driverregistration">行驶证/驾驶证识别</a>
                                </div>
                                <div class="cat-item-row">
                                    <a href="#businesslicense">营业执照识别</a>
                                    <a href="#creditcard">银行卡识别</a>
                                    <a href="#common">通用识别</a>
                                </div>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <span>人脸识别</span>
                            </div>
                            <div class="cat-item-sub">
                                <div class="cat-item-row">
                                    <a href="#detect">人脸分析</a>
                                    <a href="#multiface">多人脸检测<i class="cat-tag is-new"></i></a>
                                    <a href="#compare">人脸对比</a>
                                    <a href="#search">人脸搜索</a>
                                </div>
                                <div class="cat-item-row">
                                    <a href="#shape">五官定位</a>
                                    <a href="javascript:void(0);" class="disabled">人脸核身(即将开放)</a>
                                </div>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <span>图片识别</span>
                            </div>
                            <div class="cat-item-sub">
                                <div class="cat-item-row">
                                    <a href="#express">看图说话</a>
                                </div>
                                <div class="cat-item-row">
                                    <a href="#plants">花草识别<i class="cat-tag is-hot"></i></a>
                                </div>
                                <div class="cat-item-row">
                                    <a href="#cars">车辆识别<i class="cat-tag is-new"></i></a>
                                </div>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <span>图片特效<i class="cat-tag is-new"></i></span>
                            </div>
                            <div class="cat-item-sub">
                                <div class="cat-item-row">
                                    <a href="#">人脸美妆</a>
                                </div>
                                <div class="cat-item-row">
                                    <a href="#">颜龄检测</a>
                                </div>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <a href="#">智能鉴黄</a>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <a href="#">暴恐识别</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="cat-group">
                    <h3 class="cat-tit"><div class="cat-ico"><i class="ico-voice"></i></div>智能语音</h3>
                    <ul class="cat-list">
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <span>语音识别</span>
                            </div>
                            <div class="cat-item-sub">
                                <div class="cat-item-row">
                                    <a href="#">关键词检索<i class="cat-tag is-new"></i></a>
                                </div>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <a href="#">语音合成<i class="cat-tag is-new"></i></a>
                            </div>
                        </li>
                        <li class="cat-item">
                            <div class="cat-item-main">
                                <span>语音唤醒(即将开放)</span>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

--%>
</div>

<%--重置密码的操作--%>
<div id="reset_buttons">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-btn="resetSaveOrUpdate" data-param="">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-btn="resetCancel">取消</a>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div id="changeState"> 设置挂失</div>
    <div>
        <span>打开</span>
        <div style="width:150px;">
            <div id="resetPsw" ><b>重置密码</b></div>
            <div>Excel</div>
            <div>PowerPoint</div>
        </div>
    </div>
    <div data-options="iconCls:'icon-save'">Save</div>
    <div class="menu-sep"></div>
    <div>返回</div>
</div>

<div id="reset_dialog" >
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
            <%--<tr>--%>
                <%--<td>--%>

                    <%--<font size="1">会员姓名:</font>--%>

                <%--</td>--%>
                <%--<td>--%>
                    <%--<input name="name" class="easyui-textbox"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
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
                    <input name="birthday" class="easyui-datebox"/>
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
                    <input name="payment.id"  class="easyui-combobox"
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
