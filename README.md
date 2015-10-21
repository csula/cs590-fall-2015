# Advanced Software Architecture

[![Join the chat at https://gitter.im/csula/cs590-fall-2015](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/csula/cs590-fall-2015?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Welcome to CS590 Advanced Software Architecture. This is a fast-paced course with emphasis architectural designs, coding, and implementation.  Please check the `README.md` often for news and announcements.

## Quick Links

* Lecture 1: [Syllabus](Syllabus.md), [Introduction](documents/notes/01_introduction.md) and [Functional Programming Primer](documents/notes/02_functional-primer.md).
* Lecture 2: [Classic Software Architecture](documents/notes/03_classic-software-architecture.md)
* Lecture 3: [Design Patterns](documents/notes/04_design-patterns.md)
* Lecture 4: [Framework for Building a Complex System](documents/notes/05_framework-complex-systems.md)
* Assignment: [Homework 1](documents/homeworks/homework1.md)

## Announcements

* [10/21/2015] A number of students have asked my about homework 1. Your task is to write a registration system.  This homework is due 10/30, 10/31, 11/2 (depending which class you're attending). You are should get started right away.

* [10/21/2015] I have updated [Lecture 4 Notes](documents/notes/05_framework-complex-systems.md) with a detailed tutorial on how to get a web application running.  Students are recommended to go through the process before attempting homework 1.

* [10/20/2015] Here are the solutions the programming part of the quiz:

```clojure
;;; finding the RMS
(defn sqrt [x] (Math/sqrt x))
(defn sq [x] (* x x))

(defn rms
  [X]
  (let [n (count X)
        one-over-n (/ 1 n)]
     (sqrt (* one-over-n (reduce + (map sq X))))))

(def X (range 3 1023))
(rms X)
```

```clojure
;;; finding the standard deviation
(defn sqrt [x] (Math/sqrt x))
(defn sq [x] (* x x))
(defn diff [x] (- x mu))
(defn avg [X] (/ (reduce + X) (count X)))
(defn std
  [mu X]
  (let [n (count X)
        one-over-N (/ 1 n)
        Y (map #(- %1 mu) X)
        YS (map sq Y)]
    (sqrt (* one-over-N (reduce + YS)))))

(def X (range 1 1025 2))
(std (avg X) X)
```

```clojure
;;; finding the minimum distance
(defn sqrt [x] (Math/sqrt x))
(defn sq [x] (* x x))
(defn dist 
  [p] 
  (sqrt (reduce + (map sq [(:x p) (:y p) (:z p)]))))

(reduce 
 min (map dist [{:x 1 :y 2 :z 3} {:x 2 :y 4 :z -2} {:x 1 :y -1 :z 0}]))
```


* [10/20/2015] Here is an excellent article by [Ed Featherson](https://dzone.com/users/2566190/ed-featherston.html) from [DZone](http://dzone.com) ["Why Do I Need an Architect?"](https://dzone.com/articles/why-do-i-need-an-architect-redux-1).  The main point is:

> From the 10,000 foot level, the architect has ultimate ownership of the technology vision, definition, leadership, and responsibility for the successful delivery of the system.

When you do your first homework assignment, try to focus on the big picture.  In otherwords, try to think as an architect.

* [10/15/2015] No quiz this week.  We will, however, do a complete application from scratch in class.  The goal here is to demonstrate to students a complete project from a software architecture perspective. 

Please note that this is a very tight and compressed class so we will not have time to troubleshoot computer problems.  To be most effective and to maximize our time together, please try to get the development environment working before class.  In summary, students should come to class with the following installed and ready to go:
  * MongoDB server
  * leiningen
  * Light Table or an IDE of their choosing

* [10/10/2015] A student asked about team composition:

> Is it ok to do the project by your own? Since I don't have any friend who take this course, that means I want to do the project by my own. 

I don't have a problem with students going solo.

* [10/9/2015] A student asked: "Could you just let me know what would be the last working day for CS590 for this quarter?"

According to the [CSULA final schedule](http://web.calstatela.edu/classschedule/pdf/fall_book/27FinalexamscheduleFall2015.pdf):

  * Monday Class: 7:30 - 10:00 PM December 7, 2015
  * Friday Class: 4:30 - 7:00 PM December 11, 2015
  * Saturday Class: 8:00 - 10:30 AM December 12, 2015

That being said, we will try to arrange the final presentation so that all three sections can do presentation together at a common location and time.  Logistics to follow.  To be on the safe side, I recommend that students do not make travel plans earlier than December 13, 2015.

* [10/9/2015] A number of students asked what do they need to review to be prepared for the quiz.  Students need do the reading assignments and watch the videos listed in the `Reading Assignment` section.  As for the programming part, the quiz will include simple code snippets. You will not be asked to write a full application during the quiz (this is not feasible nor practical).

* [10/8/2015] A student asked me this question:

> I had a doubt regarding the "let" function, while teaching us you told us that "let" function can be used to create variables which could be used within a particular scope. 

Here is an example of how `let` is used:
```clojure
(defn f []
  (let [a "string a"
        b 1
        c #{1 2 3}]
     (println "a =" a)
     (println "b =" b)
     (println "c =" c)))
```

> I was planning to use it within my function, but I got an error which said that "let" function can be used only on vectors (I was trying to use it on a set).  I wanted to know if there is any particular reason behind this restriction. 

To be clear `let` is a clojure special form (i.e. a fundamental part of the language) and not a "function" _per se_.  That being said, its arguments are consisted of first a declaration vector and clojure expression after.  The expansion of this vector argument is a set of interweaving variables and their values, respectively.

> And, my other doubt was if I had to create a temporary variable usable only inside a recursive function and if I use "def" function to do that then on every recursive call I will be creating a variable with the same name, will it cause a problem? 

The use of `def` is not encouraged and should be used sparingly.  In this sense whenever you define a variable with `def` it is only visible in that scope.  When exiting from the scope the variable goes out of scope.

* [10/5/2015] This is to remind students that [clojuredocs.org](http://clojuredocs.org) is an excellent reference site for clojure programming.  Also, note that if you find yourself writing a function or routine, please check to see if this has already been written.  Chances are that someone has already written something similar to what you're intending to write.

* [10/2/2015] As of today, the Monday class is full.  You can no longer switch class unless you have **explicit** permission from me. As a side note, for those of you thinking that by attending the Monday session, you can perhaps get a leg up on the quiz questions, don't bother!  Your quizzes will consist of 5 questions randomly drawn from a pool of questions.

* [10/1/2015] Before looking at the solution, you should take a look at the [non-graded quiz](documents/notes/02_functional-primer.md#non-graded-quiz) and use it as a checkpoint.

* [9/30/2015] Note that we will **not** have a quiz this week.  Quiz 1 will be postponed to the following week.  We will continue to review some more functional programming examples to get people more comfortable.

* [9/30/2015] Non-grade quiz solution & clarification on the MR example in class:
```clojure
(defn combine [& args]
  "Return a string as joined collection"
  (str (apply str args)))
  
(defn combine-with-space [& args]
  "Return a string that is a joined collection with each element
   having a space appended"
  (str (apply str (map (fn [x] (str x " ")) args))))

(println (combine "John" "Mike" "James"))
(println (combine-with-space "John" "Mike" "James"))
```
* [9/28/2015] Regarding the reading and video-watching assignments, they are light on theory and more on practice.  Note that the reading assignments are required. In otherwords, you will be asked about them on the quiz.
  * Quiz tip 1: Understand the basic tenets of a BOT architecture.
  * Quiz tip 2: Practice code found in chapter 3 of [Clojure for the Brave and True](https://www.nostarch.com/download/Clojure%20for%20the%20Brave%20and%20True_sample_ch3.pdf).

* [9/27/2015] A number of students have asked if they can attend a different session than the one that they are assigned to attend.  My short answer is: it should be fine.  However, please keep in mind that once you attend a different session, you need to stick to that session.  This is because of the varying class dynamics and pace.

* [9/26/2015] Please review the [course syllabus](Syllabus.md) and come to class with questions.  In order for us to be prepared for the first day of class, please bring your laptop computer.  We will have a short non-graded assessment quiz.  The purpose of the quiz is to gauge the the students' programming level.

