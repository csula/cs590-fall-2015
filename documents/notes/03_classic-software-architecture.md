# Lecture 2: Classic Software Architecture

## Objectives

* Understand functional programming collection, recursion, and modules
* Understand classic software archiecture curriculum

## Metrics/Desired Outcomes

* Comprehension of collections in clojure 
* Write recursive functions with clojure
* Comprehension of the keypoints of classical software architecture

## Reading Assignment

* Fairbanks, George. [Introduction to Software Architecture](https://www.youtube.com/watch?v=x30DcBfCJRI).  We will watch and discuss this video in class.

## Introduction to Clojure Videos (continued)

1. Collections (7:02)
2. Recursions (8:16)
3. Modules (3:44)

## Classic Software Architecture Curriculum

### Introduction to Software Architecture (0:00 - 29:22)
* Introduction video segment
* Provided some motivations as to why software architecture is important
* Tradeoffs
* Many architectures yield different choices
* Case Study of Rackspace three design models

### Part 1 (29:22 - 54:42)
* Differences between architecture, architecting, and architect
* Views: consitent or inconsistent views; limited view on components; view are specific to task/goal
* Non-functional requirement: quality attributes; dimension of quality to measure software (latency, security, ...)
* Design Space: range and constraints to make a decision
* Tradeoffs: not everything can be addressed or met perfectly
* Architecture Drivers: quality measure of how the system is to perform; helps identify and select architecture choice

### Part 2 (57:00 - 1:16:00)
* Rational Architecture Decisions: `X is a priority so we design Y accepting downside Z`
* Analyzing Views: what is the right diagram answer a question?
* Standard Notation: consistent notation and language for architecture diagram.  Best option is UML.
* Guiderails: constraints (self-imposed)
  * Make sure you understand the `3-tier system`
  * [Itempotency](https://stackoverflow.com/questions/1077412/what-is-an-idempotent-operation): multiple calls on the same function & arguments return the same result.  

## Part 3 (1:16:00 - 1:30:17)
* Architecture Styles
  * Big ball of mud: no coherent style
  * Client-server: request and respond
  * Pipe-and-filter: flow of data that goes from one program to the next
  * Map-Reduce: small digestable chunks 
  * N-Tier: front, logic, storage (for example)
  * Layered: we will cover this in class
* Tradeoffs are built into the patterns
* Conceptual Models: recognition of the concept

### Part 4 (1:30:17 - 1:50:50)
* Engineering Models: models is for simplicity  
* Canonical Models:
  * Domain model
  * Boundary model
  * Internals model
  * Code model
* Architecture vs. Code: cannot tell model from code
  * Negative intention are harder to express in code
  * Efficiency improvements
  * Read your old code: let us talk about it
  * Know the difference between `library` and `framework`

### Part 5 (1:50:50 - end) 
* Architecture is done at large level
  * Comparison between Web and Large System
* Look at failures: analyze and choose solution that minimize penalties on failfure

