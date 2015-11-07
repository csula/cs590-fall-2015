(ns gradesheet.routes.home
  (:require [gradesheet.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn show-page [page]
  (layout/render page))

(defn about-page []
  (layout/render "about.html"))

(defn register-page []
  (layout/render "register.html"))

(defn login-page []
  (layout/render "login.html"))

(defn welcome-page []
  (layout/render "welcome.html"))

(defroutes home-routes
  (GET "/" [] (welcome-page))
  (GET "/register" [] (register-page))
  (GET "/login" [] (login-page))
  (GET "/about" [] (about-page))
  (POST "/register" [& form] (register-user form))
  (POST "/login" [& form] (login form))
  )

