(ns game.player
  (:use arcadia.core
        game.core
        hard.core
        game.collidable)
  (:import [UnityEngine GameObject Debug Input])) 

(declare c-fixed-update
         c-awake
         c-collide)

(defcomponent Player 
  [^int fireRate 
   ^int fireFrame
   ^int lives 
   ^int invulnTime
   ^int invulnTimeLeft
   ^float speed
   ^Bullet bullet 
   ^Bullet secondaryBullet
   ^Vector3 bulletOffset]
  
  (Awake [this] (c-awake this))
  (FixedUpdate [this] (c-fixed-update this))
  (OnTriggerEnter2D [this collider] (c-collide this collider))
  (OnTriggerStay2D [this collider] (c-collide this collider)))

(defn- fire-bullet [this]
  (let [start-pos (v3+ (position this) (.bulletOffset this))]
    (do 
      (make-collidable (instantiate (.bullet this) start-pos) :player-bullet)
      (set! (.fireFrame this) (.fireRate this)))))

(defn- handle-controls [this fire-frame]
  (let [shoot? (and (= 0 fire-frame)
                (or (Input/GetKey "enter")
                    (Input/GetKey "return")
                    (Input/GetKey "space")
                    (Input/GetKey "z")))
        mvspd (if (and (or (Input/GetKey "right") (Input/GetKey "left")) 
                       (or (Input/GetKey "down") (Input/GetKey "up")))
                  (/ (.speed this) (Math/Sqrt 2))
                  (.speed this))
        dx (cond (Input/GetKey "right") mvspd
                 (Input/GetKey "left") (- mvspd)
                 :else 0)
        dy (cond (Input/GetKey "up") mvspd
                 (Input/GetKey "down") (- mvspd)
                 :else 0)
        new-vec (v3+ (position this) (vector3 dx dy 0))]
      (do
        (if shoot? (fire-bullet this))
        (set! (position this) new-vec))))

(defn- c-awake [this]
  (do 
    (set! (.fireFrame this) (int 0))
    (make-collidable this :player)))

(defn- c-fixed-update [this]  
  (let [fire-frame (if (> (.fireFrame this) 0)
                       (- (.fireFrame this) 1)
                       (.fireFrame this))
        ;new-pos (.. (object-named "Main Camera") camera (ScreenToWorldPoint Input/mousePosition))
        clicked (Input/GetMouseButton 0)
        cur-invuln-time-left (.invulnTimeLeft this)
        new-invuln-time-left (if (> cur-invuln-time-left 0)
                                 (- cur-invuln-time-left 1)
                                 cur-invuln-time-left)]
     (do 
       (set! (.invulnTimeLeft this) (int new-invuln-time-left))
       (.. this (GetComponent "Animator") (SetInteger "invuln" (int new-invuln-time-left)))
       (set! (.fireFrame this) (int fire-frame))
       (handle-controls this fire-frame))))

(defn- c-collide [this collider]
  (let [collider-type (collider-type (.gameObject collider))
        take-damage (and (or (= collider-type :enemy-bullet) 
                             (= collider-type :enemy)) 
                         (= (.invulnTimeLeft this) 0))]
    (if take-damage
        (let [lives-left (int (- (.lives this) 1))]
          (do
            (set! (.invulnTimeLeft this) (.invulnTime this))
            (set! (.lives this) lives-left))))))

