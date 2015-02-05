(ns scully.background
  (:use arcadia.core)
  (:import [UnityEngine]))

(def sprite-size 19.19)
(def end-x (- (* 1 sprite-size)))
(def start-x (* 1 sprite-size))
(def move-rate (/ sprite-size (* 60 5)))

(defn new-pos [this new-x]
  (set! (.. this transform position) (Vector3. new-x -1.75 3)))

(defn update [this]
  (let [old-x (.. this transform position x)
        new-x (if (> old-x end-x) 
                  (- old-x move-rate) 
                  start-x)]
    (set! (.. this transform position) (new-pos this new-x))))

(defcomponent Background [^Boolean firstSprite] 
  (Awake [this] 
         (if firstSprite
           (set! (.. this transform position) (new-pos this 0))
           (set! (.. this transform position) (new-pos this sprite-size))))
  (Update [this] (update this)))