const app = Vue.createApp({
    data() {
        return {
            name: "",
            surname: "",
            fullName: "",
        }
    },
    methods: {
        setName(event) {
            this.name = event.target.value
            this.updateFullName();
        },
        setSurname(event) {
            this.surname = event.target.value
            this.updateFullName();
        },
        updateFullName() {
            this.fullName = this.name + " " + this.surname.toUpperCase()
        },
    }
})
