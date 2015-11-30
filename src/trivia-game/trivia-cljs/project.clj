(defproject trivia-cljs "0.0.1-development"
  :description "trivia game client"
  :plugins [[lein-cljsbuild "1.0.0"]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [domina "1.0.3"]]
  :cljsbuild
  {:builds
   [{:compiler
     {:output-to "../trivia/resources/public/js/client.js",
      :optimizations :whitespace,
      :pretty-print true},
     :source-paths ["src-cljs"]}]})
