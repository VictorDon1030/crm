//跳转分店
function myshop(){
    $('#import').attr('src','/myshop.do');
}
//本店信息
function subbranch(){
    $('#import').attr('src','/subBranch/view.do');
}
//管理信息
function manage(){
    $('#import').attr('src','/management.do');
}
//数据信息
function dataManage(){
    $('#import').attr('src','/dataManage.do');
}

//支付设置
function payment(){
    $('#import').attr('src','/payment.do');
}

//操作日志
function systemLog(){
    $('#import').attr('src','/systemLog/view.do');
}
//登陆日志
function loginLog(){
    $('#import').attr('src','/loginLog/view.do');
}
