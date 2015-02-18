(ns game.menu
  (:use arcadia.core)
  (:import [UnityEngine Debug Transform 
            Input GameObject Application]))

(def menu-bottom -1.4)
(def menu-top -0.1)

(declare c-update
         c-awake)

(defcomponent Menu [^Boolean top]
  (Awake [this] (c-awake this)) 
  (Update [this] (c-update this)))

(defn- get-cursor [^Menu this]
  (->> (.GetComponentsInChildren (.gameObject this) Transform)
       (filter #(= (.name %) "Cursor")) 
       first))

(defn- menu-action [this] 
  (if (.top this)
    (do
      (GameObject/Destroy (.gameObject (get-cursor this)))
      (.. this (GetComponent "Animator") (SetBool "isMenu" false)))
    (Application/Quit)))

(defn- handle-other-key! [^Menu this] 
  (if (.. this (GetComponent "Animator") (GetBool "isMenu"))
      (menu-action this)
      (Application/LoadLevel "Level")))

(defn- handle-arrow-keys! [^Menu this]
  (if (.. this (GetComponent "Animator") (GetBool "isMenu"))
    (let [cursor-transform (get-cursor this)
          y-val (if (.top this) menu-bottom menu-top)
          pos (.position cursor-transform)]
      (do
        (set! (.top this) (not (.top this)))
        (set! (.. cursor-transform position) (Vector3. (.x pos) y-val 1))))))

(defn- c-awake [^Menu this]
  (set! (.top this) true))

(defn- c-update [^Menu this]
  (let [arrow-keys?  (or (Input/GetKeyDown "up")
                         (Input/GetKeyDown "down"))
        other-key?   (Input/anyKeyDown)]
    (cond arrow-keys?  (handle-arrow-keys! this)
          other-key? (handle-other-key! this))))