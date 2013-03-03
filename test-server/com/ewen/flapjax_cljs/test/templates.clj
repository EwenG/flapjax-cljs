(ns com.ewen.cle-usb-cljs.test.templates
  (:require [net.cgrand.enlive-html :as enlive]
            [hiccup.core :refer [html]]
            [hiccup.element :refer [javascript-tag]])
  (:import [java.io.File]))

#_(enlive/deftemplate index-page (java.io.File. "resources/public/bootstrap.html")
  []
  [:head]  (enlive/append (apply enlive/html-snippet (html (include-js "js/dev.js"))))) 

(defn index-page [in]
  (apply str (enlive/emit* 
              (enlive/at (enlive/html-resource in) [:head]  
                         (enlive/append 
                          (apply enlive/html-snippet 
                                 (html (javascript-tag 
                                        "com.ewen.cle_usb_cljs.utils.add_load_event.call(null, function() {com.ewen.cle_usb_cljs.dev.connect_repl.call(null);});"
                                        ))))))))














