(ns session-demo.config
  (:require [taoensso.timbre :as timbre]))

(def defaults
  {:init
   (fn []
     (timbre/info "\n-=[session-demo started successfully]=-"))
   :middleware identity})
