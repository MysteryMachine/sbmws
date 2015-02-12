(ns game.enemy
  (:use arcadia.core 
        game.core
        game.collidable)
  (:import [UnityEngine Debug Transform GameObject Vector3]))

(declare c-update c-awake)

(defcomponent Enemy 
  [^Vector3 speed
   ^Boolean moveForward]
  
  (Awake [this] (c-awake this)) 
  (Update [this] (c-update this)))

(defn- c-awake [this] 
  (doseq [transform (rest (.GetComponentsInChildren (.gameObject this) Transform))]
  	(add-component (.gameObject transform) game.collidable.Collidable)
  	(set! (.. (.gameObject transform) (GetComponent "Collidable") type) :enemy)))

(defn- c-update [this] 
  (let [transform (.. this (GetComponent "Transform"))
        new-vec (v3+ (.position transform) (.speed this))]
    (if (.moveForward this)
      (if (> (.x new-vec) -60)
        (set! (.position transform) new-vec)
        (GameObject/Destroy (.gameObject this)))
    (set! (.position transform) (v3- (.position transform)
                                     (vector3 -0.02 0 0))))))

(defn- enemy-take-damage [enemy-component collider]
  (let [bullet (.. collider gameObject (GetComponent "Bullet"))
        dmg (.damage bullet)  
        new-health (- (.health enemy-component) dmg)]
    (do
      (if (<= new-health 0) 
        (GameObject/Destroy (.gameObject enemy-component))
        (do
          (if (> (.recoveryTime enemy-component) 0)
            (do 
              (set! (.. enemy-component (GetComponentInParent Enemy) moveForward) false)
              (set! (.curRecTime enemy-component) (.recoveryTime enemy-component))))
          (set! (.health enemy-component) (int new-health))))
      (GameObject/Destroy (.gameObject collider)))))

(defn should-recover [enemy-component]
  (> (.curRecTime enemy-component) 0))

(defn recover [enemy-component]
  (if (should-recover enemy-component)
    (let [new-rec-time (- (.curRecTime enemy-component) 1)]
       (if (= 0 new-rec-time)
           (set! (.. enemy-component (GetComponentInParent Enemy) moveForward) true))
       (set! (.curRecTime enemy-component) (int new-rec-time)))))

(defn enemy-collide [enemy-component collider]
  (if (has-collider-type collider :player-bullet)
      (enemy-take-damage enemy-component collider)))
