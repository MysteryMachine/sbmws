(ns scully.background
  (:use arcadia.core)
  (:import [UnityEngine]))

(def end-x -16.25)
(def start-x 16.25)
(def move-rate 0.05)

(defn update [this]
  (let [old-x (.. this transform position x)
        new-x (if (> old-x end-x) 
                  (- old-x move-rate) 
                  start-x)]
    (set! (.. this transform position) (Vector3. new-x -1.75 3))))

(defcomponent
  Background [] (Update [this] (update this)))