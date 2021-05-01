const app = Vue.createApp({
    data() {
        return {
            name: 'Albert',
            surname: 'Attard',
        }
    },
    computed: {
        fullName() {
            return this.name + " " + this.surname;
        }
    }
})
