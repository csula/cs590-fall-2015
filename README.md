# Advanced Software Architecture

[![Join the chat at https://gitter.im/csula/cs590-fall-2015](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/csula/cs590-fall-2015?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Welcome to CS590 Advanced Software Architecture. This is a fast-paced course with emphasis architectural designs, coding, and implementation.  Please check the `README.md` often for news and announcements.

## Quick Links

* Lecture 1: [Syllabus](Syllabus.md), [Introduction](documents/notes/01_introduction.md) and [Functional Programming Primer](documents/notes/02_functional-primer.md).
* Lecture 2: [Classic Software Architecture](documents/notes/03_classic-software-architecture.md)
* Lecture 3: [Design Patterns](documents/notes/04_design-patterns.md)
* Lecture 4: [Framework for Building a Complex System](documents/notes/05_framework-complex-systems.md)
* Assignment: [Homework 1](documents/homeworks/homework1.md)
* Lecture 5: [Layered Architecture Pattern](documents/notes/06_layered-architecture.md)
* Lecture 6: [Event Driven Pattern](documents/notes/07_event-driven.md)
* Lecture 7: [Event Driven Pattern revisited](documents/notes/08_clojurescript-event-driven.md)
* Assignment: [Homework 2](documents/homeworks/homework2.md)
* Lecture 8: [Microservices](documents/notes/09_microservices.md)
* Assignment: [Homework 3](documents/homeworks/homework3.md)
* Assignment: [Homework 4](documents/homeworks/homework4.md)
* Assignment: [Project](documents/final-project/project.md)


## Announcements

**12/29/2015**

All after discussion with some students online, I am going to relax the third assignment and remove the SPA requirement.  You are welcome to implement the solution as you see fit without the SPA requirement.  

Also you do not have to complete the assignments by Sunday/Monday.  You can submit it next week (12/4, 12/5, 12/7).  The final "exam" (12/12/2015) will be demoing the final assignment (hw4 and project, which is cleaning up of the 4th assignment and adding some additional features of your choosing).

**12/28/2015**

All a number of you are feeling a tremendous amount of pain with the third homework assignments.  I feel your pain.  Let us discuss ways in which we can make it simpler and achievable.

Friday and Saturday students can meet with me tomorrow at 1:00 PM in our classroom and discuss ways to make this assignment achievable.  In the mean time please enjoy your weekend.

**12/25/2015**

This is a fascinating read on the [Momento Pattern]( https://dzone.com/articles/memento-pattern-1) which allows for a system to save its internal state to an external source; this allows for hibernation of a running system.  Question of the day: does the momento pattern support or contradict the use of immutables?

**11/24/2015**

As you start to think about the last homework assignment and the project, please take a minute out to read [The DZONE guide to enterprize integration 2015 edition](https://dzone.com/storage/assets/668425-dzone-guide-enterpriseintegration.pdf).  Notice the emphasis that is being placed on microservices.

**11/23/2015**

Here is the code for the scoreboard example we did in class:

```clojure
(defn broadcast-score! [msg]
  (println (str "|" msg "|"))
  (doseq [channel @channels]
    (async/send! channel msg)))

(defn mod-message [message] (format "[\"^ \",\"~:message\",\"%s\"]" message))

(defroutes websocket-routes
  (GET "/score/:message" [message] (broadcast-score! (mod-message message)))
  (GET "/ws" [] ws-handler))
```

**11/23/2015**

To perform the authentication API here is the code that you'll need to cut-and-paste:

```clojure
[io.pedestal.interceptor.helpers :refer [definterceptor defhandler]] 

(defhandler token-check [request] 
  (let [ token (get-in request [:headers "x-catlog-token"])] 
    (if (not (= token "o brave new world")) 
      (assoc (ring-resp/response {:body "access denied"}) :status 403))))
```


**11/22/2015**

At my request, [@savaness](https://github.com/savaness) created [ticket #13](https://github.com/csula/cs590-fall-2015/issues/13). To be clear, for [homework 3](https://github.com/csula/cs590-fall-2015/blob/master/documents/homeworks/homework3.md), your assignment will meet the following "guide rails":

1. Single Page Application (1 html)
2. No Refresh (`action` will not have a route)
3. JSON transfer between client/server (all communications between browser and server will be via header `content-type/json`)

If you have `doubts` please see me, post to gitter via public forum or `PM` me on gitter.  You can also [create issues](https://github.com/csula/cs590-fall-2015/issues).  But please understand that **there will not be an extension offered for this assignment**.  [Please don't ask for an extension](documents/notes/images/18zv86xndsjh7png.png).

There are two opportunities to present the homework assignment:
* Sunday 11/29
* Monday 11/30

Finally, this is a reminder that the coding part for the assignment is simple -- we've already demonstrated this for the first two assignments.  It's important that you understand the architecture aspect.  To this end, please be prepared to discuss:

* What is your design?
* Who is talking to who and when?
* What are the components?
* What is architecture pattern?  Event-driven? 
* What are your potential microservices (ok even if you have not implemented any)?
* What are your tradeoffs?

These are questions that I will ask **you** about your project.  You will receive a score from 0 to 10.  0 is nothing done, 10 is eveything done correctly and that you comprehend the architecture of your system.

**11/20/2015**

For Thanksgiving weekend, since the University will be closed on Friday (11/27) *and* Saturday (11/28), let us have class instead on Sunday (11/29) at 1:15 PM. I will confirm location.  We will still have Monday class at regular scheduled time and location.  This addresses ticket #14.

**11/20/2015**

Let us clone this website to your desktop: `https://github.com/luminus-framework/multi-client-ws-immutant`.  It will help work toward the real-time scoreboard example in class.

**11/15/2015**

Here is the `post` payload:

```json
{ 
"lazy-mouse" : {
 "name" : "Jerry",
 "color": "grey"
 }
}
```
Here is the `add-project` fucntion:

```clojure
(defn add-project [request]
  (let [data-map (:json-params request)]
     (clojure.pprint/pprint data-map)
     (ring-resp/created "http://whatever/" "created")))
```

Here is the `get-project` function:

```clojure
(defn get-project [request]
  (let [projname (get-in request [:path-params :project-name])
        value ((keyword projname) mock-project-collection)]
    (bootstrap/json-response value)))
```

Here is the map for your convenience:

```clojure
(def mock-project-collection
 {
  :sleeping-cat {
    :name "Garfield"
    :color "orange"
  }
  :sleeping-dog {
    :name "Odin"
    :color "yellow"
  }
 })
```

**11/14/2015**

Many thanks to [Akshay S.](https://github.com/akshays04) for the tutorial on how to integrate [Google Captcha Tutorial](documents/notes/pdfs/google-captcha.pdf).

**11/12/2015**

Solution for ungraded exercise has been committed to the [event-pattern](src/event-pattern/) folder.  Enjoy!

**11/12/2015**

A number of you are working hard on the homework.  I've decided to significantly reduce your workload by not having a quiz this week.  Please focus on the homework and not worry about the quiz.

**11/11/2015**

For the password checking, consider the following code:

```clojure
(def upper (re-pattern "[A-Z]+"))
(def number (re-pattern "[0-9]+"))
(def special (re-pattern "[\"'!@#$%^&*()?]+"))

(defn strength? [password]
  (not (nil? (and (re-find upper password)
                  (re-find number password)
                  (re-find special password)))))

(defn length? [password]
  (> (count password) 8))

(defn valid-password? [password]
  (and (strength? password) (length? password)))
```

Our approach here is to construct the checks little by little.

**11/10/2015**

One question was asked, how detail should our architecture diagram be?  My response to that is as detail as you need it.  If you want to get a feel for what yours should look like.  Consider this [event-architecture-sketch.png](documents/notes/images/event-architecture-sketch.png) example.

Note that this diagram captures the "essence" of the components and what each components are supposed to do and how the interact.

**11/10/2015**

Folks, I was looking at the [gitter cs590 membership](https://gitter.im/csula/cs590-fall-2015**) and I noticed that there are more students officially registered for the three sections than the number of students participating in gitter.

![gitter cs590 membership](documents/notes/images/gitter.png)

I hope that you participate in the discussion and make it a fun and enjoyable learning experience.  

**11/8/2015**

[Homework 2](documents/homeworks/homework2.md) has been posted.

**11/2/2015**

I will be in the library on Wednesday from 5 to 6 PM.  If you have not completed the homework assignment.  This is your **last** opportunity to demo it to me.  

**10/30/2015**

I will be at the library Saturday from ~~2 to 5~~ 3 to 6. If you need help or want to chat, stop by and see me.

**10/29/2015**

There is a free book that might help some of the people with clojure programming. [Functional Programming for the Object-Oriented Program](http://samples.leanpub.com/fp-oo-sample.pdf)

**10/25/2015**

A student asked where I purchased the clojure tutorial.  Here is the link to [Learning Clojure](http://shop.oreilly.com/product/0636920040194.do) with Adam Bard.

**10/24/2015**

The [course syllabus](Syllabus.md) has been updated.  The schedule is firm and final. 

**10/24/2015**

I am going to be in the library today (Saturday) from 12:00 to 5:00 PM.  Please feel free to stop by if you need help.  Note also in doing a rough estimation on the time required for first homework assignment, I suspect that students will be spending somewhere between 15 to 20 hours to get the assignment working correctly.  Please refer to the [Homework 1](documents/homeworks/homework1.md) writeup for grading rubric. 

**10/23/2015**

Here are some mongo commands that might be helpful.
```
> show databases  # list all databases 
> use database gradesheet # use a database named gradesheet (make it active)
> show tables # show all tables (documents) in the active database
> db.quiz.find().pretty() # find all documents in the table quiz
> db.runCommand ({ dropDatabase : 1 }) # destroy the active database
```

**10/22/2015**

A few students asked if there is going to be a quiz this week.  Yes! there shall be one.  In fact, this is to note that we will have a quiz every week for the rest of the quarter. This week's quiz will be on:
  * Design Patterns and Architecture Patterns
  * Architecture Framework (to prepare for this do the exercise described in lab 4)

**10/21/2015**

A number of students have asked my about homework 1. Your task is to write a registration system.  This homework is due 10/30, 10/31, 11/2 (depending which class you're attending). You are should get started right away.

* [10/21/2015**] I have updated [Lecture 4 Notes](documents/notes/05_framework-complex-systems.md) with a detail tutorial on how to get a web application running.  Students are recommended to go through the process before attempting homework 1.

**10/20/2015**

Here are the solutions the programming part of the quiz:

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

**10/20/2015**

Here is an excellent article by [Ed Featherson](https://dzone.com/users/2566190/ed-featherston.html) from [DZone](http://dzone.com) ["Why Do I Need an Architect?"](https://dzone.com/articles/why-do-i-need-an-architect-redux-1).  The main point is:

> From the 10,000 foot level, the architect has ultimate ownership of the technology vision, definition, leadership, and responsibility for the successful delivery of the system.

When you do your first homework assignment, try to focus on the big picture.  In otherwords, try to think as an architect.

**10/15/2015**

No quiz this week.  We will, however, do a complete application from scratch in class.  The goal here is to demonstrate to students a complete project from a software architecture perspective. 

Please note that this is a very tight and compressed class so we will not have time to troubleshoot computer problems.  To be most effective and to maximize our time together, please try to get the development environment working before class.  In summary, students should come to class with the following installed and ready to go:

* MongoDB server
* leiningen
* Light Table or an IDE of their choosing

**10/10/2015**

A student asked about team composition:

> Is it ok to do the project by your own? Since I don't have any friend who take this course, that means I want to do the project by my own. 

I don't have a problem with students going solo.

**10/9/2015**

A student asked: "Could you just let me know what would be the last working day for CS590 for this quarter?"

According to the [CSULA final schedule](http://web.calstatela.edu/classschedule/pdf/fall_book/27FinalexamscheduleFall2015**.pdf):

* Monday Class: 7:30 - 10:00 PM December 7, 2015**
* Friday Class: 4:30 - 7:00 PM December 11, 2015**
* Saturday Class: 8:00 - 10:30 AM December 12, 2015**

That being said, we will try to arrange the final presentation so that all three sections can do presentation together at a common location and time.  Logistics to follow.  To be on the safe side, I recommend that students do not make travel plans earlier than December 13, 2015**.

**10/9/2015**

A number of students asked what do they need to review to be prepared for the quiz.  Students need do the reading assignments and watch the videos listed in the `Reading Assignment` section.  As for the programming part, the quiz will include simple code snippets. You will not be asked to write a full application during the quiz (this is not feasible nor practical).

**10/8/2015**

A student asked me this question:

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

**10/5/2015**

This is to remind students that [clojuredocs.org](http://clojuredocs.org) is an excellent reference site for clojure programming.  Also, note that if you find yourself writing a function or routine, please check to see if this has already been written.  Chances are that someone has already written something similar to what you're intending to write.

**10/2/2015**

As of today, the Monday class is full.  You can no longer switch class unless you have **explicit** permission from me. As a side note, for those of you thinking that by attending the Monday session, you can perhaps get a leg up on the quiz questions, don't bother!  Your quizzes will consist of 5 questions randomly drawn from a pool of questions.

**10/1/2015**

Before looking at the solution, you should take a look at the [non-graded quiz](documents/notes/02_functional-primer.md#non-graded-quiz) and use it as a checkpoint.

**9/30/2015**

Note that we will **not** have a quiz this week.  Quiz 1 will be postponed to the following week.  We will continue to review some more functional programming examples to get people more comfortable.

**9/30/2015**

Non-grade quiz solution & clarification on the MR example in class:
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
**9/28/2015**

Regarding the reading and video-watching assignments, they are light on theory and more on practice.  Note that the reading assignments are required. In otherwords, you will be asked about them on the quiz.
  * Quiz tip 1: Understand the basic tenets of a BOT architecture.
  * Quiz tip 2: Practice code found in chapter 3 of [Clojure for the Brave and True](https://www.nostarch.com/download/Clojure%20for%20the%20Brave%20and%20True_sample_ch3.pdf).

**9/27/2015**

A number of students have asked if they can attend a different session than the one that they are assigned to attend.  My short answer is: it should be fine.  However, please keep in mind that once you attend a different session, you need to stick to that session.  This is because of the varying class dynamics and pace.

**9/26/2015**

Please review the [course syllabus](Syllabus.md) and come to class with questions.  In order for us to be prepared for the first day of class, please bring your laptop computer.  We will have a short non-graded assessment quiz.  The purpose of the quiz is to gauge the the students' programming level.

