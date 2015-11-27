(ns multi-client-scrabble.app
  (:require [multi-client-scrabble.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
