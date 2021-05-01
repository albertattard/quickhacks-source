const app = Vue.createApp({
    data() {
        return {
            clicks: 0
        }
    },
    methods: {
        increment(by) {
            this.clicks += Math.abs(by)
        },
        decrementIfGreaterThanZero() {
            if (this.clicks > 0) {
                this.clicks -= 1
            }
        }
    }
})
