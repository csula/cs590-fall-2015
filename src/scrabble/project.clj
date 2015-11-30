(defproject multi-client-ws "0.0.1-development"
  :description "multi-client-scrabble"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [selmer "0.9.5"]
                 [com.taoensso/timbre "3.4.0"]
                 [com.taoensso/tower "3.0.2"]
                 [markdown-clj "0.9.82"]
                 [environ "1.0.1"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-session-timeout "0.1.0"]
                 [metosin/ring-middleware-format "0.6.0"]
                 [metosin/ring-http-response "0.6.5"]
                 [bouncer "0.3.3"]
                 [prone "0.8.2"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [org.clojure/clojurescript "0.0-3291" :scope "provided"]
                 [org.clojure/tools.reader "0.10.0"]
                 [reagent "0.5.1"]
                 [cljsjs/react "0.14.3-0"]
                 [reagent-forms "0.5.13"]
                 [reagent-utils "0.1.5"]
                 [secretary "1.2.3"]
                 [org.clojure/core.async "0.2.374"]
                 [cljs-ajax "0.5.1"]
                 [org.immutant/web "2.1.1"]]
  :min-lein-version "2.0.0"
  :uberjar-name "multi-client-scrabble.jar"
  :jvm-opts ["-server"]
  :main multi-client-scrabble.core
  :plugins [[lein-environ "1.0.1"]
            [lein-ancient "0.6.5"]
            [lein-cljfmt "0.3.0"]
            [lein-cljsbuild "1.0.6"]]
  :clean-targets ^{:protect false} [:target-path
                                    "pom.xml"
                                    "figwheel_server.log"
                                    "multi_client_scrabble.log"
                                    "dev-resources"
                                    "resources/public/js"]
  :cljsbuild
  {:builds
   {:app
    {:source-paths ["src-cljs"]
     :compiler
     {:output-dir "resources/public/js/out"
      :externs ["react/externs/react.js"]
      :optimizations :none
      :output-to "resources/public/js/app.js"
      :pretty-print true}}}}
  :profiles
  {:uberjar {:omit-source true
             :env {:production true}
             :hooks [leiningen.cljsbuild]
             :cljsbuild
             {:jar true
              :builds
              {:app
               {:source-paths ["env/prod/cljs"]
                :compiler {:optimizations :advanced :pretty-print false}}}}
             :aot :all}
   :dev {:dependencies [[ring-mock "0.1.5"]
                        [ring/ring-devel "1.4.0"]
                        [pjstadig/humane-test-output "0.7.0"]
                        [weasel "0.7.0"]
                        [lein-figwheel "0.5.0-2"]
                        [org.clojure/tools.nrepl "0.2.12"]
                        [com.cemerick/piggieback "0.2.1"]]
         :source-paths ["env/dev/clj"]
         :plugins [[lein-figwheel "0.3.3"]]
         :cljsbuild
         {:builds
          {:app
           {:source-paths ["env/dev/cljs"] :compiler {:source-map true}}}}
         :figwheel
         {:http-server-root "public"
          :server-port 3449
          :css-dirs ["resources/public/css"]
          :ring-handler multi-client-scrabble.handler/app}
         :repl-options {:init-ns multi-client-scrabble.core}
         :injections [(require 'pjstadig.humane-test-output)
                      (pjstadig.humane-test-output/activate!)]
         :env {:dev true}}})