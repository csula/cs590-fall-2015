# Functional Programming Primer

## Objectives
1. Introduction to Functional Programming and Lambda Calculus
2. Practice some examples of FP

## Metrics/Desired Outcomes
* Write a simple `helloworld.clj` from scratch
* Completion of reading assignment and exercise

## Reading Assignment
* [Chapter 3: Doing Things A Clojure Crash Course](https://www.nostarch.com/download/Clojure%20for%20the%20Brave%20and%20True_sample_ch3.pdf). A free pdf version is available from No Starch Press.

## Pure functions
Pure functions have two requirements:
* `referential transparency` -- always return the same result if provided same arguments
* No side effects -- through immutable data structures

Other intangible aspects of Functional Programming:
* Functions are first class citizens
* Treatment of functions (shifting our way of thinking) are more important than data
* Emphasis on algorithm over data structures
* Small and decomposable functions (tasks or actions)

### Recursion
Functional programmers rely heavily on recursion; several important (fringe) benefits of recursion is the enforcement of immutability and making code more elegant.

* Base case (aka stopping condition)
* Induction step (moving to the next recursion)

### Functional Decomposition
Decoupling function and data; breakdown problem into decomposable abstraction rather than data.  The focus here is on functions (doing) versus data (being).

## Introduction to Clojure Videos
1. Getting Started with Light Table (3:14)
2. Types (4:51)
3. Control Flow (6:07)
4. Functions (7:42)
5. Leinigen (4:05)

## Exercise
Write our first `helloworld.clj` clojure program.
* Do this with `Light Table`
* Do this with `lein` project

Run the helloworld using the REPL tool and run it using `lein`

## Non-graded quiz
Using the information that you have learned so far, write a `combine` and a `combine-with-space` function that takes a collection of strings as arguments, and return a single string without a space between the elements and with a single space between the elements, respectively.

This is a good litmus test to see if you can handle the programming assignments for this course.  If you have have trouble with any items below, you should think carefully if CS590 is the correct course at this point in your studies:

* Using `Light Table` to write your code and using the `REPL` to manipulate and debug your functions
* Using `lein` to compile and run from the command line
* Understanding (translating) the quiz objective into code


