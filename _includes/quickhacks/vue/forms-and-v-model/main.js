const app = Vue.createApp({
    data() {
        return {
            name: "",
            rating: 5,
            publish: true,
            warning: {show: false}
        }
    },
    methods: {
        reset() {
            this.name = ""
            this.rating = 5
            this.publish = true
            this.warning = {show: false}

        },

        submitReview() {
            if (this.name === '') {
                this.warning = {show: true, message: "Please fill the name before submitting the review"}
                return
            }

            const review = {
                name: this.name,
                rating: this.rating,
                publish: this.publish
            }

            /* The object can be emitted instead of simply logged */
            // this.$emit('review-submitted', review)
            console.log("Review", review)
            this.reset()
        },
    }
})
