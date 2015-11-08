(ns client-server.server
  (:require [compojure.route :as route]
            [compojure.core :as compojure]
            [ring.util.response :as response]
            [ring.adapter.jetty :as jetty]))

(defn eval-clojure [request]
  (try
    (let [expr (read-string (slurp (:body request)))]
      (pr-str (eval expr)))
    (catch Throwable t
      (str "ERROR: " t))))

(defn check-answer [request]
  (try
    (let [expr (read-string (slurp (:body request)))]
      (pr-str "text body here..."))
    (catch Throwable t
      (str "ERROR: " t))))

(compojure/defroutes app
  (compojure/GET "/" request (response/resource-response "public/index.html"))
  (compojure/GET "/quiz" request (response/resource-response "public/quiz.html"))
  (compojure/POST "/eval" request (eval-clojure request))
  (compojure/POST "/check-answer" request (check-answer request))
  (route/resources "/"))

(defn -main []
  (prn "View the example at http://localhost:4000/")
  (jetty/run-jetty app {:join? true :port 4000}))
