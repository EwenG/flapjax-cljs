(ns com.ewen.flapjax-cljs.test.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.file :refer [wrap-file]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.reload :refer [wrap-reload]]
            [hiccup.core :refer [html]]
            [hiccup.element :refer [javascript-tag]]
            [hiccup.page :refer [include-js]])
  (:import [java.io.File]))


(defroutes app-routes
  (GET "/" [] (html [:html [:head  (include-js "js/cljs.js")] [:body]]))
  (route/files "/" {:root "test-resources"})
  (route/not-found "Not Found"))

(def app
  (-> app-routes (handler/site)))

#_(.. Thread (currentThread) (getContextClassLoader) (getResourceAsStream "public/index.html"))