(ns client-server.quiz
  (:require [goog.net.XhrIo :as xhr]
            [domina :as d]
            [domina.events :as events]))

(def result-id "quiz-result")
(def expr-id "quiz-answer")
(def button-id "quiz-button")
(def url "/check-answer")

(defn receive-result [event]
  (d/set-text! (d/by-id result-id)
               (.getResponseText (.-target event))))

(defn post-for-eval [expr-str]
  (xhr/send url receive-result "POST" expr-str))

(defn get-expr []
  (.-value (d/by-id expr-id)))

(defn ^:export main []
  (events/listen! (d/by-id button-id)
                  :click
                  (fn [event]
                    (post-for-eval (get-expr))
                    (events/stop-propagation event)
                    (events/prevent-default event))))
