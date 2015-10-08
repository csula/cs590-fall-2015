# Advanced Software Architecture

[![Join the chat at https://gitter.im/csula/cs590-fall-2015](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/csula/cs590-fall-2015?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Welcome to CS590 Advanced Software Architecture. This is a fast-paced course with emphasis architectural designs, coding, and implementation.  Please check the `README.md` often for news and announcements.

## Quick Links

* [Syllabus](Syllabus.md)
* Lecture 1: [Introduction](documents/notes/01_introduction.md) and [Functional Programming Primer](documents/notes/02_functional-primer.md).
* Lecture 2: [Classic Software Architecture](documents/notes/03_classic-software-architecture.md)

## Announcements

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

`let` is a clojure special form (fundamental part of the language).  It's not a function per se.  That being said, it task a vector as an argument.  The expansion of the vector a set of variables and their values, respectively.

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

