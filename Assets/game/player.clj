(ns game.player
  (:use arcadia.core
        game.core
        game.collidable)
  (:import [UnityEngine GameObject Debug])) 

(declare c-fixed-update
         c-awake
         c-collide)

(defcomponent Player 
  [^int fireRate 
   ^int fireFrame
   ^int lives 
   ^int invulnTime
   ^int invulnTimeLeft
   ^Bullet bullet 
   ^Vector3 bulletOffset]
  
  (Awake [this] (c-awake this))
  (FixedUpdate [this] (c-fixed-update this))
  (OnTriggerEnter2D [this collider] (c-collide this collider))
  (OnTriggerStay2D [this collider] (c-collide this collider)))

(defn- fire-bullet [this]
  (let [start-pos (Vector3/op_Addition (.. this transform position) (.bulletOffset this))]
    (do 
      (make-collidable (instantiate (.bullet this) start-pos) :player-bullet)
      (set! (.fireFrame this) (.fireRate this)))))

(defn- c-awake [this]
  (do 
    (set! (.fireFrame this) (int 0))
    (make-collidable this :player)))

(defn- c-fixed-update [this]  
  (let [fire-frame (if (> (.fireFrame this) 0)
                       (- (.fireFrame this) 1)
                       (.fireFrame this))
        new-pos (.. (object-named "Main Camera") camera (ScreenToWorldPoint Input/mousePosition))
        new-vec (Vector3. (.x new-pos) (.y new-pos) 0)
        clicked (Input/GetMouseButton 0)
        fire-bullet? (and clicked (= 0 fire-frame))
        cur-invuln-time-left (.invulnTimeLeft this)
        new-invuln-time-left (if (> cur-invuln-time-left 0)
                                 (- cur-invuln-time-left 1)
                                 0)]
     (do 
       (set! (.invulnTimeLeft this) (int new-invuln-time-left))
       (set! (.. this transform position) new-vec)
       (set! (.fireFrame this) (int fire-frame))
       (if fire-bullet? (fire-bullet this)))))

(defn- c-collide [this collider]
  (let [collider-type (collider-type (.gameObject collider))
        take-damage (and (or (= collider-type :enemy-bullet) 
                             (= collider-type :enemy)) 
                         (< (.invulnTimeLeft this) 0))]
    (if take-damage
        (do
          (set! (.invulnTimeLeft this) (.invulnTime this))
          (set! (.lives this) (- (.lives this) (int 0)))))))

