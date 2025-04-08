const app = getApp()

Page({
  data: {
    moments: [],
    loading: true,
    page: 1,
    hasMore: true
  },

  onLoad: function() {
    this.loadMoments()
  },

  onShow: function() {
    // 如果需要刷新
    if (app.globalData.refreshMomentsPage) {
      this.setData({
        moments: [],
        page: 1,
        hasMore: true
      })
      this.loadMoments()
      app.globalData.refreshMomentsPage = false
    }
  },

  // 加载动态数据
  loadMoments: function() {
    if (!this.data.hasMore || this.data.loading) return
    
    this.setData({
      loading: true
    })
    
    wx.request({
      url: app.globalData.apiBaseUrl + '/moments',
      method: 'GET',
      header: {
        'Authorization': 'Bearer ' + wx.getStorageSync('token')
      },
      data: {
        page: this.data.page,
        pageSize: 10
      },
      success: res => {
        if (res.data.success) {
          const newMoments = [...this.data.moments, ...res.data.data]
          this.setData({
            moments: newMoments,
            loading: false,
            page: this.data.page + 1,
            hasMore: res.data.data.length === 10
          })
        } else {
          this.setData({
            loading: false,
            hasMore: false
          })
        }
      },
      fail: () => {
        // 模拟数据
        const mockData = [
          {
            id: 1,
            userId: 101,
            userName: '小红',
            userAvatar: 'https://img.yzcdn.cn/vant/cat.jpeg',
            content: '今天天气真好，去公园散步了~',
            images: [
              'https://img.yzcdn.cn/vant/cat.jpeg',
              'https://img.yzcdn.cn/vant/dog.jpeg'
            ],
            location: '北京市朝阳区',
            createTime: '2小时前',
            likeCount: 12,
            commentCount: 3,
            isLiked: false,
            comments: [
              {
                id: 1,
                userId: 102,
                userName: '小明',
                content: '真好啊，羡慕~',
                createTime: '1小时前'
              },
              {
                id: 2,
                userId: 103,
                userName: '小花',
                content: '公园里的花开了吗？',
                createTime: '30分钟前'
              }
            ]
          },
          {
            id: 2,
            userId: 102,
            userName: '小明',
            userAvatar: 'https://img.yzcdn.cn/vant/dog.jpeg',
            content: '分享一部最近看的好电影《千与千寻》，非常推荐！',
            images: [
              'https://img.yzcdn.cn/vant/dog.jpeg'
            ],
            createTime: '3小时前',
            likeCount: 18,
            commentCount: 5,
            isLiked: true,
            comments: [
              {
                id: 3,
                userId: 101,
                userName: '小红',
                content: '我也很喜欢这部电影！',
                createTime: '2小时前'
              }
            ]
          }
        ]
        
        const newMoments = [...this.data.moments, ...mockData]
        this.setData({
          moments: newMoments,
          loading: false,
          page: this.data.page + 1,
          hasMore: this.data.page < 3 // 模拟3页数据
        })
      }
    })
  },

  // 下拉刷新
  onPullDownRefresh: function() {
    this.setData({
      moments: [],
      page: 1,
      hasMore: true
    })
    
    this.loadMoments()
    wx.stopPullDownRefresh()
  },

  // 上拉加载更多
  onReachBottom: function() {
    this.loadMoments()
  },

  // 点赞动态
  likeMoment: function(e) {
    const momentId = e.currentTarget.dataset.id
    const momentIndex = this.data.moments.findIndex(moment => moment.id === momentId)
    
    if (momentIndex === -1) return
    
    const isLiked = this.data.moments[momentIndex].isLiked
    
    // 更新UI
    const newMoments = [...this.data.moments]
    newMoments[momentIndex].isLiked = !isLiked
    newMoments[momentIndex].likeCount = isLiked 
      ? newMoments[momentIndex].likeCount - 1 
      : newMoments[momentIndex].likeCount + 1
    
    this.setData({
      moments: newMoments
    })
    
    // 发送请求
    wx.request({
      url: app.globalData.apiBaseUrl + '/moments/' + momentId + '/like',
      method: isLiked ? 'DELETE' : 'POST',
      header: {
        'Authorization': 'Bearer ' + wx.getStorageSync('token')
      }
    })
  },

  // 发表评论
  showCommentInput: function(e) {
    const momentId = e.currentTarget.dataset.id
    wx.navigateTo({
      url: `/pages/publishMoment/publishMoment?type=comment&momentId=${momentId}`
    })
  },

  // 查看用户详情
  viewUserDetail: function(e) {
    const userId = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '/pages/userDetail/userDetail?id=' + userId
    })
  },

  // 查看动态详情（包含全部评论）
  viewMomentDetail: function(e) {
    const momentId = e.currentTarget.dataset.id
    // 可以实现动态详情页面，展示完整内容与评论
    // 这里暂时不展开实现
    console.log('查看动态详情：', momentId)
  },

  // 预览图片
  previewImage: function(e) {
    const urls = e.currentTarget.dataset.urls
    const current = e.currentTarget.dataset.current
    
    wx.previewImage({
      urls,
      current
    })
  },

  // 发布新动态
  publishMoment: function() {
    wx.navigateTo({
      url: '/pages/publishMoment/publishMoment'
    })
  }
}) 