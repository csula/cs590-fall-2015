(ns multi-client-scrabble.routes.websockets
  (:require [compojure.core :refer [GET defroutes wrap-routes]]
            [taoensso.timbre :as timbre]
            [immutant.web.async       :as async]))

(defonce channels (atom #{}))

(defn notify-clients! [channel msg]
  (doseq [channel @channels]
    (async/send! channel msg)))

(defn connect! [channel]
  (timbre/info "channel open")
  (swap! channels conj channel))

(defn disconnect! [channel {:keys [code reason]}]
  (timbre/info "close code:" code "reason:" reason)
  (swap! channels #(remove #{channel} %)))

(def websocket-callbacks
  "WebSocket callback functions"
  {:on-open connect!
   :on-close disconnect!
   :on-message notify-clients!})

(defn ws-handler [request]
  (async/as-channel request websocket-callbacks))

(defroutes websocket-routes
  (GET "/ws" [] ws-handler))
