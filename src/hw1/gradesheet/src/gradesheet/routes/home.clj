(ns gradesheet.routes.home
  (:require [gradesheet.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :refer [ok]]
            [gradesheet.models.user :as user]
            [clojure.java.io :as io]))

(defn show-page [page]
  (layout/render page))

(defn register-user
  "register user with the system"
  [form]
  ;; get data from form
  (let [username (:username form)
        password (:passsword form)
        s1 {:username username}
        s2 {:username username :password password}]
    ;; check to see if user exists
    (if (empty? (user/get-user s1))
      (do
        (user/save-user s2)
        (show-page "login.html"))
      (layout/render "error.html" {:message "user already exist"}))))

(defn login
  "login user into the system"
  [form]
  ;; get data from form
  (let [username (:username form)
        password (:passsword form)
        s2 {:username username :password password}]
    ;; check to see if username and password found in database
    (if (empty? (user/get-user s2))
      (layout/render "error.html" {:message "incorrect credential"})
      (show-page "auth-home.html"))))

(defroutes home-routes
  (GET "/" [] (show-page "welcome.html"))
  (GET "/register" [] (show-page "register.html"))
  (GET "/login" [] (show-page "login.html"))
  (GET "/about" [] (show-page "about.html"))
  (POST "/register" [& form] (register-user form))
  (POST "/login" [& form] (login form)))
