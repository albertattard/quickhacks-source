---
layout: default
title: Jest Unit Testing
parent: Testing (Vue)
grand_parent: Vue
nav_order: 10
permalink: docs/vue/testing/jest-unit-testing/
---

# Jest Unit Testing

A simple application that has a counter that can be incremented and decremented as required. The counter cannot be
negative and cannot be decremented further once it reaches `0`.

![Counter]({{ "/assets/images/vue/testing/jest-unit-testing.png" | absolute_url }})

## File: `tests/unit/counter.spec.ts`

{% highlight typescript %}
{% raw_include quickhacks/vue/testing/jest-unit-testing/tests/unit/counter.spec.ts %}
{% endhighlight %}

## File: `src/components/Counter.vue`

{% highlight vue %}
{% raw_include quickhacks/vue/testing/jest-unit-testing/src/components/Counter.vue %}
{% endhighlight %}

## File: `package.json`

{% highlight json %}
{% raw_include quickhacks/vue/testing/jest-unit-testing/package.json %}
{% endhighlight %}

## Version

- npm

  ```console
  $ npm --version
  7.11.2
  ```

- vue-cli

  ```console
  $ vue --version
  @vue/cli 4.5.12
  ```
