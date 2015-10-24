# Framework for Building Complex Software Systems

## Objectives

* Students will learn how to assemble a complex web application system using component-based approach
* Students will write a gradesheet application

## Metrics/Desired Outcomes

* Build a clojure web application using Lieningen
* Modify and update requirements 
* Modify and update code to refelect requirement changes

## Reading Assignment

* Ford, Neal. [Agile Architect & Design](http://nealford.com/downloads/Agile_Architecture_and_Design(Neal_Ford).pdf).
* Sierra, Stuart. [Clojure in the Large](http://www.infoq.com/presentations/Clojure-Large-scale-patterns-techniques).  This ~38 minute talk is about how one builds a large-scale system using clojure.
* Wiggins, Adam. [The Twelve-Factor App](http://12factor.net/).  This development and deployment pattern is a streamline and must-have approach to modern web application.
* Pratley, Timothy. [Light Table Workflow for Interactive Clojure Development](https://www.safaribooksonline.com/blog/2013/09/11/light-table-workflow-for-interactive-clojure-development/)

# Build a Complex Web Application from Scratch

## Prerequisites

Before we can begin, make sure that you have the following installed:
* [Leiningen](http://leiningen.org)
* [mongoDB](http://https://www.mongodb.org/downloads#production)
* [Light Table](http://lighttable.com)

## Project Setup

We will utilize the [duct](https://github.com/weavejester/duct) framework for building web application.  To get started, you will need to create a project template:

```bash
lein new duct gradesheet +site +example
```

### Viewing Your First Clojure Web App

You can see a rather barebone web application (something akin to a `hello world!`-esque):
```bash
cd gradesheet
lein repl
user => (go)
```

The function `(go)` (or `(reset)`) is a clojure function that instruct the repl to start running your server.  Note that using the repl is only for development mode.  When you go into production, you will need to create a separate standalone jar file, e.g. `uberjar`.

You can now point your web browser to `http://localhost:3000` and should see a welcome page.

### Add Dependencies for Templating and Models

Next add the following dependencies to your `project.clj` file.
```clojure
[com.novemberain/monger "2.1.0"]
[selmer "0.9.2"]
```

## Build Infrastructure

Create a the file `src/gradesheet/utils/layout.clj`. The purpose of this file is so that you can call inject logic into static HTML pages.

```clojure
(ns gradesheet.utils.layout
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

## Build the `Model`

Next build the model for reading and writing quiz with the mongo database.  Create `src/gradesheet/models/quiz.clj`

```clojure
(ns gradesheet.models.quiz
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.result :refer [ok? has-error?]]))

(def conn (mg/connect))
(def db (mg/get-db conn "gradesheet")) ;; database name
(def document "quiz") ;; document

(defn write-quiz
  [quiz-result]
  (mc/insert-and-return db document quiz-result))

(defn get-quiz
  []
  (mc/find-maps db document { } ))
```

## Build the `View`

To build a view, create the `resources/gradequiz/endpoint/example/quiz.html`

```html
<!DOCTYPE html>
<html lang="en" class="welcome">
  <head>
    <title>Welcome to Duct</title>
    <link rel="stylesheet" href="/assets/normalize.css/normalize.css">
    <link rel="stylesheet" href="/css/site.css">
  </head>
  <body>
    <h1>{{ title }}</h1>
      <form method="POST" action="/grade-quiz">
          {% csrf-field %}
          <input type="hidden" name="number" value="{{ q.number }}" />
          what is {{ q.number }}  x {{ q.number }} ?
          <input type="text" name="answer" /> <br/>
          Name: <input type="text" name="student-name" /> <br/>
          <input type="submit" name="submit" />
      </form>
  </body>
</html>
```

Note that `{{ title }}` and `{{ q.number }}` are injected via a map from the controller (see below).  The `{% csrf-field %}` is the antiforgery token we've discussed in class and is used to prevent forgery `POST` access.

## Build the `Controller`

Let us build the controller by modifying the routes found in `src/gradesheet/example.clj`. Add the following to the routes:

```clojure
(GET "/quiz/:number" [number] (show-quiz number))
(POST "/grade-quiz" [& form] (grade-quiz form))
```

This means that you will need to create the `show-quiz` function:

```clojure
(defn show-quiz
  [n]
  (layout/render "gradesheet/endpoint/example/quiz.html"
                 {:title (str "Quiz " n)
                  :q {:number n}}))
```

Note that the `grade-quiz` function has a helper function `(get-result ..)` 

```clojure
(defn get-result
  [number answer]
  (let [correct-answer (* number number)]
    (if (= answer correct-answer)
      (str "Correct!")
      (str "Wrong!"))))

(defn grade-quiz
  [form]
  (pprint form)
  (let [answer (Integer. (:answer form))
        number (Integer. (:number form))
        message (get-result number answer)
        student-name (:student-name form)]
    (str (my-quiz-model/write-quiz {:name student-name
                               :number number
                               :answer answer
                               :message message}))))
```

## Putting everthing together

You should have a simple quiz application.
