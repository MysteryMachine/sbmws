(ns game.level.background
  (:use arcadia.core
        game.level.core)
  (:import [UnityEngine]))

(def ^:private sprite-size 19.19)
(def ^:private x-max sprite-size)
(def ^:private x-min (- x-max))
(def ^:private dx (/ x-max (* 60 5)))

(def ^:private c-awake (bg-awake-fn x-max x-min dx))
(def ^:private c-update (bg-awake-fn x-max x-min dx))

(defcomponent Background 
  [^Boolean firstSprite] 
  
  (Awake [this] (c-awake this firstSprite))
  (Update [this] (c-update this)))