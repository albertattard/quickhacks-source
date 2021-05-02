---
layout: default
title: Create and Mount Vue Application
parent: Vue
nav_order: 3.3
permalink: docs/vue/create-app/
---

# Create and Mount Vue Application

There are two approaches

1. Create and mount the application from within the JavaScript
1. Create the application from within the JavaScript and mount it from within the HTML

Both are valid approaches. The first one is more concise while the second one promotes reuse as the same script can be
mounted to any element with needing to know its id.

## Create and mount the application from within the JavaScript

### File: `a/index.html`

{% highlight html %}
{% raw_include quickhacks/vue/create-app/a/index.html %}
{% endhighlight %}

### File: `a/main.js`

{% highlight js %}
{% raw_include quickhacks/vue/create-app/a/main.js %}
{% endhighlight %}

## Create the application from within the JavaScript and mount it from within the HTML

### File: `b/index.html`

{% highlight html %}
{% raw_include quickhacks/vue/create-app/b/index.html %}
{% endhighlight %}

### File: `b/main.js`

{% highlight js %}
{% raw_include quickhacks/vue/create-app/b/main.js %}
{% endhighlight %}
