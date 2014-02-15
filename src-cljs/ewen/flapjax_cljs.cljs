(ns ewen.flapjax-cljs
  (:require [goog.dom :as dom]
            [clojure.string :refer [upper-case]]
            [F]
            [F.dom_]
            [F.Behavior])
  (:require-macros [ewen.flapjax-cljs-macros :refer [build-fns]]))


;; [ "a", "b", "blockquote", "br", "button", "canvas", "div", "fieldset",
;;   "form", "font", "h1", "h2", "h3", "h4", "hr", "iframe", "input",
;;   "label", "legend", "li", "ol", "optgroup", "option",
;;   "p", "select", "span", "strong", "table", "tbody",
;;   "td", "textarea", "tfoot", "th", "thead", "tr", "tt", "ul" ].forEach(function (name) {
;;                                                                                         window[name.toUpperCase()] = F.dom_.makeTagB(name) ;
;;                                                                                         }) ;


;The above javascript code has been moved from the flapjax library and put here to make it compatible
;with the advanced compilation mode of the google closure compiler.


(build-fns F.dom_/makeTagB ["a" "b" "blockquote" "br" "button" "canvas" "div" "fieldset"
              "form" "font" "h1" "h2" "h3" "h4" "hr" "iframe" "input"
              "label" "legend" "li" "ol" "optgroup" "option"
              "p" "select" "span" "strong" "table" "tbody"
              "td" "textarea" "tfoot" "th" "thead" "tr" "tt" "ul"])




;;Wrap the flapjax functions

(declare receiverE)

(def EventStream F/EventStream)
(def Behavior F/Behavior)

;;;;;;;;;;;;;;;;;;;;;;;;;;; UTILITY FUNCTIONS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn startsWith
[streamE v]
(.startsWith streamE v))

(defn changes
[sourceB]
(.changes sourceB))

;;;;;;;;;;;;;;;;;;;;;;;;; EVENT STREAM FUNCTIONS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def oneE F/oneE)
(def zeroE F/zeroE)
(def mapE F/mapE)
(def mergeE F/mergeE)

(defn switchE
[streamE]
(.switchE streamE))

(defn filterE
[pred streamE]
(.filterE streamE pred))

(defn constantE
[streamE v]
(.constantE streamE v))

(defn collectE
[streamE init f]
(.collectE streamE init f))

(defn notE
[streamE]
(.notE streamE))

(defn filterRepeatsE
[streamE]
(.filterRepeatsE streamE))

(def receiverE F/receiverE)
(def sendEvent F/sendEvent)

(defn snapshotE
[streamE valueB]
(.snapshotE streamE valueB))

(defn onceE
[streamE]
(.onceE streamE))

(defn skipFirstE
[streamE]
(.skipFirstE streamE))

(defn delayE
[streamE intervalB]
(.delayE streamE intervalB))

(defn blindE
[streamE intervalB]
(.blindE streamE intervalB))

(defn calmE
[streamE intervalB]
(.calmE streamE intervalB))

(def timerE F/timerE)

(def extractEventE F/extractEventE)

(def extractValueE F/extractValueE)

(def insertValueE F/insertValueE)

(def clicksE F/clicksE)

;;;;;;;;;;;;;;;;;;;;;;;;;;; BEHAVIOR FUNCTIONS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def constantB F/constantB)

(defn delayB
[sourceB intervalB]
(.delayB sourceB intervalB))

(defn valueNow
[sourceB]
(.valueNow sourceB))

(defn switchB
[sourceBB]
(.switchB sourceBB))

(def andB F.Behavior/andB)
(def orB F.Behavior/orB)

(defn notB
[valueB]
(.notB valueB))

(def liftB F/liftB)
(def condB F/condB)

(defn ifB
[predicateB consequentB alternativeB]
(.ifB predicateB consequentB alternativeB))

(def timerB F/timerB)

(defn blindB
[sourceB intervalB]
(.blindB sourceB intervalB))

(defn calmB
[sourceB intervalB]
(.calmB sourceB intervalB))


















(defmulti extractValueB type)

(defmethod extractValueB :default [in-obj]
  (F/extractValueB in-obj))

;; Implement `extractValueB` for clojurescript Atoms. We want the function to be memoized,
;; but it seems `defmethod` cannot be memoized (maybe it can?).
;; Instead we modify the `extractValueB` javascript object to add the function.

(def extractValueB-Atom
  "Every modification to the targeted Atom is propagated to the `receiv` Behavior"
  (memoize
   (fn [atom-in]
     (let [receiv (receiverE)]
       (add-watch atom-in (keyword (gensym))
                  (fn [k r o n] (sendEvent receiv n)))
       (startsWith receiv @atom-in)))))

(swap! (.-method-table extractValueB)
       #(assoc % cljs.core/Atom
               extractValueB-Atom))


