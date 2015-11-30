(ns trivia.client
  (:require [goog.net.XhrIo :as xhr]
            [cljs.reader :as reader]
            [domina :as d]
            [domina.events :as events]))

(def btn-get-question-id "btn-get-question")
(def btn-check-answer-id "btn-check-answer")
(def div-question-id "question-div")
(def div-result-id "result-div")

(defn serialize [m] (str m))
(defn de-serialize [s] (reader/read-string s))

(defn choice-to-html [choice]
  (str "<li>" choice "</li>"))

(defn choices-to-html [choices]
  (apply str (map choice-to-html choices)))

(defn question-to-html [buffer]
  (let [m (de-serialize buffer)
        q (:q m)]
    (str "<form>"
         q "<br/>"
         "<ol>"
         (choices-to-html (:c m))
         "</ol>"
         "<input id=answer-box type=text />"
         "</form>")))

(defn receive-question-callback [event]
  (d/set-inner-html! (d/by-id div-question-id)
                     (question-to-html (.getResponseText (.-target event)))))

(defn receive-result-callback [event]
  (.log js/console (str "[i] received result"))
  (d/set-text! (d/by-id div-result-id)
               (.getResponseText (.-target event))))

(defn get-question [event]
  (.log js/console (str "[i] get question"))
  (xhr/send "/get-question" receive-question-callback "GET" "")
  (events/stop-propagation event)
  (events/prevent-default event))

(defn check-answer [event]
  (let [a (.-value (d/by-id "answer-box"))
        b {:answer a}
        body (serialize b)]
    (.log js/console (str "[i] check answer: " body))
    (xhr/send "/check-answer" receive-result-callback "POST" body)
    (events/stop-propagation event)
    (events/prevent-default event)))

(defn ^:export main []
  (events/listen! (d/by-id btn-check-answer-id)
                  :click check-answer)
  (events/listen! (d/by-id btn-get-question-id)
                  :click get-question))
