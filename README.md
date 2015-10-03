# Advanced Software Architecture

[![Join the chat at https://gitter.im/csula/cs590-fall-2015](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/csula/cs590-fall-2015?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Welcome to CS590 Advanced Software Architecture. This is a fast-paced course with emphasis architectural designs, coding, and implementation.  Please check the `README.md` often for news and announcements.

## Quick Links

* [Syllabus](Syllabus.md)
* Lecture 1: [Introduction](documents/notes/01_introduction.md) and [Functional Programming Primer](documents/notes/02_functional-primer.md).
* Lecture 2: [Classic Software Architecture](documents/notes/03_classic-software-architecture.md)

## Announcements

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

