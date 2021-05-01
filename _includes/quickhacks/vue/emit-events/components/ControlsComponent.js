app.component('controls-component', {
    template:
        `
        <button @click="increase(1)">Increase by 1</button>
        <button @click="increase(5)">Increase by 5</button>
        <button @click="decrease">Decrease</button>
        <button @click="reset">Reset</button>
        `,
    methods: {
        increase(by) {
            this.$emit("increase-counter", by)
        },
        decrease() {
            this.$emit("decrease-counter")
        },
        reset() {
            this.$emit("reset-counter")
        },
    },
})