(ns lms-gradesheet.models.quiz
  (:require [lms-gradesheet.layout :as layout]
            [compojure.core :refer :all]
            [clojure.java.io :as io]
            [clojure.data.json :as json]
            [cheshire.core :as cheshire]
            [ring.util.anti-forgery :as af]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.result :refer [ok? has-error?]]
            [clj-time.coerce :as c]
            [clj-time.format :as f]
            [clj-time.local :as l]
            [clojure.pprint :refer :all]))

(def conn (mg/connect))
(def db (mg/get-db conn "lms"))
(def document "quiz")

(defn read-file
  [file-path]
  (slurp (io/file (io/resource file-path))))

(defn read-exam
  [v]
  (json/read-str (read-file v) :key-fn keyword))

(defn format-time
  [date]
  (def custom-formatter (f/formatter "yyyyMMdd hh:mm:ss"))
  (f/unparse custom-formatter date))

(defn get-time-now
  []
  (format-time (l/local-now)))

(defn shuffle-choices
  [exam]
  (map (fn [x]
         {:c (shuffle (:c x))
          :q (:q x)
          :l (:l x)}) (shuffle (:questions exam))))

(defn record-grade
  [form]
  (let [result (dissoc form :__anti-forgery-token)
        grade-object (merge result {:created (get-time-now)})]
    (pprint grade-object)
    (mc/insert-and-return db document grade-object)))

