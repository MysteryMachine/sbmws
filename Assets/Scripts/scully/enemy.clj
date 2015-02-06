(ns scully.enemy
  (:use arcadia.core scully.collidable)
  (:import [UnityEngine Debug Transform GameObject Vector3]))

(declare enemy-update
         enemy-awake)

(defcomponent Enemy [^Vector3 speed]
  (Awake [this] (enemy-awake this)) 
  (Update [this] (enemy-update this)))

(defn enemy-awake [this] 
  (doseq [transform (rest (.GetComponentsInChildren (.gameObject this) Transform))]
  	(add-component (.gameObject transform) scully.collidable.Collidable)
  	(set! (.. (.gameObject transform) (GetComponent "Collidable") type) :enemy)))

(defn enemy-update [this] 
  (let [transform (.. this (GetComponent "Transform"))
        new-vec (Vector3/op_Addition (.position transform) (.speed this))]
    (if (> (.x new-vec) (float -13.0)) 
      (set! (.position transform) new-vec)
      (GameObject/Destroy (.gameObject this)))))

(defn enemy-take-damage [this collider]
  (if (has-collider-type collider :player-bullet)
    (do
      (let [bullet (.. collider gameObject (GetComponent "Bullet"))
            dmg (.damage bullet)  
            new-health (- (.health this) dmg)]
        (if (<= new-health 0)
            (GameObject/Destroy (.gameObject this))
            (set! (.health this) new-health)))
      (GameObject/Destroy (.gameObject collider)))))
