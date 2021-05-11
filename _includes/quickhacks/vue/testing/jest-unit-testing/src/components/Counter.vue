<template>
  <div class="counter">
    <h1>{{ caption }}</h1>
    <div id="counter" class="value">{{ counter }}</div>
    <button id="decrement" @click="decrementIfGreaterThan0" class="rainbow">Decrement</button>
    <button id="increment" @click="increment" class="rainbow">Increment</button>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "Counter",
  props: {
    caption: String,
  },
  data() {
    return {
      counter: 0,
    };
  },
  methods: {
    increment() {
      this.counter++;
    },

    decrementIfGreaterThan0() {
      if (this.counter > 0) {
        this.counter--;
      }
    },
  },
});
</script>

<style scoped>
.value {
  font-size: 500%;
}

.rainbow {
  background-color: #343a40;
  border-radius: 4px;
  color: #fff;
  cursor: pointer;
  padding: 8px 16px;
}

.rainbow:hover {
  background-image: linear-gradient(90deg, #00c0ff 0%, #ffcf00 49%, #fc4f4f 80%, #00c0ff 100%);
  animation: slidebg 2s linear infinite;
}

@keyframes slidebg {
  to {
    background-position: 20vw;
  }
}
</style>
