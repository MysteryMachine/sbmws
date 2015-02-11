(ns game.enemy.alien
  (:use
    arcadia.core 
    game.core
    game.enemy)
  (:import [UnityEngine Debug Transform GameObject]))

(declare c-update c-awake c-collide)

(defcomponent Alien 
  [^int health
   ^float loopSize 
   ^float loops
   ^float dt 
   ^float t]
  
  (Awake [this] (c-awake this)) 
  (Update [this] (c-update this loopSize loops t dt))
  (OnTriggerEnter2D [this collider] (c-collide this collider)))

(def ^:private max-t (* 10000 2 Math/PI))
(defn- fx [t loopSize] (* loopSize (sin t)))
(defn- fy [t loops]    (sin (* loops t)))

(defn- c-awake [this] this)
(defn- c-update [this loopSize loops t dt]
  (let [v (vector3 (local-position this) 
                   { :x (fx t loopSize)
                     :y (fy t loops)})
        tf (if (> t max-t) 0 (+ dt t))]
    (do
      (set! (.. this transform localPosition) v)
      (set! (.t this) (float tf)))))

(defn- c-collide [this collider] (enemy-collide this collider))
