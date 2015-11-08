(defproject client-server "0.1.0-development"
  :plugins [[lein-cljsbuild "1.0.0"]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [domina "1.0.3"]
                 [compojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]]
  :source-paths ["src/clj"]
  :cljsbuild
  {:builds
   [{:compiler
     {:output-to "resources/public/js/client.js",
      :optimizations :whitespace,
      :pretty-print true},
     :source-paths ["src/cljs"]}]}
  :main client-server.server)
