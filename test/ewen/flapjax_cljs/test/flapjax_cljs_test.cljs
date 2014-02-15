(ns ewen.flapjax-cljs.test.flapjax-cljs-test
  (:require [cemerick.cljs.test :as t]
            [ewen.flapjax-cljs :as F-cljs]
            [domina :refer [append! html-to-dom]]
            [enfocus.core :as ef]
            [goog.dom])
  (:require-macros [cemerick.cljs.test :refer [is deftest run-tests]]
                   [enfocus.macros :as em]
                   [ewen.flapjax-cljs-macros :refer [with-B]]))


(enable-console-print!)

(defn get-test-B []
  (let [test-E (F-cljs/receiverE)
        test-B (F-cljs/startsWith test-E nil)]
    [test-E test-B]))



(with-B
    (em/defsnippet test-snippet :compiled "test-resources/test.html" ["p"] [val1 val2]
      ["p"] (em/content (str val1 " " val2))))

(deftest Snippet-works-with-normal-values
  (is (= js/HTMLParagraphElement (type (test-snippet "val1" "val2"))))
  (is (= "val1 val2" (-> (test-snippet "val1" "val2") (.-firstChild) (.-data)))))

(deftest Snippet-works-with-behaviors
  (let [[test-E-1 test-B-1] (get-test-B)
        [test-E-2 test-B-2] (get-test-B)]
    (F-cljs/sendEvent test-E-1 "val1")
    (F-cljs/sendEvent test-E-2 "val2")
    (is (= js/HTMLParagraphElement (type (test-snippet test-B-1 test-B-2))))
    (is (= "val1 val2" (-> (test-snippet test-B-1 test-B-2) (.-firstChild) (.-data))))
    (F-cljs/sendEvent test-E-1 "val3")
    (is (= js/HTMLParagraphElement (type (test-snippet test-B-1 test-B-2))))
    (is (= "val3 val2" (-> (test-snippet test-B-1 test-B-2) (.-firstChild) (.-data))))))








#_(t/test-ns 'ewen.flapjax-cljs.test.flapjax-cljs-test)




#_(:comment






(def test-atom (atom {:a "a" :b "b"}))
(def atom-B (F-cljs/extractValueB test-atom))
(F-cljs/valueNow atom-B)
(with-B
    (em/defsnippet test-snippet :compiled "test-resources/test.html" ["p"] [val1]
      ["p"] (em/content (:a val1))))
(.log js/console (test-snippet atom-B))
(swap! test-atom #(assoc % :a "new-a"))
(F-cljs/valueNow atom-B)
(.log js/console (test-snippet atom-B))




 )
