(ns scully.bullet
  (:use arcadia.core)
  (:import [UnityEngine]))

(defcomponent Bullet [^float lifetime
                      ^float timeElapsed
                      ^float speed
                      ^float follow] 
  (Update [this] 
    (let [dead (> timeElapsed lifetime)
          new-time (+ 1 timeElapsed)]
      (if dead 
          (GameObject/Destroy (.gameObject this))
          (let [transform (.GetComponent this "Transform")
                pos (.position transform)
                x (.x pos) y (.y pos) z (.z pos)
                new-pos (Vector3. (+ x speed) y z)]
            (do 
              (set! timeElapsed new-time)
              (set! (.position transform) new-pos)))))))