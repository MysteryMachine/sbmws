(ns scully.midground
  (:use arcadia.core)
  (:import [UnityEngine]))

(def end-x -21.1)
(def start-x 21.1)
(def move-rate 0.1)

(defn update [this]
  (let [old-x (.. this transform position x)
        new-x (if (> old-x end-x) 
                  (- old-x move-rate) 
                  start-x)]
    (set! (.. this transform position) (Vector3. new-x -1.75 2))))

(defcomponent 
  Midground [] (Update [this] (update this)))