(ns client-server.server
  (:require [compojure.route :as route]
            [compojure.core :as compojure]
            [ring.util.response :as response]
            [ring.adapter.jetty :as jetty]))

(def upper (re-pattern "[A-Z]+"))
(def number (re-pattern "[0-9]+"))
(def special (re-pattern "[\"'!@#$%^&*()?]+"))

(defn strength? [password]
  (not (nil? (and (re-find upper password)
                  (re-find number password)
                  (re-find special password)))))

(defn length? [password]
  (> (count password) 8))

(defn valid-password? [password]
  (and (strength? password) (length? password)))

(defn serialize [m] (str m))
(defn de-serialize [s] (read-string s))

(defn eval-clojure [request]
  (try
    (let [expr (read-string (slurp (:body request)))]
      (pr-str (eval expr)))
    (catch Throwable t
      (str "ERROR: " t))))

(defn check-answer [request]
  (try
    (let [expr (read-string (slurp (:body request)))
          n (Integer. (:number expr))
          a (Integer. (:answer expr))]
      (if (= a (* n n))
        (pr-str "correct")
        (pr-str "incorrect")))
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

(valid-password? "Foobar?8Foobar9!")
(de-serialize (serialize {:answer 1 :number 2}))
