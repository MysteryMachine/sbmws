(ns scully.player
  (:use arcadia.core
        scully.bullet
        scully.collidable)
  (:import [UnityEngine GameObject Debug])) 

(declare fixed-update
         awake)

(defcomponent Player [^float fireRate ^float fireFrame
                      ^Bullet bullet ^Vector3 bulletOffset
                      ^int lives]
  (Awake [this] (awake this))
  (FixedUpdate [this] (fixed-update this)))

(defn awake [this]
  (do 
    (set! (.fireFrame this) (float 0))
    (set! (.bullet this) (.. this (GetComponent "BulletHolder") bullet))
    (add-component (.gameObject this) scully.collidable.Collidable)
    (set! (.. this (GetComponent "Collidable") type) :player)))

(defn fixed-update [this]  
  (let [fire-frame (if (= (.fireFrame this) (float 0))
                       (float 0)
                       (float (- (.fireFrame this) (float 1))))
        new-pos (.. (object-named "Main Camera") camera (ScreenToWorldPoint Input/mousePosition))
        new-vec (Vector3. (.x new-pos) (.y new-pos) (float 0))
        clicked (Input/GetMouseButton 0)
        fire-bullet (and clicked (= (float 0) fire-frame))
        bullet-location (if fire-bullet (.. this transform position))]
     (do 
       (set! (.. this transform position) 
                 new-vec)
       (set! (.fireFrame this) fire-frame)
       (if fire-bullet
           (do 
             (instantiate (.bullet this) (Vector3/op_Addition bullet-location
                                                              (.bulletOffset this)))
             (set! (.fireFrame this) (.fireRate this)))))))
