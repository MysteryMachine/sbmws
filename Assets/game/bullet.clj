(ns game.bullet
  (:use arcadia.core
        game.core
        game.collidable)
  (:import [UnityEngine Animator Debug]))

(declare c-update c-collide)

(defcomponent Bullet 
  [^int damage
   ^int lifetime
   ^int timeElapsed
   ^float follow
   ^Vector3 speed]

  (Update [this] (c-update this))
  (OnTriggerEnter2D [this collider] (c-collide this collider)))

(def ^:private bullet-death-state (Animator/StringToHash "Base Layer.Bullet Death" ))
(def ^:private dead-state (Animator/StringToHash "Base Layer.Dead" ))

(defn- move [this]
  (let [transform (.GetComponent this "Transform")
        new-time (+ 1 (.timeElapsed this))
        new-pos (v3+ (.position transform) (.speed this))]
    (do 
      (set! (.timeElapsed this) (int new-time))
      (set! (.position transform) new-pos))))

(defn- c-update [this]
  (let [anim-state (.. this (GetComponent "Animator") (GetCurrentAnimatorStateInfo (int 0)) nameHash)]
    (cond 
      (> (.timeElapsed this) (.lifetime this)) (GameObject/Destroy (.gameObject this))
      (=  bullet-death-state anim-state) this
      (=  dead-state anim-state) (GameObject/Destroy (.gameObject this))
      :else (move this))))

(defn- c-collide [this collider]
  (if (has-collider-type collider :enemy)
      (.. this (GetComponent "Animator") (SetTrigger "die"))))
