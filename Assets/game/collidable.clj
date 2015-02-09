(ns game.collidable
  (:use arcadia.core
        game.core)
  (:import [UnityEngine Debug Transform GameObject]))

(defcomponent Collidable [^Keyword type])

(defn collider-type [obj] 
  (if-component "Collidable" obj .type))

(defn has-collider-type [collider type]
  (= type (collider-type (.gameObject collider))))

(defn make-collidable [obj col-type]
  (do
    (add-component (.gameObject obj) game.collidable.Collidable)
    (set! (.. obj (GetComponent "Collidable") type) col-type)
    obj))