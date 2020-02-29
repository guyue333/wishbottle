// miniprogram/pages/treehole/writeTreehole.js
var util = require("../../utils/util.js")
const device = wx.getSystemInfoSync();   //获取设备信息
const width = device.windowWidth - 30;    //30是内边距的宽度

Page({

  /**
   * 页面的初始数据
   */
  data: {
    textareaWidth:width,      //多行文本输入框的宽度
    treeholeContent:'',       //用户在输入框中输入的内容
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  //键盘输入时触发该函数
  inputChange:function(event){
    console.log('用户正在输入')
    console.log(event.detail)
    this.setData({
      treeholeContent:event.detail.value
    })
  }, 

  //用户点击提交按钮触发该函数
  formSubmit:function(event){
    var that = this
    console.log('用户点击提交按钮')
    console.log(event.detail)
    if(event.detail.value.treeholeContent == ""){
      wx.showModal({
        content: '树洞内容不能为空',
      })
    }else{    //树洞内容不为空，发送请求到数据库后台插入这条树洞信息
      //获取当前系统时间
      var time = util.formatTime(new Date())
      //获取当前用户的id
      wx.getStorage({
        key: 'userId',
        success: function(res) {
          wx.request({
            url: 'http://127.0.0.1:8080/treeholes',
            method:"POST",
            data:{
              writerId:res.data,          //发布者id
              createTime:time,                //树洞创建时间
              treeholeContent: event.detail.value.treeholeContent,    //树洞内容
              treeholeStatus: 0,       //树洞状态，0表示正常发布
              likedNumber:0          //点赞数，树洞一开始被创建时，点赞数量是0
            },
            header:{
              'content-type':'application/json',
            },
            success:function(res){
              console.log(res.data)
              that.setData({
                treeholeContent:''      //树洞插入成功后将树洞内容清空
              })
              //发布成功，给出提示
              wx.showToast({
                title: '树洞已发布',
                icon: 'success',
                duration: 1500
              })
            }
          })
        },
      })
    }
  },

  //点击查看热门话题触发该函数
  toHotTopic:function(){
    console.log("这里应该弹出热门话题。。。")
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