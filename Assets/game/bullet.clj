(ns game.bullet
  (:use arcadia.core
        game.core
        game.collidable)
  (:import [UnityEngine]))

(declare c-update)

(defcomponent Bullet 
  [^int damage
   ^int lifetime
   ^int timeElapsed
   ^float follow
   ^Vector3 speed]

  (Update [this] (c-update this)))

(defn- move [this]
  (let [transform (.GetComponent this "Transform")
        new-time (+ 1 (.timeElapsed this))
        new-pos (v3+ (.position transform) (.speed this))]
    (do 
      (set! (.timeElapsed this) (int new-time))
      (set! (.position transform) new-pos))))

(defn- c-update [this]
  (if (> (.timeElapsed this) (.lifetime this)) 
      (GameObject/Destroy (.gameObject this))
      (move this)))
