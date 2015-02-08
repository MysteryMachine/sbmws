(ns game.collidable
  (:use arcadia.core
        game.core)
  (:import [UnityEngine Debug Transform GameObject]))

(defcomponent Collidable [^Keyword type])

(defn add-collider! [this type]
  (add-component (.gameObject this) game.collidable.Collidable)
  (set! (.. this (GetComponent "Collidable") type) type)
  
  ;(component-init this game.collidable.Collidable
                  ;:type type)
)

(defn collider-type [obj] 
  (if-component "Collidable" obj .type))

(defn has-collider-type [collider type]
  (= type (collider-type (.gameObject collider))))