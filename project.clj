(defproject com.ewen.flapjax-cljs "1.0.1-SNAPSHOT"
  :description "Utilities to use flapjax from clojurescript"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :test-paths ["test" "test-server"]
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/clojurescript "0.0-1586"]
                 [com.cemerick/piggieback "0.0.4" :scope "test"]
                 [domina "1.0.1" :scope "test"]
                 [compojure "1.1.3" :scope "test"]
                 [ring-serve "0.1.2" :scope "test"]
                 [ring/ring-devel "1.1.6" :scope "test"]
                 [ring/ring-core "1.1.6" :scope "test"]
                 [enlive "1.0.1" :scope "test"]
                 [enfocus "1.0.1-SNAPSHOT" :scope "test"]
                 [com.cemerick/clojurescript.test "0.0.1" :scope "test"]
                 [com.ewen.utils-cljs "1.0.0-RELEASE" :scope "test"]]
  :dev-dependencies [[lein-cljsbuild "0.3.0"]
                     [lein-marginalia "0.7.1"]]
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-marginalia "0.7.1"]
            [lein-deps-tree "0.1.2"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src-cljs" "browser-repl" "test"]
                        :compiler {:output-to "test-resources/js/cljs.js"
                                   :optimizations :simple
                                   :pretty-print true}}
                       {:id "advanced"
                        :source-paths ["src-cljs"]
                        :jar true
                        :compiler {:output-to "test-resources/js/cljs.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]}
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]})