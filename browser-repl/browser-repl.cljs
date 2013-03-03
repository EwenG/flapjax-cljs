(ns browser-repl
  (:require [com.ewen.utils-cljs.utils :refer [add-load-event]]
            [com.ewen.utils-cljs.dev :refer [connect-repl]]))

(add-load-event connect-repl)

