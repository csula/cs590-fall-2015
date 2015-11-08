(ns client-server.client
  (:require [goog.net.XhrIo :as xhr]
            [domina :as d]
            [domina.events :as events]))

(def result-id "eval-result")
(def expr-id "eval-expr")
(def button-id "eval-button")
(def url "/eval")

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
