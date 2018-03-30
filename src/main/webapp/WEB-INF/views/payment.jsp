<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/payment.js"></script>
    <title>支付设置</title>

    <style type="text/css">
        .font{
            color: #9eb9c2;
        }
        .bg{
            margin-left: 10px;
            font-size: 1.4ch;
            font-family: 'Microsoft YaHei';
        }
        .by{
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div id="tt" class="easyui-tabs" data-options="fit:true,border:false">
    <div title="已绑定支付" style="display:none;" data-options="fit:true">
        <iframe src="/joinApply/view.do" height="100%" width="100%" frameborder="0"></iframe>
    </div>
    <div title="绑定信息" style="display:none;" data-options="fit:true">
        <iframe id="weChat" src="" height="100%" width="100%" frameborder="0"></iframe>
    </div>
    <div title="支付申请" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">
        <div>
            <form id="ff" method="post">
                <div>
                    <span>1. 填写支付申请信息<i class="font"> | 开通支付功能必填信息</i></span>
                    <div class="by">
                        <span class="bg">店铺名称 : <input name="shopName" class="easyui-textbox" data-options="required:true"/></span>
                        <span class="bg">联系电话 : <input name="tel" class="easyui-textbox" data-options="required:true"/></span>
                    </div>
                    <div class="by">
                        <span class="bg">联系邮箱 : <input name="email" class="easyui-textbox" data-options="required:true,validType:'email'"/></span>
                        <span class="bg">开户类型 : <input name="accountType" type="radio"/>个体
                            <input name="accountType" type="radio"/>企业</span>
                    </div>
                    <div class="by">
                        <span class="bg">开户银行 : <input name="bankName" class="easyui-textbox" data-options="required:true"/></span>
                        <span class="bg">开户名称 : <input name="accountName" class="easyui-textbox" data-options="required:true"/></span>
                    </div>
                </div>

                <div style="margin-top: 15px">
                    <span>2. 填写支付绑定相关<i class="font"> | 开通支付功能必填信息</i></span>
                    <div class="by">
                        <span class="bg">威信账号 : <input name="accountNumber" class="easyui-textbox" data-options="required:true"/></span>
                    </div>
                    <div class="by">
                        <span class="bg">应用秘钥 : <input name="secretkey" class="easyui-passwordbox" data-options="required:true"/></span>
                    </div>
                    <div class="by">
                        <span class="bg">用户秘钥 : <input name="applyKey" class="easyui-passwordbox" data-options="required:true"/></span>
                    </div>
                </div>
                <div style="margin-top: 20px">
                    <button id="tj"  style="width: 80px;height: 45px;background-color: limegreen">确定保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
