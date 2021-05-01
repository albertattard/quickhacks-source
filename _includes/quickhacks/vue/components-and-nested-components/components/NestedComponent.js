app.component('nested-component', {
    props: {
        ingredients: {
            type: Array,
            required: true
        }
    },
    template:
        `
        <h2>Nested Component</h2>
        <ul>
            <li v-for="ingredient in ingredients">{{ ingredient }}</li>
        </ul>
        `
})