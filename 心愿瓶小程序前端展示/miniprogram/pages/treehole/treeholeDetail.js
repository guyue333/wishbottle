// miniprogram/pages/treehole/treeholeDetail.js
var util = require("../../utils/util.js")
const device = wx.getSystemInfoSync();       //获取设备信息
const width = (device.windowWidth - 30 - 50);    // 30是margin的宽度，50是发送按钮的宽度

Page({

  /**
   * 页面的初始数据
   */
  data: {
    inputWidth:width,    //输入框的宽度
    hasUserinfo:false,   //用户是否授权
    treeholeInfo:{},     //树洞详细信息
    hasComment:false,    //当前是否有留言
    isZan:false,         //用户是否点赞
    likedNumber:0,       //用户的点赞数
    avatarUrl: 'https://6465-dev-hmgna-1300940634.tcb.qcloud.la/user-unlogin.png?sign=eedc61bd412bc29f266fc5384625d512&t=1578468317',//用户头像
    nickName: '匿名用户',    //用户昵称
    commentNumber: 0,            //每一条树洞的回复数，应该对应数据库中的数据
    treeholeReply:'',         //用户键盘输入的评论
    replyInfo:[],          //树洞的评论信息
    detailReason:'',        //树洞的举报原因
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    var that = this
    //获取这条树洞的id，然后存储在缓存中
    var treeholeId = JSON.parse(options.treeholeId)
    wx.setStorage({
      key: 'treeholeId',
      data: treeholeId,
    })

    //根据树洞id，查询数据库中树洞的详细信息
    wx.request({
      url: 'http://127.0.0.1:8080/treehole/'+treeholeId,
      method:"GET",
      header:{
        'content-type':'application/json'
      },
      success:function(res){
        console.log("树洞的详细信息：")
        console.log(res.data)
        that.setData({
          treeholeInfo: res.data,
          likedNumber:res.data.likedNumber
        })
      }
    })

    //根据树洞id，查询数据库中对应的评论内容
    wx.request({
      url: 'http://127.0.0.1:8080/treeholeReplies/'+treeholeId,
      method:"GET",
      header:{
        'content-type':'application/json'
      },
      success:function(res){
        if(res.data.length != 0){
            //如果数据库中有评论
            that.setData({
            hasComment: true,
            replyInfo:res.data,
          })
        }
      }
    })
    
    //根据用户id和树洞id查询用户对该条树洞的点赞状态
    wx.getStorage({
      key: 'userId',
      success: function(res) {
        wx.request({
          url: 'http://127.0.0.1:8080/getLikeStatus',
          method:"GET",
          data:{
            treeholeId: treeholeId,
            userId:res.data
          },
          header:{
            'content-type':'application/json'
          },
          success:function(res){
            console.log(res.data)
            if(res.data == 1){
              //如果用户对该条心愿点赞了
              that.setData({
                isZan:true
              })
            }
          }
        })
      },
    })
  },

  getReplies:function(res){
    console.log("getReplies is called"+res)
  },

  //键盘输入时触发该函数
  inputChange:function(event){
    //把键盘输入的值绑定到变量上
    this.data.treeholeReply = event.detail.value
  },
 
  //用户点击“发送”按钮触发该函数
  send:function(event){
    var that = this
    var replyContent = this.data.treeholeReply
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          //如果用户已经授权
          if (that.data.treeholeReply == '') {
            //如果输入为空，则进行提示
            wx.showToast({
              title: '您的输入为空，请重新输入！',
              icon: 'none',
              mask: true,
              duration: 2000
            });
          }else{
            
            //如果输入不为空，则将数据发送到后台
            //获取当前系统时间
            var time = util.formatTime(new Date())
            //获取当前树洞的id
            wx.getStorage({
              key: 'treeholeId',
              success: function(res) {
                var treeholeId = res.data
                wx.request({
                  url: 'http://127.0.0.1:8080/treeholeReply',
                  method:"POST",
                  data:{
                    treeholeId:res.data,                         //树洞的id
                    replyContent: replyContent,               //评论的内容(这里使用that.data.treeholeReply不能获取到数据)
                    createTime:time,                              //评论时间
                  },    
                  header:{
                    'content-type':'application/json'
                  },
                  success:function(res){
                    //插入成功，给出提示
                    wx.showToast({
                      title: '评论成功',
                      icon: 'success',
                      duration: 1500
                    })
                    //插入成功后，重新发送查询请求
                    wx.request({
                      url: 'http://127.0.0.1:8080/treeholeReplies/' + treeholeId,
                      method: "GET",
                      header: {
                        'content-type': 'application/json'
                      },
                      success: function (res) {
                        that.setData({
                          hasComment: true,
                          replyInfo: res.data,
                        })
                      }
                    })
                  }
                })
              },
            })
            that.setData({
              hasComment:true,
              treeholeReply:'',     //将留言消息清空
            })
          }
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

  //用户点击点赞按钮触发该函数
  likeTreehole:function(event){
    var userId = wx.getStorageSync("userId");    //获取用户id

    //判断用户之前是否点赞，如果iszan为false，则说明用户要点赞，如果iszan为true，则说明用户要取消点赞
    if (event.currentTarget.dataset.iszan == false){
      //修改页面的值
      this.setData({
        isZan:true,
        likedNumber:this.data.likedNumber + 1
      })
      //发送请求到数据库，修改数据库的值
      wx.getStorage({
        key: 'treeholeId',
        success: function(res) {
          wx.request({
            url: 'http://127.0.0.1:8080/addlikedNumer',
            data:{
              treeholeId:res.data,
              userId:userId
            },
            method: "GET",
            success: function (res) {
              console.log(res.data)
            }
          })
        },
      })
    }else{
      //修改页面的值
      this.setData({
        isZan: false,
        likedNumber: this.data.likedNumber - 1
      })
      //发送请求到数据库，修改数据库的值
      wx.getStorage({
        key: 'treeholeId',
        success: function (res) {
          wx.request({
            url: 'http://127.0.0.1:8080/minuslikedNumer',
            method: "GET",
            data:{
              treeholeId:res.data,
              userId:userId
            },
            success: function (res) {
              console.log(res.data)
            }
          })
        },
      })
    }
  },

  //点击举报树洞的举报图标显示举报原因
  showModal: function (event) {
    this.setData({
      modalName: event.currentTarget.dataset.target
    })
  },

  //隐藏举报原因
  hideModal: function (event) {
    this.setData({
      modalName: null
    })
  },

  //输入举报原因时触发该函数
  reportChange: function (event) {
    this.data.detailReason = event.detail.value
  },

  //用户点击“确定”，举报该条心愿
  reportWish: function (event) {
    var that = this
    var reportReason = this.data.detailReason
    if (reportReason == '') {    //如果用户输入的举报原因为空，给出提示
      wx.showToast({
        title: '输入不能为空',
        icon: 'none',
        mask: true,
        duration: 2000
      });
    } else {    //如果数据不为空，则发送请求到后台
      //获取当前系统时间
      var time = util.formatTime(new Date());
      //获取心愿id
      var treeholeid = wx.getStorageSync('treeholeId');
      //发送请求
      wx.request({
        url: 'http://127.0.0.1:8080/treeholeReports',
        method: "POST",
        header: {
          'content-type': 'application/json'
        },
        data: {
          treeholeId: treeholeid,
          reportReason: reportReason,
          createTime: time
        },
        success: function (res) {
          console.log(res.data)
          that.setData({
            modalName: null
          })
          wx.showToast({
            title: '举报成功',
            icon: 'success',
            duration: 1500
          })
        }
      })
    }
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