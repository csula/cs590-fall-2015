(ns gradesheet.models.user
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.result :refer [ok? has-error?]]))

(def conn (mg/connect))
(def db (mg/get-db conn "gradesheet-live")) ;; database name
(def document "user") ;; document

(defn save-user
  [user]
  (mc/insert-and-return db document user))

(defn get-user
  [search-criteria]
  (mc/find-maps db document search-criteria))
