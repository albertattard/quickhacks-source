import { shallowMount } from "@vue/test-utils";
import Counter from "@/components/Counter.vue";

describe("Counter.vue", () => {
  it("renders a counter with the given caption", () => {
    const caption = "The new shining counter";
    const wrapper = shallowMount(Counter, {
      props: { caption },
    });

    expect(wrapper.find("h1").text()).toMatch(caption);
  });

  it("starts the counter with the count of 0", () => {
    const wrapper = shallowMount(Counter);

    const expected = 0;
    /* Check the value on the page (my preferred way of asserting the value as it covers both the state and the UI, but
       it is more brittle as changes to the UI, such as the component ID, may break this test) */
    expect(wrapper.find("#counter").text()).toBe(expected.toString());
    /* Check the value in the state */
    expect(wrapper.vm.$data.counter).toBe(expected);
  });

  it("increment the counter when the increment button is clicked", async () => {
    const wrapper = shallowMount(Counter);

    await wrapper.find("#increment").trigger("click");
    expect(wrapper.find("#counter").text()).toBe("1");
  });

  it("does not decrement the counter when its value is 0 and the decrement button is clicked", async () => {
    const wrapper = shallowMount(Counter);

    await wrapper.find("#decrement").trigger("click");
    expect(wrapper.find("#counter").text()).toBe("0");
  });

  it("decrement the counter when its value is greater than 0 and the decrement button is clicked", async () => {
    const wrapper = shallowMount(Counter);
    wrapper.vm.$data.counter = 10;

    await wrapper.find("#decrement").trigger("click");
    expect(wrapper.find("#counter").text()).toBe("9");
  });
});
