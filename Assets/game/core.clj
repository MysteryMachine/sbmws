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

(defmacro position [obj]
  `(.position (.transform ~obj)))

(defmacro local-position [obj]
  `(.position (.transform ~obj)))

(defmacro if-component [comp-name obj fun]
  `(if-let [c# (.GetComponent ~obj ~comp-name)] (~fun c#)))


