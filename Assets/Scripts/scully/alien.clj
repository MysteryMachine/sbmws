(ns scully.alien
  (:use arcadia.core)
  (:import [UnityEngine Debug Transform GameObject]))

(declare update
         awake)

(defcomponent Alien []
  (Awake [this] (awake this)) 
  (Update [this] (update this)))

(defn awake [this] this)
(defn update [this] this)