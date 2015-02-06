(ns scully.alien
  (:use arcadia.core 
        scully.enemy
        scully.collidable)
  (:import [UnityEngine Debug Transform GameObject]))

(declare update
         awake
         collide)

(defcomponent Alien [^int health ^float t]
  (Awake [this] (awake this)) 
  (Update [this] (update this))
  (OnTriggerEnter2D [this collider] (collide this collider)))

(defn awake [this] this)
(defn update [this]
  (let [t (.t this)
        new-t (if (> t (float 36000)) (float 0) (+ (float 1) t))
        radt (/ t Math/PI 2 10)
        x (* 2 (Math/Sin radt))
        y (Math/Sin (* 2 radt))
        transform (.GetComponent this "Transform")
        z (.z (.localPosition transform))
        v (Vector3. (float x) (float y) (float z))]
    (do
      (set! (.localPosition transform) v)
      (set! (.t this) (float new-t)))))

(defn collide [this collider] (enemy-take-damage this collider))
