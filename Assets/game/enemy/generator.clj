(ns game.enemy.generator
  (:use
    arcadia.core 
    game.core
    game.enemy)
  (:import [UnityEngine Debug Transform GameObject Resources]))


(defn- spawn-enemy [this y] 
  (instantiate (Resources/Load 
                 (chance 100 "StdAlien"))
               (vector3 9 y 0)))

(defn- c-update [this t generator-wait y-min y-max level level-inc-rate min-rate]
  (let [wait-try (- generator-wait (* level-inc-rate level))
        wait (if (> wait-try min-rate) wait-try min-rate)
        pass (/ generator-wait (+ (/ t 60) generator-wait 1))
        gen (> (rand) pass)
        y (+ (* (rand) (- y-max y-min)) y-min)]
    (if gen (do 
              (spawn-enemy this y)
              (set! (.t this) (int 0)))
            (set! (.t this) (int (+ 1 t))))))


(defcomponent EnemyGenerator 
  [^int generatorWait
   ^int t
   ^int level
   ^int levelIncRate
   ^int minRate
   ^float yMin
   ^float yMax]
  (FixedUpdate [this] (c-update this t generatorWait yMin yMax level levelIncRate minRate)))
