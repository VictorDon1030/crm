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

</head>
<body >

<table id="product_datagrid" class="easyui-datagrid" >
</table>

<div id="toolbar">
    <%--表示我需要有这个权限才可以使用 也可以自定义为角色--%>
        <a class="easyui-linkbutton" name="add" data-options="iconCls:'icon-add', plain:true " data-cmd="add">新增</a>
        <a class="easyui-linkbutton" name="edit" data-options="iconCls:'icon-edit', plain:true " data-cmd="edit">编辑</a>
        <a id="btn_changeState" class="easyui-linkbutton" name="remove" data-options="iconCls:'icon-remove', plain:true " data-cmd="remove">删除</a>
        <a class="easyui-linkbutton" name="reload" data-options="iconCls:'icon-reload', plain:true " data-cmd="reload">刷新</a>
        <input id="keyword" class="easyui-textbox" prompt="商品名称/品牌/销售单价" style="width:180px">
        <input id="beginDate" class="easyui-datebox" prompt="过期的开始时间">至
        <input id="endDate" class="easyui-datebox" prompt="过期的结束时间">
        <a class="easyui-linkbutton"  data-options="iconCls:'icon-search', plain:true " data-cmd="searchs">关键字查询</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search', plain:true " onclick="window.open('/product/exportXls.do')">导出</a>
   <%-- <form action="/product/importXls.do" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit" value="上传"/>
    </form>--%>
        <form id="min_form" method="post">
            <table>
                <tr>
                    <td>
                        <div id="p" class="easyui-panel" style="width:320px;height:170px; background-color: #ffedde;">
                            <p style="font-size:14px">共有商品</p>
                            <p style="font-size:30px ;" align="center"><span style="color: darkorange"><i
                                    id="totalProduct"><span id="piece">0</span></i>种</span></p>
                            <p style="font-size:14px">库存低于
                                <span style="color: darkorange"><i>10件</i>的有</span>
                                <span style="color: darkorange"><i id="someProduct">21</i>件</span>商品</p>
                        </div>
                    </td>

                    <td>

                        <div class="easyui-panel" style="width:320px;height:170px;  background-color: #ffffb6;">
                            <p style="font-size:14px">商品均价</p>
                            <p style="font-size:30px ;" align="center"><span style="color: darkorange">
                                <i><span id="avgPrice">0</span></i>元</span></p>
                        </div>
                    </td>
                    <td>
                        <div class="easyui-panel" style="width:320px;height:170px;  background-color: #dff0d8;">
                            <p style="font-size:14px">已经过期商品总数量</p>
                            <p style="color: darkorange;font-size:30px" align="center"><span id="pastDue">0</span>件 </p>
                            <p>的有<span style="color: darkorange" align="center"><i id="lessProduct">21</i>件</span>商品</p>
                        </div>
                    </td>
                    <td>
                        <div class="easyui-panel" style="width:320px;height:170px; background-color: #99cdff;">
                            <p style="font-size:14px">最佳选</p>
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
                <select class="easyui-combobox" style="width:190px; height:35px;">
                    <option value="44978" data-type="0">饮料</option>
                    <option value="45084" data-type="0">烟酒</option>
                    <option value="45085" data-type="0">日用品</option>
                    <option value="45900" data-type="0">食品类</option>
                    <option value="46063" data-type="0">酱油</option>
                    <option value="46498" data-type="0">水果</option>
                    <option value="46492" data-type="0">水</option>
                    <option value="46606" data-type="0">aa</option>
                </select>
                <input type="button"  value="新增" style="background-color: #00ffff">
                </td>
                &emsp;&emsp;
                <td>
                    <span style="font-size: 20px;">二级分类</span>;&emsp;
                    <select class="easyui-combobox"
                            style="width:186px; height:35px;">
                        <option value="0">二级类别</option>
                        <option value="37293">可乐</option></select>
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
                    <input type="button" value="新增" style="background-color: #ff73fd">
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

<div id="buttons" >
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok', plain:true " data-cmd="save">提交</a>
    <a  class="easyui-linkbutton" data-options="iconCls:'icon-no', plain:true " data-cmd="cancel">取消</a>
</div>


</body>
</html>

