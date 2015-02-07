(ns game.alien
  (:use arcadia.core
        hard.core 
        game.enemy
        game.collidable)
  (:import [UnityEngine Debug Transform GameObject]))

(declare c-update
         c-awake
         c-collide)

(defcomponent Alien [^int health ^int t]
  (Awake [this] (c-awake this)) 
  (Update [this] (c-update this))
  (OnTriggerEnter2D [this collider] (c-collide this collider)))

(defn- c-awake [this] this)
(defn- c-update [this]
  (let [t (.t this)
        new-t (if (> t 36000) 0 (+ 1 t))
        radt (/ t Math/PI 2 10)
        x (* 2 (Math/Sin radt))
        y (Math/Sin (* 2 radt))
        z (.. this transform localPosition)
        v (Vector3. x y x)]
    (do
      (set! (.. this transform localPosition) v)
      (set! (.t this) (int new-t)))))

(defn c-collide [this collider] (enemy-take-damage this collider))
