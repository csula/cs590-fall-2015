(ns multi-client-scrabble.core
  (:require
   [multi-client-scrabble.handler :refer [app init destroy parse-port]]
   [ring.middleware.reload :as reload]
   [immutant.web :as immutant]
   [environ.core :refer [env]]
   [taoensso.timbre :as timbre])
  (:gen-class))

(defn http-port [port]
  (parse-port (or port (env :port) 3000)))

(defonce server (atom nil))

(defn start-server [port]
  (init)
  (reset! server (immutant/run app :port port)))

(defn stop-server []
  (when @server
    (destroy)
    (immutant/stop @server)
    (reset! server nil)))

(defn start-app [[port]]
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop-server))
  (start-server (http-port port))
  (timbre/info "server started on port:" (:port @server)))

(defn -main [& args]
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop-server))
  (start-app args))
