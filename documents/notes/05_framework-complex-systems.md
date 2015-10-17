# Framework for Building Complex Software Systems

## Objectives

* Students will learn how to assemble a complex web application system using component-based approach
* Students will write a gradesheet application

## Metrics/Desired Outcomes

* Build a clojure web application using Liningen
* Modify and update requirements 
* Modify and update code to refelect requirement changes

## Reading Assignment

* Ford, Neal. [Agile Architect & Design](http://nealford.com/downloads/Agile_Architecture_and_Design(Neal_Ford).pdf).
* Sierra, Stuart. [Clojure in the Large](http://www.infoq.com/presentations/Clojure-Large-scale-patterns-techniques).  This ~38 minute talk is about how one builds a large-scale system using clojure.
* Wiggins, Adam. [The Twelve-Factor App](http://12factor.net/).  This development and deployment pattern is a streamline and must-have approach to modern web application.
* Pratley, Timothy. [Light Table Workflow for Interactive Clojure Development](https://www.safaribooksonline.com/blog/2013/09/11/light-table-workflow-for-interactive-clojure-development/)

# Build a Complex Web Application from Scratch

We will utilize the [duct](https://github.com/weavejester/duct) framework for building web application.  To get started, you will need to create a project template:

```bash
lein new duct gradesheet +site +example
```

Next add the following dependencies to your `project.clj` file.
```clojure
[com.novemberain/monger "2.1.0"]
[selmer "0.9.2"]
```

Let us now modify the routes found in `src/gradesheet/example.clj`

```clojure
(GET "/quiz/:number" [number] (show-quiz number) )
```

This means that you will need to create a `show-quiz` function and that function should take a number as an argument.

To render a webpage, you will need to create:

```clojure
(ns lms.utils.layout
  (:require [selmer.parser :as parser]
            [ring.util.response :refer [content-type response]]
            [ring.util.anti-forgery :as af]
            [compojure.response :refer [Renderable]]
            [clojure.java.io :as io]))

(deftype RenderableTemplate [template params]
  Renderable
  (render [this request]
    (content-type
     (->> (assoc params
                 :servlet-context (:context request))
          (parser/render (slurp template))
          response)
     "text/html; charset=utf-8")))

(defn render [template & [params]]
  (parser/add-tag! :csrf-field (fn [_ _] (af/anti-forgery-field)))
  (let [template-path (io/file (io/resource template))]
    (RenderableTemplate. template-path params)))
```

The models code for storing database example for a counter is as followed:

```clojure
(ns gradesheet.models.counter
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.result :refer [ok? has-error?]]))

(def conn (mg/connect))
(def db (mg/get-db conn "gradesheet")) ;; database name
(def document "counter") ;; document

(defn write-counter
  [number]
  (mc/insert-and-return db document {:counter number}))

(defn read-counter
  [] 
  (mc/find-maps db document { } ))
```

Putting everything together, you should have a simple web application.
