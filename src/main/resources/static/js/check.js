layui.use('form', function(){
  var form = layui.form;
  var $ = layui.jquery;
  //复选框全选以及取消全选
  $("#chooseall").on("click",function () {
    var checked = $("#chooseall").is(':checked');
    if(checked){
      $("input[name='datas']").prop("checked",true)
    }else{
      $("input[name='datas']").prop("checked",false)
    }
    form.render("checkbox");
  });
});
// +----------------------------------------------------------------------
// | 列表页通用引用文件（如有特殊需求可参照此文件写一个单独的js文件,如:sys_config.js）
// +----------------------------------------------------------------------
layui.use(['element', 'layer'], function () {
  var element = layui.element;
  var layer = layui.layer;
  var $ = layui.jquery;

  var active = {
    // | 弹出层（新增、编辑）
    // +----------------------------------------------------------------------
  onwrite:function(){
    var dw_url = $(this).attr("dw-url");//URL地址，必填
    console.log("dw_url");
    console.log(dw_url);
    var dw_title = $(this).attr("dw-title");//弹出层标题，必填
    var dw_width = $(this).attr("dw-width");//弹出层宽度，如80%或500px；如果没有默认为屏幕宽度的50%
    var dw_height = $(this).attr("dw-height");//弹出层高度，如50%或500px；如果没有默认为屏幕高度的50%
    if (dw_url == undefined) {
      layer.msg("请给button加上dw-url属性");
      return false;
    }
    if (dw_title == undefined) {
      layer.msg("请给button加上dw-title属性");
      return false;
    }
    if (dw_width == undefined) dw_width = '50%';
    if (dw_height == undefined) dw_height = '80%';
    layer.open({
      type: 2,
      title: dw_title,
      shadeClose: true,
      shade: 0.8,
      closeBtn:false,
      area: [dw_width, dw_height],
      content: dw_url,
      cancel: function (index, layero) {
        $(".dw-refresh").trigger('click');
        return false;
      }
    });
    },
    // | 删除
    // +----------------------------------------------------------------------
  deleteone:function(){
    var dw_url = $(this).attr("dw-url");//URL地址，必填
    var dw_title = $(this).attr("dw-title");//删除数据标识，如姓名，默认为'该数据'
    if (dw_url == undefined) {
      layer.msg("请给button加上dw-url属性");
      return false;
    }
    if (dw_title == undefined) {
      dw_title = '该数据';
    }
    layer.open({
      type: 1,//注意1和2的区别,对content
      title: "删除信息提示",
      shadeClose: true,
      shade: 0.8,
      closeBtn:false,
      area: '500px',
      btn:['确定','取消'],
      btnAlign:'c',
      content: '<h3 style="padding: 20px;text-align: center;">确定要删除 [ ' + dw_title + ' ] 吗?</h3>',
      success:function (layero) {
        var btn = layero.find('.layui-layer-btn');
        btn.find('.layui-layer-btn0').attr({
          href:dw_url,
        });
      }
    });
    },
    // | 刷新
    // +----------------------------------------------------------------------
  refresh:function(){
      location.href = location.href;
    },
    // | 批量删除,用的ajax传数组
    // +----------------------------------------------------------------------
  deletemore:function(){
    var dw_url = $(this).attr("dw-url");//URL地址，必填
    if (dw_url == undefined) {
      layer.msg("请给button加上dw-url属性");
      return false;
    }
    var chk_value = [];//将需要删除的id存起来
    $('input[name="datas"]:checked').each(function () {
      chk_value.push($(this).val());
    });
    if (chk_value.length == 0) {
      layer.msg("请选择要删除的数据!", { anim: 1 });
      return false;
    }
    layer.open({
      type: 1,//注意1和2的区别,对content
      title: "删除信息提示",
      shadeClose: true,
      shade: 0.8,
      closeBtn:false,
      area: '500px',
      btn:['确定','取消'],
      btnAlign:'c',
      content: '<h3 style="padding: 20px;text-align: center;">确定要删除选中的  ' + chk_value.length + '  条数据吗?</h3>',
      success:function(layero){
        var btn = layero.find('.layui-layer-btn');
        btn.find('.layui-layer-btn0').click(function () {
          $.ajax({
            async:true,
            url:dw_url,
            type: 'POST',
            dataType: 'json',
            data:{"listid": JSON.stringify(chk_value)},
            success:function(data){
              eval(data);
            }
          });
        });
      }
    });
    }
  }

$('.layui-btn').on('click',function(){
  var othis = $(this), method = othis.data('method');
  active[method]?active[method].call(this,othis):'';
})

});