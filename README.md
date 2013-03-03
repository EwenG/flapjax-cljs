flapjax-cljs
============

A library to make it easier to use flapjax from clojurescript.

Usage
=====

Simply add flapjax-cljs as a dependency in you project.clj :
```clojure
[com.ewen.flapjax-cljs "1.0.0-RELEASE"]
```

Features
========

* Wraps the flapjax library in order to avoid the need to include flapjax in your `:libs` or `:extern` section when using cljsbuild.

* Makes flapjax compatible with the google-closure compiler `:advanced` compilation mode.

* Several flapjax functions are defined on prototypes. Flapjax-cljs wraps the flapjax functions in the `com.ewen.flapjax-cljs` namespace to make them plain functions.

* Extends the `extractValueB` functions to work on [clojurescript atoms](http://clojure.org/atoms).

* When using flapjax and and the [enfocus templating library](https://github.com/ckirkendall/enfocus) together, the `com.ewen.flapjax-cljs-macros.with-B` macro can be used to transform enfocus snippets into snippets that accept flapjax behavior object as parameter.