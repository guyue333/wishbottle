console.log("ssss")
//表单验证
layui.use(['layer','form'], function(){
	var $ = layui.jquery;
    var form = layui.form;
    var layer = layui.layer;

    //自定义验证规则
    form.verify({
    	//这里的变量名和前面的lay-verify中一致，默认是required是不能为空
    	managename:function(value){
    		if (value.length < 4) {
    			return '请输入至少4位用户名！	';
    		}else if(value.length > 10){
    			return '请输入不要超过10位用户名！ ';
			}
    	},
    	managepwd:function(value){
    		if (value.length <6) {
    			return '请输入至少6位密码！	';
    		}else if(value.length > 12){
				return '请输入不要超过12位密码！ ';
			}
    	},
		whitever:function(value){
			if (value.length == 0) {
				return '输入内容不能为空！';
			}
		}
    })
	//自定义下拉框监听,管理员
	form.on('select(level)', function(data){
		console.log(data.value); //得到被选中的值
		if (data.value == 1){
			$('.txtsearch').css('display','none');
			$('.numsearch').css('display','inline-block');
			$('.num1search').css('display','none');
		}else if (data.value == 3){
			$('.txtsearch').css('display','none');
			$('.num1search').css('display','inline-block');
			$('.numsearch').css('display','none');
		}else {
			$('.txtsearch').css('display','inline-block');
			$('.numsearch').css('display','none');
			$('.num1search').css('display','none');
		}
	});
    //用户
	form.on('select(userlevel)',function (data) {
		if(data.value == 1 || data.value == 3){
			$('.usertxt').css('display','none');
			$('.usernum').css('display','inline-block');
			$('.usersex').css('display','none');
		}else if(data.value == 4){
			$('.usertxt').css('display','none');
			$('.usersex').css('display','inline-block');
			$('.usernum').css('display','none');
		}else {
			$('.usertxt').css('display','inline-block');
			$('.usernum').css('display','none');
			$('.usersex').css('display','none');
		}
	});
	//复选框全选
	/*form.on('checkbox(yesChoose)', function(data) {
		console.log("shenme");
		console.log(data.value);
		$("input[name='datas']").each(function (index, item) {
			item.checked = data.elem.checked;
		});
		form.render("checkbox");
	});*/

});

//主页面功能使用
layui.use(['element'], function () {
});

$('.numsearch').css('display','none');
$('.num1search').css('display','none');
$('.usernum').css('display','none');
$('.usersex').css('display','none');