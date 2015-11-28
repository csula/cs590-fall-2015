(ns multi-client-scrabble.core
  (:require [reagent.core :as reagent :refer [atom]]
            [multi-client-scrabble.websockets :as ws]))

(defonce messages (atom []))

(defn message-list []
  [:ul
   (for [[i message] (take 500 (reverse (sort-by second (map-indexed vector @messages))))]
     ^{:key i}
     [:p message])])

(defn message-input []
  (let [value (atom nil)]
    (fn []
      [:input.form-control
       {:type :text
        :placeholder "type in a message and press enter"
        :value @value
        :on-change #(reset! value (-> % .-target .-value))
        :on-key-down
        #(when (= (.-keyCode %) 13)
           (ws/send-transit-msg!
            {:message @value})
           (reset! value nil))}])))

(defn score-board []
  [:div {:class "panel panel-default"}
   [:div {:class "panel-heading"}
     [:h3
      [:div "Score Board"]]]
   [:div {:class "panel-body"}
    [:div.col-sm-6
     [message-list]]
     [message-input]]])

(defn update-messages! [{:keys [message]}]
  (swap! messages #(vec (conj % message))))

(defn mount-components []
  (reagent/render-component [#'score-board] (.getElementById js/document "scoreboard")))

(defn init! []
  (ws/make-websocket! (str "ws://" (.-host js/location) "/ws") update-messages!)
  (mount-components))


