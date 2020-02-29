// miniprogram/pages/myself/myself.js
const app = getApp();   
Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: 'data:image/png;base64,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',
    nickName:'点击头像进行授权',
    logged: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that  = this

    //检查登录态是否过期
    wx.checkSession({
      success: function () {
        console.log("登录态未过期")
      },
      fail: function () {
        console.log("登录态已过期，需要重新登录")

        //调用接口获取登录凭证
        wx.login({
          success: function (res) {
            if (res.code) {
              // console.log(res.code)
              // 发起网络请求
              wx.request({
                url: 'http://127.0.0.1:8080/userLogin',
                method: "POST",
                data: {
                  code: res.code
                },
                header: {
                  'content-type': 'application/x-www-form-urlencoded'
                },
                success: function (res) {
                  // console.log("返回的用户id类型是：" + typeof res.data)
                  //把返回的用户id存储到本地缓存中
                  wx.setStorage({
                    key: 'userId',
                    data: res.data,
                  })
                }
              })
            } else {
              console.log("登录失败！" + res.errMsg)
            }
          }
        })
      }
    })

    // 查看是否授权
    wx.getSetting({
      success: res => {
        if(res.authSetting['scope.userInfo']){
          //已经授权，发送请求到数据库中查询出对应的用户信息
          //获取当前用户的id
          wx.getStorage({
            key: 'userId',
            success: function(res) {
              console.log("当前用户的id为："+res.data)
              //根据用户id的值查询对应的用户信息
              wx.request({
                url: 'http://127.0.0.1:8080/user/'+res.data,
                method:'GET',
                header:{
                  'content-type': 'application/x-www-form-urlencoded'
                },
                success:function(res){
                  // console.log(res.data)
                  that.setData({
                    nickName:res.data.userName,
                    avatarUrl:res.data.userAvatar
                  })
                }
              })
            },
          })
        }
      }
    })
  },

  //点击机器人客服触发该函数
  toCustomerService:function(){
    wx.navigateTo({
      url: '../customerService/customerService',
    })
  },
 
  //用户点击头像框获取信息的函数
  onGetUserInfo: function (e) {
    var that = this;
    // console.log("e.detail")
    // console.log(e.detail)
    if (!that.data.logged && e.detail.userInfo) {
      //如果用户允许授权，先获取用户的id值
      wx.getStorage({
        key: 'userId',
        success: function(res) {
          console.log("缓存中的userId为："+res.data)

          //获取到userid以后，我们根据userid的值对用户进行操作
          wx.request({
            //由于在用户登录阶段，后台已经新增了一条没有信息的用户记录，所以在这里我们把其他信息更新即可
            url: 'http://127.0.0.1:8080/user/'+res.data,
            method: "PUT",   //默认是GET请求
            data: {
              userId: res.data,
              userName: e.detail.userInfo.nickName,
              userGender: e.detail.userInfo.gender,
              userAvatar: e.detail.userInfo.avatarUrl,
              userCity: e.detail.userInfo.city,
              userProvince: e.detail.userInfo.province
            },
            header: {
              'content-type': 'application/json'
            },
            success(res) {
              console.log(res.data)
              //更新成功以后，把用户的信息绑定到对应的变量上
              that.setData({
                logged: true,
                avatarUrl: e.detail.userInfo.avatarUrl,
                nickName:e.detail.userInfo.nickName,
              })
            }
          })
        },
      })
      
    }
  },
 
  //点击修改资料按钮触发该函数
  toModifyData:function(event){
    //查看是否授权
    wx.getSetting({
      success:res=>{
        if(res.authSetting['scope.userInfo']){
          //如果用户已经授权，那么直接跳转
          wx.navigateTo({
            url:'../modifyData/modifyData'
          })
        }else{
          //如果没有授权，提示用户授权以后才能使用小程序功能
          wx.showModal({
            title: '点击头像框进行授权',
            content: '授权以后才能体验小程序的功能',
            showCancel:false,                       //不显示取消按钮
            confirmColor: "#39b54a",
          })
        }
      }
    })
  },

  //点击我的心愿按钮触发该函数
  toMyWish: function (event) {
    //查看是否授权
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          //如果用户已经授权，那么直接跳转
          wx.navigateTo({
            url: '../myWish/myWish'
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

  //点击我的树洞按钮触发该函数
  toMyTreehole: function (event) {
    //查看是否授权
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          //如果用户已经授权，那么直接跳转
          wx.navigateTo({
            url: '../myTreehole/myTreehole'
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

  //点击关于我们出发该函数
  toAboutUs:function(){
    wx.navigateTo({
      url: '../aboutUs/aboutUs',
    })
  },

  //点击联系我们显示二维码
  showQrcode: function(){
    wx.previewImage({
      //这里均为云文件的File ID
      current:'cloud://dev-hmgna.6465-dev-hmgna-1300940634/微信图片_20200102101718.png',
      urls: ['cloud://dev-hmgna.6465-dev-hmgna-1300940634/微信图片_20200102101718.png'],
    })
  },

  //点击分享给好友弹出提示信息
  Share:function(){
    //在这里应该生成小程序的二维码，但是我先在这里给出提示信息
    wx.showModal({
      title: '分享小程序',
      content: '点击右上角的分享图标，邀请更多人加入我们吧',
      showCancel: false,    //不显示取消按钮
      confirmColor: "#39b54a",
      success(res) {
        if (res.confirm) {
          console.log('用户点击确定')
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
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
    //每一次该页面显示时，都去数据库中查询最新的用户信息
    //获取当前用户的id
    wx.getStorage({
      key: 'userId',
      success: function (res) {
        console.log("当前用户的id为：" + res.data)
        //根据用户id的值查询对应的用户信息
        wx.request({
          url: 'http://127.0.0.1:8080/user/' + res.data,
          method: 'GET',
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          success: function (res) {
            // console.log(res.data)
            that.setData({
              nickName: res.data.userName,
              avatarUrl: res.data.userAvatar
            })
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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function (res) {
    if(res.from === 'button'){
      console.log("点击页面转发按钮进行转发")
    }else if(res.from === 'menu'){
      console.log("点击转发菜单进行转发")
    }
    
    //自定义转发内容
    return{
      title:'一起来玩吧'
    }
  }
})