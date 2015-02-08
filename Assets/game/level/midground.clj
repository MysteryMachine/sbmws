(ns game.level.midground
  (:use arcadia.core
        game.level.core)
  (:import [UnityEngine]))

(def ^:private sprite-size 28.82)
(def ^:private x-max sprite-size)
(def ^:private x-min (- x-max))
(def ^:private dx (/ x-max (* 8000 5)))

(def ^:private c-awake (bg-awake-fn x-max x-min dx))
(def ^:private c-update (bg-awake-fn x-max x-min dx))

(defcomponent Foreground 
  [^Boolean firstSprite] 
  
  (Awake [this] (c-awake this firstSprite))
  (Update [this] (c-update this)))
