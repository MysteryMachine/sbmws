(ns scully.bullet
  (:use arcadia.core)
  (:import [UnityEngine]))

(defcomponent
  Bullet [] (Update [this] this))