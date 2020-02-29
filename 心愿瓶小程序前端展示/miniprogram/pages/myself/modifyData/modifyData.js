// miniprogram/pages/myself/modifyData/modifyData.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    height:'',    //屏幕高度
    avatarUrl:'./user-unlogin.png',    //用户头像地址
    nickName:'用户昵称',
    userInfo:{},              //用户信息
    date:'2000-01-01',   //出生日期默认值
    region:['湖北省','武汉市','洪山区'],     //所在城市默认值
    index: 1,
    picker: ['小姐姐', '小哥哥'],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("onLoad里的options")
    console.log(options)
    var that = this
    let { avatar } = options
    if (avatar) {   //这里加判断是因为用户可能会修改头像，然后再跳返回该页面，这时options就是有值的
      that.setData({
        avatarUrl : options.avatar
      })
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
              })
            }
          })
        },
      })
    }else{    //当用户直接从首页跳转过来时，options就是没值的
      //发送请求到数据库中查询用户信息
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
      
    }

    //获取系统信息
    wx.getSystemInfo({
      success: res => {
        this.setData({
          //获取当前系统的屏幕高度，然后设置给 height 变量，以达到铺满全屏的效果
          height: res.screenHeight,
        })
      },
    })
    
  },

  //点击头像框触发修改头像的函数
  changePhoto:function(){
    var that = this
    console.log("头像框被点击了")
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success(res) {
        // tempFilePath可以作为img标签的src属性显示图片
        const tempFilePaths = res.tempFilePaths
        // console.log(tempFilePaths)
        //将选择的图片传到upload页面处理
        wx.redirectTo({
          url: '../upload/upload?src='+tempFilePaths
        })
      }
    })
  },

  //日期的值改变时触发该函数
  DateChange:function(event){
    console.log("日期的值改变了")
    console.log(event.detail)
    this.setData({
      date:event.detail.value
    })
  },

  //所在城市的值改变时触发该函数
  RegionChange:function(event){
    console.log("所在城市的值改变了")
    console.log(event.detail)
    this.setData({
      region:event.detail.value
    })
  },

  //性别的值改变时触发该函数
  PickerChange:function(event){
    console.log("性别的值改变了")
    console.log(event.detail)
      this.setData({
        index:event.detail.value
      })
  },

  //点击提交按钮触发该函数，发送请求到数据库中
  formSubmit:function(event){
    var that = this
    console.log("点击了提交按钮")
    console.log(event.detail)
    wx.getStorage({
      key: 'userId',
      success: function(res) {
        //发送请求更新用户信息
        wx.request({
          url: 'http://127.0.0.1:8080/user/'+res.data,
          method:'PUT',
          data:{
            userId: res.data,
            userName: event.detail.value.nickName,
            userGender: event.detail.value.gender,
            userAvatar:that.data.avatarUrl,
            userCity: event.detail.value.city[1],
            userProvince: event.detail.value.city[0],
            userBirthday:event.detail.value.birthday
          },
          success:function(){
            //信息更新成功，给出提示
            wx.showToast({
              title: '保存资料成功',
              icon:'success',
              duration:1500
            })
          }
        })
      },
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
  onShow: function (option) {

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