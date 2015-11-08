(ns hw1.models.user
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.result :refer [ok? has-error?]]))

(def conn (mg/connect))
(def db (mg/get-db conn "hw1")) ;; database name
(def document "user") ;; document

(defn add-user
  [quiz-result]
  (mc/insert-and-return db document quiz-result))

(defn get-user
  [search-criteria]
  (mc/find-maps db document search-criteria))

(defn exist-user?
  [username]
  (if (empty? (get-user {:username username}))
    false
    true))

(defn auth-user?
  [username password]
  (if (empty? (get-user {:username username :password password}))
    false
    true))
