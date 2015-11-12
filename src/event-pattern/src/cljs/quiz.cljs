(ns client-server.quiz
  (:require [goog.net.XhrIo :as xhr]
            [cljs.reader :as reader]
            [domina :as d]
            [domina.events :as events]))

(def button-gen-integer-id "button-gen-integer")
(def quiz-integer-var-id "quiz-integer-var")
(def quiz-answer-id "quiz-answer")
(def button-answer-id "button-answer")
(def quiz-result-id "quiz-result")

(def max-int 10)
(def url "/check-answer")

(defn serialize [m] (str m))
(defn de-serialize [s] (reader/read-string s))

(defn receive-result [event]
  (d/set-text! (d/by-id quiz-result-id)
               (.getResponseText (.-target event))))

(defn post-for-eval [expr-str]
  (xhr/send url receive-result "POST" expr-str))

(defn get-expr []
  (let [n (.-innerHTML (d/by-id quiz-integer-var-id))
        a (.-value (d/by-id quiz-answer-id))]
    (.log js/console (str "n: " n))
    (.log js/console (str "a: " a))
    (serialize {:number n :answer a})))

(defn update-text-field []
  (let [rnd-number (rand-int max-int)]
    (.log js/console (str "random number: " rnd-number))
    (d/set-text! (d/by-id quiz-integer-var-id) rnd-number)))

(defn ^:export main []
  (events/listen! (d/by-id button-gen-integer-id)
                  :click
                  (fn [event]
                    (update-text-field)
                    (events/stop-propagation event)
                    (events/prevent-default event)))
  (events/listen! (d/by-id button-answer-id)
                  :click
                  (fn [event]
                    (post-for-eval (get-expr))
                    (events/stop-propagation event)
                    (events/prevent-default event))))
