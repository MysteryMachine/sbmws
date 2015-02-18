(ns game.over
  (:use arcadia.core)
  (:import [UnityEngine Debug Transform 
            Input GameObject Application]))

(declare c-update)

(defcomponent GameOver [] (Update [this] (c-update this)))

(defn- c-update [this] 
  (if (Input/anyKeyDown) (Application/LoadLevel "Menu")))