(ns session-demo.config
  (:require [selmer.parser :as parser]
            [taoensso.timbre :as timbre]
            [session-demo.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (timbre/info "\n-=[session-demo started successfully using the development profile]=-"))
   :middleware wrap-dev})
