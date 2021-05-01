app.component('outer-component', {
    props: {
        speciality: {
            type: Boolean,
            required: true
        }
    },
    template:
        `
        <h1>Component</h1>
        <p>{{ food }}<span v-if="speciality"> (Speciality)</span></p>
        <nested-component :ingredients="ingredients"></nested-component>
        `,
    data() {
        return {
            food: 'Pizza',
            ingredients: ['Cheese', 'Pizza Dough', 'Mozzarella'],
        }
    },
})