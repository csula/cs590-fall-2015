# Design Patterns & Software Architecture Design Patterns

## Objectives

* Introduction to design patterns
* Understand how design patterns can impact architecture design and decisions

## Metrics/Desired Outcomes

* Comprehension of design patterns
* Develop a program that models an example architecture pattern
* Evaluate design patterns

## Reading Assignment

* Schott, George. [CS164 Lecture 3 Design Patterns](https://youtu.be/mym5m-GKG0Q).  This is an *excellent* talk on design patterns.  We will watch part of this video in class.

* Banas, Derek. [Design Patterns in Java](http://www.newthinktank.com/videos/design-patterns-tutorial/).

  * We will watch the introduction video and the student will get to select one of the specific design pattern videos.

* Sierra, Stuart. [Components Just Enough Structure](https://www.youtube.com/watch?v=13cmHf_kt-Q).  This talks about a common pattern: **Components** (or stateful objects) structure.  This video is optional and students will not be quizzed on the video.

## Design Patterns

* Introduced/pioneered by the [Gang of Four](https://en.wikipedia.org/wiki/Design_Patterns) with their seminal book [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.worldcat.org/title/design-patterns-elements-of-reusable-object-oriented-software/oclc/31171684).
  * Language du'jour was C++ -- but adaptable to many OO languages
  * There are known problems with [GoF](http://c2.com/cgi/wiki?ShowTrialOfTheGangOfFour)

* Purpose of Design Patterns: do not reinvent the wheel.  Commonly observed problems can be abstracted and solved with an existing pattern

(This section is directly from Wikipedia and GoF Book)

### Categories of Design Patterns

* Creational Patterns: used to create objects
* Structural Patterns: used to compose (modify) objects
* Behavioral Patterns: used for communications between objects

### Creational Patterns

* Abstract factory pattern groups object factories that have a common theme.
* Builder pattern constructs complex objects by separating construction and representation.
* Factory method pattern creates objects without specifying the exact class to create.
* Prototype pattern creates objects by cloning an existing object.
* Singleton pattern restricts object creation for a class to only one instance.

### Structural Patterns

* Adapter allows classes with incompatible interfaces to work together by wrapping its own interface around that of an already existing class.
* Bridge decouples an abstraction from its implementation so that the two can vary independently.
* Composite composes zero-or-more similar objects so that they can be manipulated as one object.
* Decorator dynamically adds/overrides behaviour in an existing method of an object.
* Facade provides a simplified interface to a large body of code.
* Flyweight reduces the cost of creating and manipulating a large number of similar objects.
* Proxy provides a placeholder for another object to control access, reduce cost, and reduce complexity.

### Behavioral Patterns

* Chain of responsibility delegates commands to a chain of processing objects.
* Command creates objects which encapsulate actions and parameters.
* Interpreter implements a specialized language.
* Iterator accesses the elements of an object sequentially without exposing its underlying representation.
* Mediator allows loose coupling between classes by being the only class that has detailed knowledge of their methods.
* Memento provides the ability to restore an object to its previous state (undo).
* Observer is a publish/subscribe pattern which allows a number of observer objects to see an event.
* State allows an object to alter its behavior when its internal state changes.
* Strategy allows one of a family of algorithms to be selected on-the-fly at runtime.
* Template method defines the skeleton of an algorithm as an abstract class, allowing its subclasses to provide concrete behavior.
* Visitor separates an algorithm from an object structure by moving the hierarchy of methods into one object.
 
## Design Patterns in Software Architecture

This area is still fairly open; however we can imagine that there are a number of observable patterns or behaviors in the life of a software engineering and software architect

* Deployment Pattern: how does one deploy software to the public?
* Registration Pattern: how does a system register new users?
* Defense-in-Depth Pattern: what is the underlining architecture for defending a software system.
* Peer-to-Peer
* Client-server 

### Software Design Pattern Exercise

Develop a registration flow that is re-usable. Apply the following SA principles:

* Design space
* Architecture tradeoffs
* Architecture drivers
* Risk analysis
