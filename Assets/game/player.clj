(ns game.player
  (:use arcadia.core
        game.core
        hard.core
        game.collidable)
  (:import [UnityEngine GameObject Debug Input Application])) 

(declare c-fixed-update
         c-awake
         c-collide)

(defcomponent Player 
  [^int fireRate 
   ^int fireFrame
   ^int catFireRate
   ^int catFireFrame
   ^int lives 
   ^int invulnTime
   ^int invulnTimeLeft
   ^float speed
   ^Bullet bullet 
   ^Bullet cat
   ^Vector3 bulletOffset
   ^Vector3 catOffset
   ^Quaternion catRotation]
  
  (Awake [this] (c-awake this))
  (FixedUpdate [this] (c-fixed-update this))
  (OnTriggerEnter2D [this collider] (c-collide this collider))
  (OnTriggerStay2D [this collider] (c-collide this collider)))

(defn- fire-bullet [this]
  (let [start-pos (v3+ (position this) (.bulletOffset this))]
    (do 
      (make-collidable (instantiate (.bullet this) start-pos) :player-bullet)
      (set! (.fireFrame this) (.fireRate this)))))

(defn- fire-cat [this]
  (let [start-pos (v3+ (position this) (.catOffset this))]
    (do 
      (make-collidable (instantiate (.cat this) start-pos (.catRotation this)) :player-bullet)
      (set! (.catFireFrame this) (.catFireRate this)))))

(defn- handle-controls [this fire-frame cat-frame]
  (let [fire-key? (or (Input/GetKey "enter")
                    (Input/GetKey "return")
                    (Input/GetKey "space")
                    (Input/GetKey "z"))
        shoot? (and (= 0 fire-frame) fire-key?)
        shoot-cat? (and (= 0 cat-frame) fire-key?)
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
        (if (and (not shoot-cat?) shoot?) (fire-bullet this))
        (if shoot-cat? (fire-cat this))
        (set! (position this) new-vec))))

(defn- c-awake [this]
  (do 
    (set! (.fireFrame this) (int 0))
    (make-collidable this :player)))

(defn- c-fixed-update [this]  
  (let [fire-frame (if (> (.fireFrame this) 0)
                       (- (.fireFrame this) 1)
                       (.fireFrame this))
        cat-frame (if (> (.catFireFrame this) 0)
                       (- (.catFireFrame this) 1)
                       (.catFireFrame this))
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
       (set! (.catFireFrame this) (int cat-frame))
       (handle-controls this fire-frame cat-frame))))

(defn- c-collide [this collider]
  (let [collider-type (collider-type (.gameObject collider))
        take-damage (and (or (= collider-type :enemy-bullet) 
                             (= collider-type :enemy)) 
                         (= (.invulnTimeLeft this) 0))]
    (if take-damage
        (let [lives-left (int (- (.lives this) 1))]
          (do
            (set! (.invulnTimeLeft this) (.invulnTime this))
            (set! (.. (object-named "Lives") (GetComponent "Text") text) (str "x " lives-left))
            (set! (.lives this) lives-left)
            (if (= lives-left 0) (Application/LoadLevel "Game Over")))))))

