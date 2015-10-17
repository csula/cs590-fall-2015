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

## Design Phase

### Functional requirements

### Non-functional (quality attributes)

## Analysis Phase

### Tradeoffs

### Risk

### Architecture Drivers

 
