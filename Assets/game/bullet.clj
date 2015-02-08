(ns game.bullet
  (:use arcadia.core
        game.collidable)
  (:import [UnityEngine]))

(defcomponent Bullet 
  [^int damage
   ^int lifetime
   ^int timeElapsed
   ^float follow
   ^Vector3 speed]
   
  (Awake [this] (add-collider! this))
  
  (Update [this] 
    (let [dead (> timeElapsed lifetime)
          new-time (+ 1 timeElapsed)]
      (if dead 
          (GameObject/Destroy (.gameObject this))
          (let [transform (.GetComponent this "Transform")
                pos (.position transform)
                new-pos (Vector3/op_Addition pos (.speed this))]
            (do 
              (set! (.timeElapsed this) (int new-time))
              (set! (.position transform) new-pos)))))))