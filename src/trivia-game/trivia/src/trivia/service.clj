(ns trivia.service
  (:require [trivia.layout :as layout]
            [io.pedestal.http :as bootstrap]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [ring.util.response :as ring-resp]))

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(defn home-page
  [request]
  (ring-resp/response "Hello World!"))

(defn selmer-page
  [request]
  (layout/render "example.html" {:title "example"}))

(defn game-page
  [request]
  (layout/render "game.html" {:title "Trivia Game"}))

(def question {:q "How do you determine if empty?"
               :c ["empty?", "exist?", "map", "reduce"]
               :a "empty?"
               :l "q3"})

(defn get-question
  [request]
  (ring-resp/response (str (disassoc question :a))))

(defn get-correct-choice [q]
  (let [v (:c q)
        a (:a q)]
    (+ 1 (.indexOf v a))))

(defn check-answer
  [request]
  (try
    (let [expr (read-string (slurp (:body request)))
          answer (:answer expr)
          a (if (or (nil? answer) (= answer "")) 0 (Integer. (:answer expr))]
      (if (= a (get-correct-choice question))
        (ring-resp/response "correct")
        (ring-resp/response "incorrect")))
    (catch Throwable t
      (str "ERROR: " t))))

(defroutes routes
  ;; Defines "/" and "/about" routes with their associated :get handlers.
  ;; The interceptors defined after the verb map (e.g., {:get home-page}
  ;; apply to / and its children (/about).
  [[["/" {:get home-page}
     ["/check-answer" {:post check-answer}]
     ["/selmer" {:get selmer-page}]
     ["/game" {:get game-page}]
     ["/get-question" {:get get-question}]
     ["/about" {:get about-page}]]]])

;; Consumed by trivia.server/create-server
;; See bootstrap/default-interceptors for additional options you can configure
(def service {:env :prod
              ::bootstrap/routes routes
              ::bootstrap/resource-path "/public"
              ::bootstrap/type :jetty
              ::bootstrap/port 8080})
