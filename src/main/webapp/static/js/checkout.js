$(function () {
    var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
        renderRow: function(target, fields, frozen, rowIndex, rowData){
            var cc = [];
            cc.push("<td colspan=" + fields.length + " style='padding:10px 5px;border:0;'>");
            if (!frozen){
                var img = rowData.imagePath;
                cc.push('<img src="'+img+'" style="width:150px;float:left"/>');
                cc.push('<div style="float:left;margin-left:20px;">');
                for(var i=0; i<fields.length; i++){
                    var copts = $(target).datagrid('getColumnOption', fields[i]);
                    cc.push('<p><span class="c-label">' + copts.title + ':</span> ' + rowData[fields[i]] + '</p>');
                }
                cc.push('</div>');
            }
            cc.push('</td>');
            return cc.join('');
        }
    });

    $('#dictionaryItem').datagrid({
        view: cardview,
        onClickRow:function (index,row) {
            console.log(row);
        }
    });
})
