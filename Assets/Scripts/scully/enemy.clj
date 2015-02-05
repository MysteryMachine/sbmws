(ns scully.enemy
  (:use arcadia.core)
  (:import [UnityEngine Debug Transform GameObject Vector3]))

(declare update
         awake)

(defcomponent Enemy [^Vector3 speed]
  (Awake [this] (awake this)) 
  (Update [this] (update this)))

(defn awake [this] 
  (doseq [transform (rest (.GetComponentsInChildren (.gameObject this) Transform))]
  	(add-component (.gameObject transform) scully.collidable.Collidable)
  	(set! (.. (.gameObject transform) (GetComponent "Collidable") type) :enemy)))

(defn update [this] 
  (let [transform (.. this (GetComponent "Transform"))
        new-vec (Vector3/op_Addition (.position transform) (.speed this))]
    (if (> (.x new-vec) (float -13.0)) 
      (set! (.position transform) new-vec)
      (GameObject/Destroy (.gameObject this)))))