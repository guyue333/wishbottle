layui.use('element', function(){

});

var myChart = echarts.init(document.getElementById('cardwish'));
myChart.showLoading();
$.ajax({
    async: false,
    url: "/wish/wishCard",
    type: 'POST',
    dataType: 'json',
    success: function (data) {
        myChart.hideLoading();
        eval(data);
        var name = [];
        var count = [];
        for (var i=0;i<data.length;i++){
            name.push(data[i].name);
            count.push(data[i].value);
        }
        myChart.setOption({
            title:{
                text:"用户喜爱标签统计图"
            },
            anmation:false,
            legend:{
                data:["数量"],
                left:'50%',
                top:5
            },
            tooltip:{
                trigger:"axis"
            },
            xAxis:{
                type:'category',
                boundaryGap:false,
                data:name
            },
            yAxis:{
                type:'value',
                axisLabel:{
                    formatter:'{value}'
                }
            },
            toolbox:{
                show:true,
                orient:'vertical',
                itemSize:20,
                itemGap:20,
                feature:{
                    magicType:{type:['line','bar']},
                    restore:{},
                    saveAsImage:{}
                },

            },
            series:[
                {
                    name:'标签数量',
                    type:'line',
                    data:count,
                    markLine:{
                        data:[{type:'average',name:'平均值'}]
                    },
                    markPoint:{
                        data:[
                            {type:'max',name:'最多'},
                            {type:'min',name:'最少'}
                        ]
                    }
                }
            ]
        });
    }
});