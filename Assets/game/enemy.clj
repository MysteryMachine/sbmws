(ns game.enemy
  (:use arcadia.core 
        game.core
        game.collidable)
  (:import [UnityEngine Debug Transform GameObject Vector3]))

(declare c-update c-awake)

(defcomponent Enemy 
  [^Vector3 speed]
  
  (Awake [this] (c-awake this)) 
  (Update [this] (c-update this)))

(defn- c-awake [this] 
  (doseq 
    [transform (rest (.GetComponentsInChildren (.gameObject this) Transform))]
  	(add-component (.gameObject transform) game.collidable.Collidable)
  	(set! (.. (.gameObject transform) (GetComponent "Collidable") type) :enemy)))

(defn- c-update [this] 
  (let [transform (.. this (GetComponent "Transform"))
        new-vec (v3+ (.position transform) (.speed this))]
    (if (> (.x new-vec) -13) 
      (set! (.position transform) new-vec)
      (GameObject/Destroy (.gameObject this)))))

(defn- enemy-take-damage [this collider]
  (let [bullet (.. collider gameObject (GetComponent "Bullet"))
        dmg (.damage bullet)  
        new-health (- (.health this) dmg)]
    (do
      (if (<= new-health 0) 
        (GameObject/Destroy (.gameObject this))
        (set! (.health this) (int new-health)))
      (GameObject/Destroy (.gameObject collider)))))

(defn enemy-collide [this collider]
  (if (has-collider-type collider :player-bullet)
      (enemy-take-damage this collider)))
