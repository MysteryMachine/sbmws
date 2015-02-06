(ns scully.alien
  (:use arcadia.core 
        scully.enemy
        scully.collidable)
  (:import [UnityEngine Debug Transform GameObject]))

(declare update
         awake
         collide)

(defcomponent Alien [^int health]
  (Awake [this] (awake this)) 
  (Update [this] (update this))
  (OnTriggerEnter2D [this collider] (collide this collider)))

(defn awake [this] this)
(defn update [this] this)
(defn collide [this collider] (enemy-take-damage this collider))