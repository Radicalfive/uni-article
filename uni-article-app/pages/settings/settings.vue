<template>
	<view class="px-2">
		<view class="bg-white mt-1">
			<uni-list-item title="账号与安全">
			</uni-list-item>
		</view>
		<view class="bg-white mt-1">
			<uni-list-item title="资料编辑">
			</uni-list-item>
		</view>
		<view class="bg-white mt-1">
			<uni-list-item title="清理缓存" showBadge>
				<text slot="right" class="text-muted">{{currentSize | format}}</text>
			</uni-list-item>
		</view>
		<view class="bg-white mt-1">
			<uni-list-item title="意见反馈">
			</uni-list-item>
		</view>
		<view class="bg-white mt-1">
			<uni-list-item title="关于社区">
			</uni-list-item>
		</view>
		
		<view class="p-2">
			<!-- <button class="rounded-circle bg-pink text-white shadow" @tap="open('login')">退出登录</button> -->
			<button class="rounded-circle bg-pink text-white shadow" @tap="logout">退出登录</button>
		</view>
	</view>
</template>

<script>
	import uniListItem from '@/components/uni/uni-list-item/uni-list-item.vue';
	export default {
		components: {
			uniListItem,
		},
		data() {
			return {
				currentSize: 10010 // 缓存大小
			}
		},
		// 过滤器，按照缓存大小显示不同单位
		filters: {
			format(value) {
				return value > 1024 ? (value / 1024).toFixed(2) + 'MB' : value.toFixed(2) + 'KB';
			}
		},
		methods: {
			open(path) {
				uni.navigateTo({
					url:`../${path}/${path}`,
				})
			},
			// 退出登录
			logout() {
				uni.showModal({
					content: '是否要退出登录',
					success: res => {
						if (res.confirm) {
							uni.clearStorageSync();
							// 返回上一级页面
							uni.navigateBack({
								delta: 1
							});
							uni.showToast({
								title: '退出登录成功',
								icon: 'none'
							});
						}
					}
				});
			}
		}
	}
</script>

<style>
</style>
