(ns hw1.routes.home
  (:require [hw1.layout :as layout]
            [hw1.models.user :as user]
            [compojure.core :refer [defroutes POST GET]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(defn register [form]
  (let [username (:username form)
        password (:password form)]
    (if (not (user/exist-user? username))
      (do
        (user/add-user {:username username :password password})
        (layout/render "login.html"))
      (layout/render "error.html" {:message "user exist"}))))

(defn login [form]
  (let [username (:username form)
        password (:password form)]
    (if (user/auth-user? username password)
      (layout/render "auth-home.html"))
      (layout/render "error.html" {:message "wrong credentials"})))

(defroutes home-routes
  (GET "/" [] (layout/render "welcome.html"))
  (GET "/about" [] (about-page))

  (GET "/login" [] (layout/render "login.html"))
  (GET "/register" [] (layout/render "register.html"))

  (POST "/login" [& form] (login form))
  (POST "/register" [& form] (register form)))

