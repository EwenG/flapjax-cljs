(ns com.ewen.flapjax-cljs-macros
  (:require [clojure.string :refer [upper-case]]))


;Build a javascript symbol (in upper-case) using the string provided
(defn- build-js-symb [symb]
  (->> symb (upper-case) (str "js/") (symbol)))

;Build javascript functions with names in upper-case and equal to the strings provided.
(defmacro build-fns [fn-def fn-names]
  (conj (map #(list 'set! (build-js-symb %) (list fn-def %)) fn-names) 'do))







(defn- ensure-mode
  " 'mode' argument is not required and defaults to :remote, for
backward compatability"
  [[mode & _ :as body]]
  (if (keyword? mode)
    body
    (cons :remote body)))

;This macro can be used to wrap a enfocus `defsnippet` expression.
;A snippet created this way can take flapjax Behaviors as parameters.
;The resulting DOM node will be automatically updated when the Behaviors change.
(defmacro with-B [[defs sym & body]] ;sym is the user choosen name for the snippet
  (let [[mode uri sel args & forms] (ensure-mode body)
        s-name (gensym)] ;s-name is a temporary name for the snippet
    `(let [snippet-B-builder# ;snippet-B-builder# takes the original snippet as input and returns a new snippet that accepts Behaviors as input parameter.
           (fn [func#]
             (fn ~args ;args are the parameter provided by the user when using the snippet
               (let [get-root-elt-B# (fn [valB#] ;get-root-elt-B calls the original snippet on its arguments using the liftB function : (liftB original-snippet args)
                                       (->> (comp #(.-firstChild %) func#) 
                                            (conj valB#) 
                                            (apply com.ewen.flapjax-cljs/liftB)))
                     root-elt# (-> (list ~@args) 
                                   (get-root-elt-B#) 
                                   (com.ewen.flapjax-cljs/valueNow))
                     update-fn# (fn [new-val#] ;The function that updates the returned DOM element when one of the provided Behaviors changes.
                                  (do (goog.dom/removeChildren root-elt#)
                                      (dorun 
                                       (map 
                                        #(goog.dom/appendChild root-elt# (.cloneNode % true)) 
                                        (.-childNodes new-val#)))))]
                 (com.ewen.flapjax-cljs/liftB update-fn# (get-root-elt-B# (list ~@args)))
                 root-elt#)))]
       (~defs ~s-name ~@body) ;Defines the original snippet
       (def ~sym (snippet-B-builder# ~s-name)) ;Defines the new-snippet (the one that can take Behaviors as arguments)
       (set! ~s-name nil))));Undefines the original snippet. Whe don't need it anymore

;Closurescript function used in the `with-B` macro. Maybe it is easier to understand using this form.
#_(defn- makeFnB [funct]
  (fn [& valB]
    (let [get-root-elt-B (fn [valB] (->> (comp #(.-firstChild %) funct) (conj valB) (apply liftB)))
          root-elt (-> valB (get-root-elt-B) (valueNow))
          update-fn (fn [new-val]
                      (do (dom/removeChildren root-elt)
                          (dorun 
                           (map 
                            #(dom/appendChild root-elt (.cloneNode % true)) 
                            (.-childNodes new-val)))))]
      (liftB update-fn (get-root-elt-B valB))
      root-elt)))
