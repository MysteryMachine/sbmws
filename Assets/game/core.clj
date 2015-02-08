(ns game.core
  (:use arcadia.core
        hard.core)
  (:import [UnityEngine Debug]))

(defmacro sin [t] `(Math/Sin ~t))
(defmacro cos [t] `(Math/Cos ~t))

(defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let [{ x :x y :y z :z } changeset
          dx (or x `(.x ~v))
          dy (or y `(.y ~v))
          dz (or z `(.z ~v))]
      `(vector3 ~dx ~dy ~dz))))

(defmacro position [obj]
  `(.position (.transform ~obj)))

(defmacro local-position [obj]
  `(.position (.transform ~obj)))

; (defmacro if-component [comp-name obj fun]
;   `(if-let [c# (.GetComponent ~obj ~comp-name)] (~fun c#)))

(defmacro if-component [comp-name obj fun]
  `(if-let [c# (.GetComponent ~obj ~comp-name)] (~fun c#)))

(defmacro component-init [obj type & bindings]
  `(let [strmap# (clojure.walk/stringify-keys (apply hash-map ~bindings))
        names# (keys strmap#)]
    names#))
