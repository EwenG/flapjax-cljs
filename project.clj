(defproject ewen/flapjax-cljs "1.0.1"
  :description "Utilities to use flapjax from clojurescript"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :test-paths ["test"]
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2156"]
                 [com.cemerick/clojurescript.test "0.2.2" :scope "test"]]
  :profiles {:dev {:dependencies [[domina "1.0.2"]
                                  [enfocus "1.0.1"]]}}
  :plugins [[lein-cljsbuild "1.0.2"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src-cljs" "test"]
                        :compiler {:output-to "test-resources/cljs/flapjax-cljs.js"
                                   :output-dir "test-resources/cljs/"
                                   :optimizations :none
                                   :source-map true}}
                       {:id "advanced"
                        :source-paths ["src-cljs"]
                        :jar true
                        :compiler {:output-to "test-resources/cljs/flapjax-cljs.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]})
