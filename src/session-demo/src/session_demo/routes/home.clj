(ns session-demo.routes.home
  (:require [session-demo.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [ring.util.response :refer [response]] ;; add this
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(defn get-user [{session :session}]
  (response (str session)))

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn set-user! [id {session :session}]
  (-> (str "User set to: " id)
      response
      (assoc :session (assoc session
                        :user id
                        :token (uuid)))))

(defn remove-user! [{session :session}]
  (-> (response "")
      (assoc :session (dissoc session :user :token))))

(defn clear-session! []
  (dissoc (response "") :session))

(defroutes app-session-routes
  (GET "/get-session" req (get-user req))
  (GET "/login/:id" [id :as req] (set-user! id req))
  (GET "/remove" req (remove-user! req))
  (GET "/logout" req (clear-session!)))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))

