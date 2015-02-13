(ns game.core
  (:use arcadia.core
        hard.core)
  (:import [UnityEngine Debug]))

(defmacro sin [t] `(Math/Sin ~t))
(defmacro cos [t] `(Math/Cos ~t))

(defmacro vector3  [x y z] `(Vector3. ~x ~y ~z))
(defmacro v3update [v & changeset] 
  `(let [v# ~v
         { x# :x y# :y z# :z } ~(apply hash-map changeset)
          dx# (or x# (.x v#) 0)
          dy# (or y# (.y v#) 0)
          dz# (or z# (.z v#) 0)]
     (Vector3. dx# dy# dz#)))

(defn v3+ [& more]
  (reduce #(Vector3/op_Addition %1 %2) more))

(defn v3- [& more]
  (reduce #(Vector3/op_Subtraction %1 %2) more))

(defmacro position [obj]
  `(.position (.transform ~obj)))

(defmacro local-position [obj]
  `(.position (.transform ~obj)))

(defmacro if-component [comp-name obj fun]
  `(if-let [c# (.GetComponent ~obj ~comp-name)] (~fun c#)))


; Yoink -> https://gist.github.com/nasser/de0ddaead927dfa5261b
(defmacro chance [& body]
  (let [r (gensym "chance")
        pairs (sort-by first (partition 2 body))
        odds (map first pairs)
        exprs (map last pairs)
        sum (apply + odds)
        fracs (map #(float (/ % sum)) odds)
        frac-pairs (partition 2 (interleave fracs exprs))]
    `(let [~r (rand)]
       (cond
         ~@(apply concat
                  (reduce
                    (fn [acc [odds expr]]
                      (let [odd-sum (if (seq acc)
                                      (-> acc last first last)
                                      0)]
                        (conj acc [`(< ~r ~(+ odd-sum odds))
                                   expr])))
                    []
                    (drop-last frac-pairs)))
         :else
         ~(-> frac-pairs last last)))))


