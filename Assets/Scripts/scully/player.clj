(ns scully.player
  (:use arcadia.core
        scully.bullet
        scully.collidable)
  (:import [UnityEngine GameObject Debug])) 

(declare fixed-update
         awake
         collide)

(defcomponent Player [^float fireRate ^float fireFrame
                      ^Bullet bullet ^Vector3 bulletOffset
                      ^float lives ^float invulnTime
                      ^float invulnTimeLeft]
  (Awake [this] (awake this))
  (FixedUpdate [this] (fixed-update this))
  (OnTriggerEnter2D [this collider] (collide this collider))
  (OnTriggerStay2D [this collider] (collide this collider)))

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
        bullet-location (if fire-bullet (.. this transform position))
        cur-invuln-time-left (.invulnTimeLeft this)
        new-invuln-time-left (if (> cur-invuln-time-left (float 0))
                                 (float (- cur-invuln-time-left (float 1)))
                                 (float 0))]
     (do 
       (set! (.invulnTimeLeft this) new-invuln-time-left)
       (set! (.. this transform position) 
                 new-vec)
       (set! (.fireFrame this) fire-frame)
       (if fire-bullet
           (do 
             (instantiate (.bullet this) (Vector3/op_Addition bullet-location
                                                              (.bulletOffset this)))
             (set! (.fireFrame this) (.fireRate this)))))))

(defn collide [this collider]
  (let [collider-type (collider-type (.gameObject collider))
        take-damage (and (or (= collider-type :enemy-bullet) 
                             (= collider-type :enemy)) 
                         (< (.invulnTimeLeft this) (float 0.001)))]
    (if take-damage
        (do
          (set! (.invulnTimeLeft this) (.invulnTime this))
          (set! (.lives this) (float (- (.lives this) (float 1)))))(Debug/Log (str collider-type)))))

