const app = Vue.createApp({
    data() {
        return {
            month: '-',
            students: [
                {id: 1, name: 'Albert', month: 'November'},
                {id: 2, name: 'Rodianne', month: 'December'},
                {id: 3, name: 'Jade', month: 'February'},
                {id: 4, name: 'Aden', month: 'April'},
            ]
        }
    },
    methods: {
        changeMonth(month) {
            this.month = month
        }
    }
})
