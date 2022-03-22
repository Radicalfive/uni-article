<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	@import './common/icon.css';
	@import './common/base.css';
	@import './common/style.css';
	
	page {
		background: #f6f6f6;
		height: 100%;
	}
	
	::-webkit-scrollbar {
		display: none;
	}
</style>
