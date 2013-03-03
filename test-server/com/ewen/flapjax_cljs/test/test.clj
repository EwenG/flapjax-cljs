(ns com.ewen.flapjax-cljs.test.test
  (:require [com.ewen.flapjax-cljs.test.handler :as handler-test]
            [ring.util.serve :refer [serve-headless stop-server]]
            [cljs.repl.browser]))

(serve-headless handler-test/app)

(stop-server)

;Starts the browser connected REPL
(cemerick.piggieback/cljs-repl
  :repl-env (doto (cljs.repl.browser/repl-env :port 9000)
              cljs.repl/-setup))