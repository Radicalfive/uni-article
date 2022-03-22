<template>
	<view class="p-2">
		<view class="bg-white mt-1">
			<uni-list-item title="账号与安全"></uni-list-item>
		</view>
		<view class="bg-white mt-1">
			<uni-list-item title="资料编辑"></uni-list-item>
		</view>
		<view class="bg-white mt-1">
			<uni-list-item title="清楚缓存" showBadge>
				<text solt="right" class="text-muted">{{currentSize | format}}</text>
			</uni-list-item>
		</view>
		<view class="bg-white mt-1">
			<uni-list-item title="意见反馈"></uni-list-item>
		</view>
		<view class="bg-white mt-1">
			<uni-list-item title="关于社区"></uni-list-item>
		</view>

		<view class="mt-1">
			<button class="rounded-circle bg-pink text-white shadow" @tap="open('login')">退出登录</button>
		</view>
	</view>
</template>

<script>
	import uniListItem from '@/components/uni/uni-list-item/uni-list-item.vue';
	export default {
		components: {
			uniListItem
		},
		data() {
			return {
				currentSize: 10010 //缓存大小
			}
		},
		filters: {
			format(value) {
				return value > 1024 ? (value / 1024).toFixed(2) + 'MB' : value.toFixed(2) + 'KB';
			}
		},
		methods: {
			open(path) {
				uni.navigateTo({ 
					url: `../${path}/${path}`,
				})
			}
		}
	}
</script>

<style>

</style>
