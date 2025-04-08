App({
  globalData: {
    userInfo: null,
    apiBaseUrl: 'http://localhost:8888/api',
    likedProfiles: [],
    matches: [],
    userLocation: null,
    refreshDiscoverPage: false,
    refreshMomentsPage: false
  },
  
  onLaunch: function() {
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: res => {
              this.globalData.userInfo = res.userInfo
              
              // 登录
              wx.login({
                success: loginRes => {
                  // 发送 code 到后台换取 openId, sessionKey, unionId
                  if (loginRes.code) {
                    wx.request({
                      url: this.globalData.apiBaseUrl + '/login',
                      data: {
                        code: loginRes.code,
                        userInfo: res.userInfo
                      },
                      success: result => {
                        // 保存登录凭证
                        wx.setStorageSync('token', result.data.token)
                      }
                    })
                  }
                }
              })
            }
          })
        }
      }
    })
    
    // 获取位置权限
    wx.getSetting({
      success: res => {
        if (!res.authSetting['scope.userLocation']) {
          wx.authorize({
            scope: 'scope.userLocation',
            success: () => {
              this.getLocation()
            }
          })
        } else {
          this.getLocation()
        }
      }
    })
  },
  
  getLocation: function() {
    wx.getLocation({
      type: 'wgs84',
      success: res => {
        this.globalData.userLocation = {
          latitude: res.latitude,
          longitude: res.longitude
        }
      }
    })
  }
}) 