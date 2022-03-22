<template>
	<view class="bg-white">
		<!-- 状态栏 -->
		<!-- #ifndef MP-WEIXIN -->
		<uni-status-bar></uni-status-bar>
		<!-- 返回按钮 -->
		<view @tap="back" class="iconfont icon-guanbi mt-5 ml-5 font-lg text-muted size-100">
		</view>
		<!-- #endif -->
		<!-- 账号密码登录 -->
		<template v-if="choick">
			<view>
				<view class="text-center" style="padding-top: 130rpx; padding-bottom: 130rpx;">
					<text class="h3 text-body">账号密码登录</text>
				</view>
				<view class="flex mb-1 p-1">
					<input type="text" v-model="phone" placeholder="手机号" class="border-bottom p-2 flex-1">
				</view>
				<view class="flex mb-1 p-1">
					<input type="text" v-model="password" placeholder="请输入密码" class="border-bottom p-2 flex-1">
					<view class="font-sm text-muted px-3 flex align-center">
						忘记密码?
					</view>
				</view>
				<view class="p-2">
					<button class="rounded-circle bg-pink text-white" :class="disable ? 'bg-pink-disabled' : 'bg-pink'"
						:loading="loading">
						{{ loading ? '登陆中...' : '登录' }}
					</button>
				</view>

				<view class="text-center mt-3 text-primary font-sm">
					<text class="p-1" @tap="choick=!choick">验证码登录</text>
					<text class="text-body mx-2">|</text>
					<text class="p-1">登录遇到问题</text>
				</view>
				<view class="text-center text-muted mt-3">
					<label class="flex justify-center">
						<checkbox :value="checked" />
						<view class="">
							<text>注册即代表同意</text>
							<text class="text-primary">《xxx协议》</text>
						</view>
					</label>
				</view>
			</view>
		</template>

		<!-- 验证码登录 -->
		<template v-else>
			<view>
				<view class="text-center" style="padding-top: 130rpx; padding-bottom: 130rpx;">
					<text class="h3 text-body">手机验证码登录</text>
				</view>
				<view class="flex mb-1 p-1">
					<view class="border-right font px-2 flex align-center">+86</view>
					<input type="text" v-model="phone" placeholder="手机号" class="border-bottom p-2 flex-1">
				</view>
				<view class="flex md-2 p-2">
					<input type="text" v-model="verifyCode" placeholder="请输入验证码" class="p-2 flex-1">
					<view @tap="getCode" :class="limitTime > 0 ? 'bg-pink-disabled' : 'bg-pink'"
						class="font-sm col-2 rounded flex align-center rounded text-white justify-center">
						{{limitTime > 0 ? limitTime + 's' : '获取验证码'}}
					</view>
				</view>
				<view class="p-2">
					<button class="rounded-circle bg-pink text-white"
						:class="disabled ? 'bg-pink-disabled' : 'bg-pink'">登录</button>
				</view>
				<view class="text-center mt-3 text-primary font-sm">
					<text class="p-1" @tap="choick=!choick">账号密码登录</text>
					<text class="text-body mx-2">|</text>
					<text class="p-1">登录遇到问题</text>
				</view>
				<view class="text-muted text-center mt-3">
					注册即代表同意
					<text class="text-primary">《xxx协议》</text>
				</view>
			</view>
			<!-- 第三方登录 -->
			<view class="flex align-center px-5 py-3 mt-3">
				<view class="flex-1 flex align-center justify-center" @click="appLogin">
					<view class="iconfont icon-weixin bg-success font-lg text-white flex 
					align-center justify-center rounded-circle size-100">
					</view>
				</view>
				<view class="flex-1 flex align-center justify-center" @click="appLogin">
					<view class="iconfont icon-QQ bg-primary font-lg text-white flex 
					align-center justify-center rounded-circle size-100">
					</view>
				</view>
				<view class="flex-1 flex align-center justify-center" @click="appLogin">
					<view class="iconfont icon-xinlangweibo bg-warning font-lg text-white flex 
					align-center justify-center rounded-circle size-100">
					</view>
				</view>
			</view>
		</template>
	</view>
</template>

<script>
	import uniStatusBar from '@/components/uni/uni-status-bar/uni-status-bar.vue';
	export default {
		components: {
			uniStatusBar
		},
		data() {
			return {
				disabled: false,
				choick: true,
				checked: false,
				loading: false,
				phone: '',
				password: '',
				verifyCode: '',
				limitTime: 0,
			}
		},
		methods: {
			back() {
				uni.navigateBack({
					delta: 1
				})
			},
			validate() {
				//手机号正则
				var mPattern = /^1[345789]\d{9}$/;
				if (!mPattern.test(this.phone)) {
					uni.showToast({
						title: '手机号格式不正确',
						icon: 'none'
					});
					return false;
				}
				// ...更多验证
				return true;
			},
			// login() {
			// 	// 账号密码登录
			// 	let data = {
			// 		username: '杨阳',
			// 		password: '123123'
			// 	}
			// 	const url = 'http://106.14.169.149:8071/login';
			// 	uni.request({
			// 		url: url,
			// 		method: 'POST',
			// 		data: data
			// 	}).then((res) => {
			// 		// console.log(res[1].data);
			// 		console.log(res[1].data.data.nickname);
			// 	})
			// },
			getCode() {
				// 防止重复点击获取验证码
				if (this.limitTime > 0) {
					return;
				}
				// 验证手机号(没通过)
				if (!this.validate()) {
					return;
				}
				this.limitTime = 10;
				let timer = setInterval(() => {
					if (this.limitTime >= 1) {
						this.limitTime--;
					} else {
						this.limitTime = 0;
						clearInterval(timer);
					}
				}, 1000)
			}
		}
	}
</script>

<style>

</style>
