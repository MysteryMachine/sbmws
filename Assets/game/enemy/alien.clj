(ns game.enemy.alien
  (:use
    arcadia.core 
    game.core
    game.enemy)
  (:import [UnityEngine Debug Transform GameObject]))

(declare c-update c-awake c-collide)

(defcomponent Alien 
  [^int health
   ^int recoveryTime
   ^int curRecTime
   ^float height
   ^float width 
   ^float yLoops
   ^float xLoops
   ^float dt 
   ^float t]
  
  (Awake [this] (c-awake this)) 
  (Update [this] (c-update this xLoops yLoops height width t dt curRecTime))
  (OnTriggerEnter2D [this collider] (c-collide this collider)))

(def ^:private max-t (* 10000 2 Math/PI))
(defn- fx [t xLoops width]  (* width (sin (* xLoops t))))
(defn- fy [t yLoops height] (* height (sin (* yLoops t))))

(defn- c-awake [this] this)
(defn- c-update [this xLoops yLoops height width t dt curRecTime]
  (let [should-rec (should-recover this)
        tf (if (> t max-t) 0 (+ (/ dt (+ 1 (Math/Sqrt curRecTime))) t))
        v (v3update (local-position this) 
                    :x (fx t xLoops width)
                    :y (fy t yLoops height))]
    (do
      (recover this)
      (set! (.. this transform localPosition) v)
      (set! (.t this) (float tf)))))

(defn- c-collide [this collider] (enemy-collide this collider))
