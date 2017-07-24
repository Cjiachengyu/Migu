var weixinShareConfig = {
	shareDomainUrl : 'http://' + window.location.host,

	shareLink : "",
	shareTitle : "",    // 分享到好友有title+msg
	shareMsg : "",      // 分享到朋友圈只有msg
	shareImage : "",
	shareInitCallback : function() {
		// 分享模块初始化成功后调用
	},
	shareSuccessCallback : function() {
		// 成功分享后调用
	}
};

var weixinShareFn = {
	getShareSignature : function() {
		$.ajax({
			'url' : '/qzy/weixin/getsignature',
			'type' : 'post',
			'data' : {'url' : location.href.split('#')[0]},
			'dataType' : 'json',
			'success' : function(data, statusText) {
				wx.config({
					debug : false,
					appId : data.appId,
					timestamp : data.timestamp,
					nonceStr : data.nonceStr,
					signature : data.signature,
					jsApiList : ['onMenuShareTimeline', 'onMenuShareAppMessage']
				});

				weixinShareConfig.shareInitCallback();
			},
			'error' : function(xhr, e1, e2) {
				weixinShareFn.getShareSignature();
			}
		});
	},

	initShare : function() {
		wx.error(function(res) {
			Alert(JSON.stringify(res));
		});

		wx.ready(function() {
			wx.onMenuShareAppMessage({
				title : weixinShareConfig.shareTitle,
				desc : weixinShareConfig.shareMsg,
				link : weixinShareConfig.shareDomainUrl + weixinShareConfig.shareLink,
				imgUrl : weixinShareConfig.shareDomainUrl + weixinShareConfig.shareImage,
				success : weixinShareConfig.shareSuccessCallback,
				cancel : function() {
				},
				fail : function(res) {
					Alert('分享失败，wx.onMenuShareAppMessage: ' + JSON.stringify(res));
				}
			});

			wx.onMenuShareTimeline({
				title : weixinShareConfig.shareMsg,
				link : weixinShareConfig.shareDomainUrl + weixinShareConfig.shareLink,
				imgUrl : weixinShareConfig.shareDomainUrl + weixinShareConfig.shareImage,
				success : weixinShareConfig.shareSuccessCallback,
				cancel : function() {
				},
				fail : function(res) {
					Alert('分享失败，wx.onMenuShareAppMessage: ' + JSON.stringify(res));
				}
			});
		});

		weixinShareFn.getShareSignature();
	},

	getShareInfoForAndroid : function() {
		window.myjsAndroid.jsFunction(
			weixinShareConfig.shareDomainUrl + weixinShareConfig.shareLink,
			weixinShareConfig.shareTitle,
			weixinShareConfig.shareMsg,
			weixinShareConfig.shareDomainUrl + weixinShareConfig.shareImage
		);
	}
};

document.addEventListener('WebViewJavascriptBridgeReady', onBridgeReady, false);
function onBridgeReady(event) {
	var bridge = event.bridge;

	bridge.init(function(message, responseCallback) {
		var data = {
			'link' : weixinShareConfig.shareDomainUrl + weixinShareConfig.shareLink,
			'title' : weixinShareConfig.shareTitle,
			'msg' : weixinShareConfig.shareMsg,
			'img' : weixinShareConfig.shareDomainUrl + weixinShareConfig.shareImage,
		};
		responseCallback(data)
	});

	bridge.registerHandler('testJavascriptHandler', function(data, responseCallback) {
		var responseData = {'Javascript Says' : 'Right back atcha!'};
		responseCallback(responseData)
	})
}

