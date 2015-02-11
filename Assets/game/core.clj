(ns game.core
  (:use arcadia.core
        hard.core)
  (:import [UnityEngine Debug]))

(defn sin [t] (Math/Sin t))
(defn cos [t] (Math/Cos t))

(defn vector3 
  ([x y z] (Vector3. x y z))
  ([v changeset] 
  	(let [{ x :x y :y z :z } changeset
            dx (or x (.x v))
            dy (or y (.y v))
            dz (or z (.z v))]
       (vector3 dx dy dz))))

(defn v3+ [& more]
  (reduce #(Vector3/op_Addition %1 %2) more))

(defmacro position [obj]
  `(.position (.transform ~obj)))

(defmacro local-position [obj]
  `(.position (.transform ~obj)))

(defmacro if-component [comp-name obj fun]
  `(if-let [c# (.GetComponent ~obj ~comp-name)] (~fun c#)))


