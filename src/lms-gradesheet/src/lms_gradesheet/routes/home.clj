(ns lms-gradesheet.routes.home
  (:require [lms-gradesheet.layout :as layout]
            [lms-gradesheet.models.quiz :as quiz]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
   "home.html" {:home (-> "docs/home.md" io/resource slurp)}))

(defn about-page []
  (layout/render
   "about.html" {:about-content (-> "docs/about.md" io/resource slurp)}))

(defn emit-quiz-message
  [title body]
  (layout/render "quiz-msg.html" {:message-title title
                                  :message-body body}))

(defn save-submission
  [form]
  (quiz/record-grade form)
  (emit-quiz-message "Success"
                     "Your quiz has been successfully submitted."))

(defn grade-quiz
  [form]
  (cond
   (not (= (:policy-flag form) "on"))
   (emit-quiz-message "Not Accepted"
                      "Your exam was not accepted because you did not check the academic policy box.")
   (= (:name form) "")
   (emit-quiz-message "Not Accepted"
                      "Name cannot be empty")
   (= (:cin form) "")
   (emit-quiz-message "Not Accepted"
                      "CIN cannot be empty")
   :else (save-submission form)))

(defn show-quiz
  [number]
  (let [exam-file (str "exams/" number ".json")
        exam (quiz/read-exam exam-file)
        params (merge exam
                      {:questions (quiz/shuffle-choices exam)
                       :p-questions (:program exam)})]
    (layout/render "quiz.html" params)))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/quiz/:number" [number] (show-quiz number))
  (POST "/quiz/submit" [& form] (grade-quiz form)))

