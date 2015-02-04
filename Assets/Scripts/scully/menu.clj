(ns scully.menu
  (:use arcadia.core)
  (:import [UnityEngine Debug Transform 
            Input GameObject Application]))

(def menu-bottom (float -1.4))
(def menu-top (float -0.1))

(declare get-cursor 
         change-menu!
         change-cursor!
         update
         awake)

(defcomponent Menu [^Boolean top]
  (Awake [this] (awake this)) 
  (Update [this] (update this)))

(defn awake [^Menu this]
  (set! (.top this) true))

(defn update [^Menu this]
  (let [change-cursor? (or (Input/GetKeyDown "up")
                     	   (Input/GetKeyDown "down"))
        change-menu? (or (Input/GetKeyDown "return")
                         (Input/GetKeyDown "enter"))]
    (cond change-menu? (change-menu! this)
          change-cursor? (change-cursor! this))))

(defn get-cursor [^Menu this]
  (->> (.GetComponentsInChildren (.gameObject this) Transform)
       (filter #(= (.name %) "Cursor")) 
       first))

(defn change-menu! [^Menu this] 
  (if (.. this (GetComponent "Animator") (GetBool "isMenu"))
    (if (.top this)
        (do
          (GameObject/Destroy (.gameObject (get-cursor this)))
          (.. this (GetComponent "Animator") 
                    (SetBool "isMenu" false)))
        (Application/Quit))
  (Application/LoadLevel "Level")))

(defn change-cursor! [^Menu this]
  (let [cursor-transform (get-cursor this)
        y-val (if (.top this) menu-bottom menu-top)
        pos (.position cursor-transform)]
    (if (.top this)
        (do
          (set! (.top this) (not (.top this)))
          (set! (.. cursor-transform position) (Vector3. (.x pos) y-val 1))))))

