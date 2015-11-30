(ns multi-client-scrabble.core
  (:require [reagent.core :as reagent :refer [atom]]
            [multi-client-scrabble.websockets :as ws]))

(defonce messages (atom []))
(def click-count (atom 0))

(defn get-top-five []
  (take 100 (reverse (sort-by first (map-indexed vector @messages)))))

(defn sort-reverse-by-value [a]
  (sort-by second a))

(defn message-list []
  [:div
   (for [[i message] (get-top-five)]
     ^{:key i}
     [:p i " " message])])

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

(defn game-area []
  [:div {:class "panel panel-primary"}
   [:div {:class "panel-heading"}
    [:h3
     [:div "Game Area"]]]
   [:div {:class "panel-body"}
    [:div.col-sm-6
     [:span "click count: " @click-count ". "
      [:input {:type "button"
               :value "Click me!"
               :on-click #(swap! click-count inc)}]]]]])

(defn update-messages! [{:keys [message]}]
  (swap! messages #(vec (conj % message))))

(defn mount-components []
  (reagent/render-component [#'game-area] (.getElementById js/document "game-area"))
  (reagent/render-component [#'score-board] (.getElementById js/document "scoreboard")))

(defn init! []
  (ws/make-websocket! (str "ws://" (.-host js/location) "/ws") update-messages!)
  (mount-components))

