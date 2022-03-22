<template>
	<view class="p-2">
		<!-- 未登录 -->
		<template v-if="!loginStatus">
			<view>未登录</view>
			<image :src="avatar" class="size-100 rounded-circle"></image>
		</template>

		<!-- 登录 -->
		<template v-else>
			<!-- 头像区域 -->
			<view class="flex align-center p-2 py-3 bg-white mt-2">
				<image :src="avatar" class="size-100 rounded-circle"></image>
				<view class="flex-1 flex flex-column px-3">
					<text class="font-md font-weight-bold text-muted mb-1">{{nickname}}</text>
					<view>
						<text class="mr-1 text-muted">总帖数</text>
						<text class="text-pink">{{data[0].num}}</text>
						<text class="mx-1 text-muted">今日发帖</text>
						<text class="text-pink">{{data[1].num}}</text>
					</view>
				</view>
				<text class="iconfont icon-jinru text-muted" @tap="open('settings')"></text>
			</view>

			<!-- 数据区域 -->
			<view class="flex align-center bg-white py-3 my-2">
				<view v-for="(item,index) in data" :key="index"
					class="flex flex-column flex-1 align-center justify-center">
					<text class="mb-1 font-md text-body font-weight-bold">{{item.num}}</text>
					<text class="font-sm text-muted">{{item.title}}</text>
				</view>
			</view>

			<!-- banner区域 -->
			<view class="p-2">
				<image src="../../static/img/banner.jpg" mode="widthFix" class="w-100 rounded"></image>
			</view>

			<!-- 列表区域 -->
			<view class="pt-2 bg-white mt-1">
				<view class="border-bottom">
					<uni-list-item showExtraIcon title="浏览历史">
						<text slot="icon" class="iconfont icon-liulan text-body font-md"></text>
					</uni-list-item>
				</view>
				<view class="border-bottom">
					<uni-list-item showExtraIcon title="会员VIP">
						<text slot="icon" class="iconfont icon-huiyuanvip text-body font-md"></text>
					</uni-list-item>
				</view>
				<view class="border-bottom">
					<uni-list-item showExtraIcon title="帖子审核">
						<text slot="icon" class="iconfont icon-keyboard text-body font-md"></text>
					</uni-list-item>
				</view>
			</view>
		</template>
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
				loginStatus: true,
				nickname: 'radical',
				data: [{
						title: '帖子',
						num: 78,
					},
					{
						title: '动态',
						num: 34,
					},
					{
						title: '评论',
						num: 23,
					},
					{
						title: '粉丝',
						num: 45,
					},
				]
			}
		},
		computed: {
			avatar() {
				return this.loginStatus === true ? '/static/img/me.jpg' : '/static/img/nologin.jpeg'
			}
		},
		onNavigationBarButtonTap() {
			uni.showToast({
				title: '点击',
				duration: 2000
			})
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
