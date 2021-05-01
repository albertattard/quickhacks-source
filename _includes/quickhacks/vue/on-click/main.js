const app = Vue.createApp({
    data() {
        return {
            clicks: 0
        }
    },
    methods: {
        decrementIfGreaterThanZero() {
            if (this.clicks > 0) {
                this.clicks -= 1
            }
        }
    }
})
