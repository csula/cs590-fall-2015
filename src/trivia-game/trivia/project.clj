(defproject trivia "0.0.1-development"
  :description "trivia game server"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [selmer "0.9.5"]
                 [markdown-clj "0.9.80"]
                 [environ "1.0.1"]
                 [metosin/ring-middleware-format "0.6.0"]
                 [metosin/ring-http-response "0.6.5"]
                 [ring/ring-defaults "0.1.5"]
                 [io.pedestal/pedestal.service "0.4.1"]
                 [io.pedestal/pedestal.jetty "0.4.1"]
                 [ch.qos.logback/logback-classic "1.1.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.12"]
                 [org.slf4j/jcl-over-slf4j "1.7.12"]
                 [org.slf4j/log4j-over-slf4j "1.7.12"]]
  :min-lein-version "2.0.0"
  :resource-paths ["config", "resources"]
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "trivia.server/run-dev"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.4.1"]]}
             :uberjar {:aot [trivia.server]}}
  :main ^{:skip-aot true} trivia.server)

