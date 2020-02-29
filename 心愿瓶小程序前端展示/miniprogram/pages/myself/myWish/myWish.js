// miniprogram/pages/myself/myWish/myWish.js
const app = getApp()
Page({
 
  /**
   * 页面的初始数据
   */
  data: {
    ColorList:app.globalData.ColorList,
    avatarUrl: 'https://6465-dev-hmgna-1300940634.tcb.qcloud.la/15271445357138304.jpg?sign=cdda5a7a02ef765a3f9577837ed41aed&t=1578637859',
    nickName: '某不知名用户',
    createTime: '2020-01-01 12:00:00',
    wishInfo:[],                       //心愿信息
    havePublish:false        //是否发布过心愿
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)

    var that = this
    //这里应该展示用户自己发布过的心愿信息
    //获取当前用户的id
    wx.getStorage({
      key: 'userId',
      success: function(res) {
        //向后台发送请求，获取用户的心愿信息
        wx.request({
          url: 'http://127.0.0.1:8080/wishesByUserId/'+res.data,
          method:"GET",
          header:{
            'content-type': 'application/json'
          },
          success:function(res){
            console.log(res.data)
            if(res.data.length != 0){
              //如果表中数据不为空
              that.setData({
                havePublish: true,
                wishInfo: res.data,
              })
            } else {
              that.setData({
                havePublish: false,
              })
            }
          }
        })
      },
    })
  },

  //当用户点击某一条具体的心愿时，跳转到该心愿的详情页面
  toDetail: function (event) {
    console.log('正在跳转到心愿详情页面。。。')
    console.log(event.currentTarget.dataset.treeholeid)
    wx.navigateTo({
      //点击某一条树洞跳转到详情页面时，传入对应的树洞id
      url: '../../wish/wishDetail?wishId=' + event.currentTarget.dataset.wishid
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
      url: 'http://127.0.0.1:8080/deleteBySelf/' + wishId,
      method: "DELETE",
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          modalName: null
        })
        //重新查询数据库中的树洞信息
        //获取当前用户的id
        wx.getStorage({
          key: 'userId',
          success: function (res) {
            //向后台发送请求，获取用户的心愿信息
            wx.request({
              url: 'http://127.0.0.1:8080/wishesByUserId/' + res.data,
              method: "GET",
              header: {
                'content-type': 'application/json'
              },
              success: function (res) {
                console.log(res.data)
                if (res.data.length != 0) {
                  //如果表中数据不为空
                  that.setData({
                    havePublish: true,
                    wishInfo: res.data,
                  })
                } else {
                  //如果没有数据
                  that.setData({
                    havePublish: false,
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