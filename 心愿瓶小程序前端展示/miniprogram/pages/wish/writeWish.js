// miniprogram/pages/wish/writeWish.js
var util = require('../../utils/util.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tags:['学习','大学生活','吐槽','保密','游戏'],  //用户的标签
    index:null,          //标签数组的下标
    wishContent:'',        //心愿内容

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this 

    //页面加载完毕后发送请求到数据库，查询所有标签
    wx.request({
      url: 'http://127.0.0.1:8080/tags',
      method:"GET",
      success:function(res){
        console.log(res)
        //将数据库中的标签信息绑定到页面上
        that.setData({
          tags:res.data
        })
      }
    })
  },

  //点击提交按钮触发该函数
  formSubmit:function(event){ 
    var that = this
    console.log('点击了提交按钮')
    console.log(event.detail)
    if (event.detail.value.wishContent == ""){
      wx.showModal({
        content: '心愿内容不能为空',
      })
    } else if (event.detail.value.tag == null ){
      wx.showModal({
        content: '请选择标签',
      })
    } else {      //心愿和标签都不为空，发送请求到后台，插入该心愿信息
      //获取当前系统时间
      var time = util.formatTime(new Date());
      console.log("当前时间是："+time)
      //获取当前用户id
      wx.getStorage({
        key: 'userId',
        success: function(res) {
          wx.request({
            url: 'http://127.0.0.1:8080/wishes',
            method:"POST",
            data:{
              writerId:res.data,          //作者id
              tagId:parseInt(event.detail.value.tag) + 1,    //标签id （数组下标加1），在这里要先把字符串转换成整数
              createTime:time,          //心愿创建时间
              wishContent:event.detail.value.wishContent,    //心愿内容
              wishStatus:0        //心愿状态，0表示正常发布
            },
            header:{
              'content-type':'application/json'
            },
            success:function(res){
              console.log(res.data)
              that.setData({          //心愿插入成功以后，清空用输入的值
                wishContent:'',
                index:null,
              })
              //心愿发布成功，给出提示
              wx.showToast({
                title: '心愿已发布',
                icon:'success',
                duration:1500
              })

            }
          })
        },
      })
    }
  },

  //当文本框的内容发生变化时触发该函数
  inputChange:function(e){
    console.log(e.detail)
  },

  //标签的值发生变化时触发该函数
  tagsChange: function(e){
    console.log(this.data.tags[e.detail.value])
    this.setData({
      index:e.detail.value
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})