// miniprogram/pages/treehole/treehole.js
const app = getApp();
const device = wx.getSystemInfoSync();     //获取设备信息
const width = ((device.windowWidth - 30) / 2) - 2;   //解决7p不适应问题，在最后减去2个px
const formLeft = (device.windowWidth - 100) / 2;   //100是按钮本身的宽度

Page({

  /**
   * 页面的初始数据
   */
  data: {
    ColorList: app.globalData.ColorList,
    buttonFromLeft:formLeft,        //为了让写树洞按钮居中显示，要知道写树洞按钮离屏幕左边的距离
    treeholeWidth:width,     //每一条树洞的宽度
    avatarUrl:'https://6465-dev-hmgna-1300940634.tcb.qcloud.la/user-unlogin.png?sign=eedc61bd412bc29f266fc5384625d512&t=1578468317',//用户头像
    nickName:'匿名用户',    //用户昵称
    createTime:'2020-01-01',    //树洞创建时间，该时间应该对应着数据库的数据
    appreciateNumber:0,         //每一条树洞的点赞数，应该对应数据库中的数据
    commentNumber:0,            //每一条树洞的回复数，应该对应数据库中的数据
    treeholeInfo:[],        //树洞信息列表
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },


  //点击“我要写”按钮触发该函数
  writeTreehole:function(event){    

    //获取用户授权信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          //如果用户已经授权，直接进行跳转
          console.log("用户点击我要写按钮，跳转到写树洞界面")
          wx.navigateTo({
            url: './writeTreehole',
          })
        } else {
          //如果没有授权，提示用户授权以后才能使用小程序功能
          wx.showModal({
            title: '点击头像框进行授权',
            content: '授权以后才能体验小程序的功能',
            showCancel: false,                       //不显示取消按钮
            confirmColor: "#39b54a",
          })
        }
      }
    })
  },

  //跳转到树洞详情页面
  toTreeholeDetail:function(event){
    console.log('正在跳转到树洞详情页面。。。')
    console.log(event.currentTarget.dataset.treeholeid)
    wx.navigateTo({ 
      //点击某一条树洞跳转到详情页面时，传入对应的树洞id
      url: './treeholeDetail?treeholeId=' + event.currentTarget.dataset.treeholeid   
    })
  },

  // cardSwiper
  cardSwiper(e) {
    console.log(e.detail)
    this.setData({
      cardCur: e.detail.current
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
    var that = this

    //在每一次onShow函数加载的时候，查询数据库中的数据
    wx.request({
      url: 'http://192.168.0.105:8080/treeholes',
      method:"GET",
      header:{
        'content-type':'application/json'
      },
      success:function(res){
        console.log(res.data)
        that.setData({
          treeholeInfo:res.data
        })
      }
    })
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
    console.log("到达页面底部了")
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})