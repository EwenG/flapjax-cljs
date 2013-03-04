flapjax-cljs
============

A library to improve the experience of using [flapjax](http://www.flapjax-lang.org/) from clojurescript.

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

* Several flapjax functions are defined on prototypes. Flapjax-cljs wraps the flapjax functions in the `com.ewen.flapjax-cljs` namespace to make them plain functions. The original flapjax functions are still available in the `F` namespace.

* Extends the `extractValueB` function to work on [clojurescript atoms](http://clojure.org/atoms).

* Helps to manipulate flapjax objects with the [enfocus templating library](https://github.com/ckirkendall/enfocus). The `com.ewen.flapjax-cljs-macros.with-B` macro can be used to transform enfocus snippets into snippets that accept flapjax behavior objects as parameters. The snippet is automatically updated when the behaviors change.

Example
=======

```clojure
(def test-atom (atom {:a "a" :b "b"}))
(def atom-B (F-cljs/extractValueB test-atom))
(F-cljs/valueNow atom-B) ; => {:a "a" :b "b"}
(with-B
    (em/defsnippet test-snippet :compiled "test-resources/test.html" ["p"] [val1]
      ["p"] (em/content (:a val1))))
(.log js/console (test-snippet atom-B)) ; => <p>a</p>
(swap! test-atom #(assoc % :a "new-a"))
(F-cljs/valueNow atom-B) ; => {:a "new-a" :b "b"}
(.log js/console (test-snippet atom-B)) ; => <p>new-a</p>
```