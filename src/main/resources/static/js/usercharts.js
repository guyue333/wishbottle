layui.use('element', function(){

});

var myChart = echarts.init(document.getElementById('sexchart'));
var myChart2 = echarts.init(document.getElementById('provincechart'));
var myChart4 = echarts.init(document.getElementById('agechart'));
myChart.setOption({
    title: {
        text: '用户性别数据加载'
    },
    tooltip: {},
    legend: {
        data:['男','女']
    },
    xAxis: {
        data: ['人数']
    },
    yAxis: {},
    series: [{
        name: [''],
        type: 'bar',
        data: []
    },{
        name: [''],
        type: 'bar',
        data: []
    }]
});
// myChart.showLoading();
$("#sexbtn").click(function () {
    //异步加载数据
    $.ajax({
        async: false,
        url: "/user/userchartTest",
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            myChart.hideLoading();  // 隐藏 loading 效果
            // 填入数据
            eval(data);
            var boy = 0;
            var girl = 0;
            for(var i = 0;i < data.length;i++){
                if (data[i].userGender == 0){
                    girl++;
                }else {
                    boy++;
                }
            }
            myChart.setOption({
                series: [{
                    name: ['男'],
                    type: 'bar',
                    data: [boy]
                },{
                    name: ['女'],
                    type: 'bar',
                    data: [girl]
                }]
            });
        }
    });
});
myChart2.showLoading();
$("#provincebtn").click(function () {
    $.ajax({
        async: false,
        url: "/user/userProvince",
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            myChart2.hideLoading();  // 隐藏 loading 效果
            eval(data);
            myChart2.setOption({
                title: {
                    text: '用户所在省份一览表',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: data.name
                },
                series: [
                    {
                        name: '数据',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: data,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            });
        }
    });
})
myChart4.showLoading();
$("#agebtn").click(function () {
    $.ajax({
        async: false,
        url: "/user/userAge",
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            myChart4.hideLoading();
            eval(data);
            var age = [];
            var count = [];
            for (var i=0;i<data.length;i++){
                age.push(data[i].name);
                count.push(data[i].value);
            }
            myChart4.setOption({
                title: {
                    text: '用户年龄数据加载'
                },
                tooltip: {},
                legend:{
                    data:["年龄"]
                },
                xAxis: {
                    type: 'category',
                    data: age
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: count,
                    type: 'line'
                }]
            });
        }
    });
});