const app = Vue.createApp({
    data() {
        return {
            name: ''
        }
    },
    methods: {
        /* Uses the event created by the browser to get what the user typed */
        setName(event) {
            this.name = event.target.value
        }
    }
})
