const app = Vue.createApp({
    data() {
        return {
            name: "Albert",
            surname: "Attard",
            linkedInLink: "https://www.linkedin.com/in/albertattard/",
            gitHubLink: "https://github.com/albertattard/",
        }
    },
    methods: {
        /* Using older syntax */
        fullName: function () {
            return this.name + " " + this.surname
        },

        /* Taking advantage from ES6 syntax */
        fullNameLastNameUpperCase() {
            return this.name + " " + this.surname.toUpperCase()
        }
    }
})
