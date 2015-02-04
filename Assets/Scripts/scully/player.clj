(ns scully.player
  (:use arcadia.core
        scully.bullet)
  (:import [UnityEngine GameObject Debug])) 

(defcomponent Player [^float fireRate 
                      ^float fireFrame
                      ^Bullet bullet
                      ^Vector3 bulletOffset]
  (Awake [this] 
    (do 
      (set! fireFrame 0.)
      (set! bullet (.. this (GetComponent "BulletHolder") bullet))))
  (FixedUpdate [this] 
    (let [fire-frame (if (= fireFrame 0.)
                         0.
                         (- fireFrame 1.))
          new-pos (.. (object-named "Main Camera") camera (ScreenToWorldPoint Input/mousePosition))
          new-vec (Vector3. (.x new-pos) (.y new-pos) 0.)
          clicked (Input/GetMouseButton 0)
          fire-bullet (and clicked (= 0. fire-frame))
          bullet-location (if fire-bullet (.. this transform position))]
       (do 
         (set! (.. this transform position) 
                   new-vec)
         (set! fireFrame fire-frame)
         (if fire-bullet
             (do 
               (instantiate bullet (Vector3/op_Addition bullet-location
                                                        bulletOffset))
               (set! fireFrame fireRate)))))))