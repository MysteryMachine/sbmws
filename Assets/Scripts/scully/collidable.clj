(ns scully.collidable
  (:use arcadia.core)
  (:import [UnityEngine Debug Transform GameObject]))

(defcomponent Collidable [^Keyword type])