(ns game.level.core
  (:use arcadia.core
        game.core))

(defn bg-update-fn [x-max x-min dx]
  (fn c-update [this]
    (let [xi (.. this position x)
          xf (if (> xi x-max) 
                    (- xi dx) 
                    x-min)]
      (set! (position this)
            (vector3 (position this) :x xf)))))

(defn bg-awake-fn [x-max x-min dx]
  (fn c-awake [this firstSprite]
    (if firstSprite
      (set! (position this) (vector3 (position this) :x 0))
      (set! (position this) (vector3 (position this) x-max)))))