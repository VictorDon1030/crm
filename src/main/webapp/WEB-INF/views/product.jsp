<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/product.js"></script>
    <title>Title</title>
    <%--声明一个样式提供--%>
    <style type="text/css">
        .myBtn{
            background-color: #ffffb6;

        }
    </style>

</head>
<body >

<table id="product_datagrid" class="easyui-datagrid" >
</table>

<div id="toolbar">
    <%--表示我需要有这个权限才可以使用 也可以自定义为角色--%>
        &emsp;<a class="easyui-linkbutton" name="add" data-options="iconCls:'icon-add', plain:true " data-cmd="add">新增</a>
        &emsp; <a class="easyui-linkbutton" name="edit" data-options="iconCls:'icon-edit', plain:true " data-cmd="edit">编辑</a>
        &emsp;<a id="btn_changeState" class="easyui-linkbutton" name="remove" data-options="iconCls:'icon-remove', plain:true " data-cmd="remove">删除</a>
        &emsp;<a class="easyui-linkbutton" name="reload" data-options="iconCls:'icon-reload', plain:true " data-cmd="reload">刷新</a>
        &emsp; <input id="keyword" class="easyui-textbox" prompt="商品名称/品牌/销售单价" style="width:180px">
        &emsp; <input id="beginDate" class="easyui-datebox" prompt="过期的开始时间">至
        <input id="endDate" class="easyui-datebox" prompt="过期的结束时间">
        <a class="easyui-linkbutton"  data-options="iconCls:'icon-search', plain:true " data-cmd="searchs">关键字查询</a>
        &emsp;&emsp;<a class="easyui-linkbutton" data-options="iconCls:'icon-undo', plain:true " onclick="window.open('/product/exportXls.do')">导出</a>
   <%-- <form action="/product/importXls.do" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit" value="上传"/>
    </form>--%>
        <form id="min_form" method="post">
            <table>
                <tr>
                    <td>
                        <div class="easyui-panel" style="width:320px;height:170px;  background-color: #ffedde;">
                            <p style="color: #CC2222"><span style="font-size: 18px;">&emsp;已经过期商品总数量</span></p>
                            <p style="color: darkorange;font-size:30px" align="center"><i><span id="pastDue">0</span>件</i></p>
                            <p style="color: #0000ff">&emsp;<span style="font-size: 20px;" id="beAboutTo">0</span><span style="font-size: 18px;" >件&emsp;商品两个月后过期</span></p>
                        </div>
                    </td>

                    <td>

                        <div class="easyui-panel" style="width:320px;height:170px;  background-color: #ffffb6;">
                            <p style="color: #ff73fd"><span style="font-size: 18px;">&emsp;商品均价</span></p>
                            <p style="font-size:30px ;" align="center"><span style="color: darkorange"><i><span id="avgPrice">0</span>元</i></span></p>
                        </div>
                    </td>

                    <td>
                        <div id="p" class="easyui-panel" style="width:320px;height:170px; background-color: #dff0d8;">
                            <p><span style="font-size: 18px;">&emsp;共有商品</span></p>
                            <p style="font-size:30px ;" align="center"><span style="color: darkorange"><i
                                    id="totalProduct"><span id="piece">0</span>种</i></span></p>
                            <p style="font-size:14px">库存低于
                                <span style="color: navy"><i>10件的有</i></span>
                                <span style="color: darkorange"><i id="someProduct">请期待下集 谢谢</i></span>  商品
                            </p>
                        </div>
                    </td>

                    <td>
                        <div class="easyui-panel" style="width:320px;height:170px; background-color: #99cdff;">
                            <p style="font-size:14px">待需求</p>
                            <p style="font-size:30px ;" align="center"><span style="color: darkorange"><i
                                    id="maxPrice">0</i></span></p>
                            <p style="font-size:30px ;" align="center"><span style="color: darkblue">
                        <i>小唐 666号</i></span></p>
                        </div>
                    </td>
                </tr>
            </table>
        </form>

</div>
<!--弹窗 重置密码页面 closed 隐藏起来 点击重置密码打开 -->
<%--<div id="reset_dialog" class="easyui-dialog" title="My Dialog">
    <form id="reset_form" method="post">

    </form>
</div>--%>


<!--弹窗 编辑页面 closed 隐藏起来 点击新增打开 -->
<div id="product_dialog" class="easyui-dialog">
    <form id="product_form" method="post">
        <input type="hidden" name="id"/>
            <tr >
                <center><span style="font-size: 20px;font-weight: bold; line-height: 45px">新增商品</span></center>
            </tr>

            <tr>
                &emsp;
                <td >
                 <span style="font-size: 20px;">一级分类</span>&emsp;
                <td><input id="stair" name="stair.id" class="easyui-combobox" style="width:190px; height:35px;"></td>
              <%--  <input id="gardenCombox" name="garden" qr="garden" required="true" type="hidden" class="queryRequired" />--%>
                <input type="button" onclick="newAdd()" value="新增" style="background-color: #00ffff">
                </td>
                &emsp;&emsp;
                <td>
                    <span style="font-size: 20px;">二级分类</span>;&emsp;
                <td><input id="gardenApprovalTypeCombox" name="secondary.id" class="easyui-combobox" style="width:190px; height:35px;"></td>
                <%--<input id="typeId" name="typeId" qr="typeId" required="true" type="hidden" class="queryRequired" />--%>
                </td>
                </td>
            </tr></br>

            <tr style="margin-top: 50px">
                &emsp;
               <td>
                    <span style="font-size: 18px;">商品条码</span>&emsp;
                    <input type="text" class="easyui-textbox" name=""  value="" prompt="努力制作中" style="width:200px; height:30px;"/>
                </td>
                <td >&emsp;
                    <span style="font-size: 18px;">&emsp;&emsp;&emsp;商品名称</span>&emsp;
                    <input type="text" class="easyui-textbox" name="name"  value="" prompt="输入商品名称" style="width:200px; height:30px;"/>
                </td>
            </tr></br>

            <tr>
                <td>&emsp;
                    <span style="font-size: 18px;">商品品牌</span>&emsp;
                    <input type="text" class="easyui-textbox"  name="brand"  value="" prompt="" style="width:200px; height:30px;"/>
                </td>
                <td>&emsp;
                    <span style="font-size: 18px;">&emsp;&emsp;&emsp;助词码&emsp;</span><i class="colorff"></i>&emsp;
                    <input type="text" class="easyui-textbox" name="auxiliaryWord"  value="" prompt="输入助词码" style="width:200px; height:30px;"/>
                </td>
            </tr></br>


            <tr>
                <td>&emsp;
                    <span style="font-size: 18px;">商品货号</span>&emsp;
                    <input type="text" class="easyui-textbox" name="goodsMark" value="" prompt="输入商品货号"prompt="" style="width:200px; height:30px;"/>
                </td>
                <td>&emsp;
                    <span style="font-size: 18px;">&emsp;&emsp;&emsp;参考进价</span>&emsp;
                    <input type="number" class="easyui-textbox" name="purchasingPrice" value="" prompt="输入参考进价"prompt="" style="width:200px; height:30px;"/>
                </td>
            </tr></br>

            <tr>
                <td>&emsp;
                    <span style="font-size: 18px;">销售单价</span>&emsp;
                    <input type="number" class="easyui-textbox"  name="unitpPrice"  value="" prompt="输入售价" prompt="" style="width:200px; height:30px;"/>
                </td>
                <td>&emsp;
                    <span style="font-size: 18px;">&emsp;&emsp;&emsp;会员售价</span><i class="colorff"></i>&emsp;
                    <input type="number" class="easyui-textbox" name="memberPrice"  value="" prompt="输入会员售价" prompt="" style="width:200px; height:30px;"/>
                </td>
            </tr></br>

            <tr>
                <td id="" style="display:none;">&emsp;
                    <span style="font-size: 18px;">会员折扣</span><i class="colorff"></i>&emsp;
                    <select class="easyui-combobox" name="minDiscount" prompt="" style="width:200px; height:30px;"></select>
                </td>
                <td>&emsp;
                    <span style="font-size: 18px;">&emsp;&emsp;&emsp;最低售价</span>&emsp;
                    <input type="number" class="easyui-textbox" name="minPrice" value="" prompt="输入会员售价" prompt="" style="width:200px; height:30px;"/>
                </td>
            </tr></br>

            <tr>
                <td>&emsp;
                    <span style="font-size: 18px;">初始库存</span>&emsp;
                        <input type="number" class="easyui-textbox"  name="initialInventory" value="" prompt="努力制作中" style="width:200px; height:30px;"/>
                </td>
                <td>&emsp;
                    <span style="font-size: 18px;">&emsp;&emsp;&emsp;商品规格</span>&emsp;
                    <input type="text" class="easyui-textbox" name="specification" value="" prompt="输入规格" style="width:200px; height:30px;"/>
                </td>
            </tr></br>


            <tr>
                <td>&emsp;
                    <span style="font-size: 18px;">商品单位</span>&emsp;
                    <select name="unit" class="easyui-combobox" style="width:200px; height:35px;">
                        <option value="件" selected="">件</option>
                        <option value="条" selected="">条</option>
                    </select>
                    <input type="button" onclick="newSave2()" value="新增" style="background-color: #ff73fd">
                </td>
                <td>&emsp;
                    <span style="font-size: 18px;">        商品积分</span>&emsp;
                    <input class="easyui-textbox" type="number" name="integral" prompt="输入商品积分" value="" style="width:200px; height:30px;"/>
                </td>
            </tr></br>

            <tr>
                <td>&emsp;
                    <span style="font-size: 18px;">商品提成</span>&emsp;
                    <input type="number" class="easyui-textbox"  name="commission" prompt="输入产品提成" value=""style="width:200px; height:30px;"/>
                </td>
                <td>&emsp;
                    <span style="font-size: 18px;">&emsp;&emsp;&emsp;过期时间</span>&emsp;
                    <input  class="easyui-datebox" name="pastDueTime"  prompt="输入过期时间"style="width:200px; height:30px;"/>
                </td>
            </tr></br>

            <tr>
                <td>&emsp;
                    <span style="font-size: 18px;">备注信息</span>&emsp;
                    <input type="text" class="easyui-textbox" maxlength="50" name="remark"  prompt="最多输入50字"style="width:200px; height:30px;"/>
                </td>
            </tr></br>

            <tr>
                <td>&emsp;
                    <span style="font-size: 18px;">商品组合</span>&emsp;
                            <input type="radio" data-package-type="0" class="checkinput CombinationButtenClick" name="packchecked"/>
                            <span style="font-size: 18px;">包装组合</span>
                            <input type="radio" data-package-type="1" class="checkinput CombinationButtenClick" name="packchecked" />
                            <span style="font-size: 18px;">套餐组合</span>

                    <input type="hidden" id="combinationBarcodeInfo">
                    <input type="hidden" id="readBarcodeInfo">

                </td>
            </tr></br>

            <tr>
                <td id="power_id_sv_pricing_method">&emsp;
                    <span class="labelinfo" style="font-size: 20px;">是否计重</span>&emsp;
                    <input class="testswitch-checkbox" id="Weightswitch" type="checkbox">
                    <div class="testswitch">
                        <label class="testswitch-label" for="Weightswitch"/>
                            <span class="testswitch-inner" data-on="开" data-off="关"></span>
                            <span class="testswitch-switch"></span>
                    </div>

                </td>
            </tr></br>
    </form>


       <%-- <div class="takePictures">
            <span>商品图片</span>
            <input class="file Picturesfile hidden" type="file" name="" id="" value="">
        </div>--%>
</div>


<!--弹窗 编辑页面 closed 隐藏起来 点击新增打开 -->
<div id="product2_dialog" class="easyui-dialog" title="My Dialog">
    <form id="product2_form" method="post">
        <input type="hidden" name="id"/>
            <p align="center"><span style="font-size: 18px;" >新增大分类</span></p>
            <div>
                &emsp;&emsp;<span style="font-size: 18px;"> 选择类型 &nbsp;:</span>
                &emsp;<a id="addStyle" class="easyui-linkbutton" data-options="plain:true" onclick="genre()"><span style="font-size: 18px;">产品类型</span></a>
                &emsp;<a id="addStyle2" class="easyui-linkbutton" data-options="plain:true" onclick="serve()"><span style="font-size: 18px;">服务产品</span></a>
            </div>

        <p>&emsp;
            &emsp;<span style="font-size: 18px;">类别名称</span>&emsp;
            <input type="text" class="easyui-textbox" maxlength="50" name="name"  prompt="输入名称"style="width:200px; height:30px;"/>
        </p>
    </form>
</div>

<div id="buttons" >
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok', plain:true " data-cmd="save">提交</a>
    <a  class="easyui-linkbutton" data-options="iconCls:'icon-no', plain:true " data-cmd="cancel">取消</a>
</div>
<div id="buttons2" >
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok', plain:true " onclick="save2()">提交</a>
    <a  class="easyui-linkbutton" data-options="iconCls:'icon-no', plain:true " onclick="cancel2()">取消</a>
</div>


</body>
</html>

