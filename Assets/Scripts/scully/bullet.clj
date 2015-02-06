(ns scully.bullet
  (:use arcadia.core)
  (:import [UnityEngine]))

(defcomponent Bullet [^float lifetime
                      ^float timeElapsed
                      ^Vector3 speed
                      ^float follow
                      ^int damage] 
  (Awake [this] 
    (add-component (.gameObject this) scully.collidable.Collidable)
    (set! (.. this (GetComponent "Collidable") type) :player-bullet))
  (Update [this] 
    (let [dead (> timeElapsed lifetime)
          new-time (+ 1 timeElapsed)]
      (if dead 
          (GameObject/Destroy (.gameObject this))
          (let [transform (.GetComponent this "Transform")
                pos (.position transform)
                new-pos (Vector3/op_Addition pos (.speed this))]
            (do 
              (set! timeElapsed new-time)
              (set! (.position transform) new-pos)))))))