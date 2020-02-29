// miniprogram/pages/wish/wish.js
const app = getApp();
const device = wx.getSystemInfoSync();     //获取设备信息
const screenHeight = device.screenHeight

Page({

  /**
   * 页面的初始数据
   */
  data: {
    ColorList:app.globalData.ColorList,
    screenHeight: screenHeight,
    avatarUrl:'https://6465-dev-hmgna-1300940634.tcb.qcloud.la/15271445357138304.jpg?sign=cdda5a7a02ef765a3f9577837ed41aed&t=1578637859',
    nickName:'某不知名用户',
    createTime:'2020-01-01 12:00:00',
    wishInfo:[],            //用户心愿的详细信息
    havePick:false,          //是否捞到过心愿
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  //用户点击某一条具体的心愿时跳转到心愿详情页面
  toWishDetail:function(event){
    console.log(event)
    wx.navigateTo({
      //点击某一条心愿跳转到详情页面时，传入对应的心愿id
      url: './wishDetail?wishId=' + event.currentTarget.dataset.wishid
    })
  },

  //用户点击写心愿按钮触发该函数
  writeWish:function(){

    //获取用户授权信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          console.log('用户点击了写心愿按钮')
          //如果用户已经授权，那么直接跳转
          wx.navigateTo({
            url: './writeWish'
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

  //点击捞心愿按钮触发该函数
  digWish:function(event){
    
    var that = this 
    //获取用户授权信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          //如果用户已经授权，提示用户正在捞瓶子
          wx.showLoading({
            title: '打捞中，请稍等',
            mask:false,
          })
          //获取用户id
          wx.getStorage({
            key: 'userId',
            success: function(res) {
              wx.request({
                url: 'http://127.0.0.1:8080/digWish/'+res.data,
                method:"GET",
                header:{
                  'content-type':'application/json'
                },
                success:function(res){
                  if(res.data != ""){
                    //如果返回不为空
                    var wishid = res.data.wishId
                    wx.hideLoading()
                    wx.showModal({
                      title: '',
                      content: '捞到一个心愿瓶',
                      confirmText: '打开看看',
                      showCancel: false,
                      success:function(res){
                        if(res.confirm){
                          //用户点击打开看看，跳转到这条心愿的详情页面
                          wx.navigateTo({
                            //点击某一条心愿跳转到详情页面时，传入对应的心愿id
                            url: './wishDetail?wishId=' + wishid
                          })
                        }else{
                          console.log('用户点击取消')
                        }
                      }
                    })
                  }else{
                    //如果返回为空
                    wx.hideLoading()
                    wx.showModal({
                      title: '',
                      content: '什么也没捞到',
                      showCancel: false
                    })
                  }
                }
              })
            },
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

  //当用户长按某一条心愿时，给出提示消息
  showModal: function (event) {
    // console.log(event)
    // console.log("你确定要删除吗")
    this.setData({
      modalName: event.currentTarget.dataset.target,
      wishid: event.currentTarget.dataset.wishid   //把选中的树洞的id设置到变量上
    })
  },
  //用户点击 “确定”，删除对应的树洞信息
  deleteWish: function (event) {
    var that = this
    var wishId = this.data.wishid
    wx.request({
      url: 'http://127.0.0.1:8080/deleteByPicker/' + wishId,
      method: "DELETE",
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          modalName: null
        })
        //重新查询数据库中的心愿信息
        //获取当前用户的id
        wx.getStorage({
          key: 'userId',
          success: function (res) {
            //向后台发送请求，获取用户的心愿信息
            wx.request({
              url: 'http://127.0.0.1:8080/wishesByPickerId/' + res.data,
              method: "GET",
              header: {
                'content-type': 'application/json'
              },
              success: function (res) {
                console.log(res.data)
                if (res.data.length != 0) {
                  //如果表中数据不为空
                  that.setData({
                    havePick: true,
                    wishInfo: res.data,
                  })
                } else {
                  //如果没有数据
                  that.setData({
                    havePick: false,
                    wishInfo: []
                  })
                }
              }
            })
          },
        })
        //删除成功后给出提示信息
        wx.showToast({
          title: '删除成功',
          icon: 'success',
          duration: 1500
        })
      }
    })
  },
  //用户点击 “取消”，隐藏提示框
  hideModal: function (event) {
    this.setData({
      modalName: null
    })
  },

  // 轮播图切换图片时触发该函数
  cardSwiper(e) {
    console.log(e.detail)
    this.setData({
      cardCur: e.detail.current
    })
  },

  //防止蒙层穿透（在动画显示时不能对页面进行操作）
  catchtouchmove:function(event){},

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
    //在这里查询数据库，展示用户捞到的心愿信息
    //获取当前用户的id，作为pickerId传递到后端
    wx.getStorage({
      key: 'userId',
      success: function(res) {
        wx.request({
          url: 'http://127.0.0.1:8080/wishesByPickerId/'+res.data,
          method:"GET",
          header:{
            'content-type': 'application/json'
          },
          success:function(res){
            console.log(res.data)
            if(res.data.length != 0){
              //如果表中数据不为空
              that.setData({
                havePick:true,
                wishInfo:res.data
              })
            }
          }
        })
      },
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