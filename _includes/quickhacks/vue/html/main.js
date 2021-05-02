const app = Vue.createApp({
    data() {
        return {
            message: "<strong>This is a <em>warning</em>!!</strong>",
        }
    },
    methods: {
        formattedMessage() {
            return "<strong>This is an <em>error</em>!!</strong>"
        }
    }
})
