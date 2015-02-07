(ns game.collidable
  (:use arcadia.core
        hard.core)
  (:import [UnityEngine Debug Transform GameObject]))

(defcomponent Collidable [^Keyword type])

(defn collider-type [obj]
  (let [collidable (.GetComponent obj "Collidable")]
    (if collidable
      	(.type collidable)
      	:none)))

(defn has-collider-type [collider type]
  (= type (collider-type (.gameObject collider))))