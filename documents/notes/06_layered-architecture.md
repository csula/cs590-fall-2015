# Software Architecture Pattern 1: Layered Architecture

## Objectives

* Understand the layered architecture pattern
* Design and implement a simple application using the n-tiered architecture
* Perform analysis on a layered architecture pattern example

## Metrics/Desired Outcomes

* Understand the pattern description
* Know the key concepts of layered architecture pattern
* Identify shortcomings of the layered pattern
* Analyze the 6 dimensions of the layered architecture pattern

## Reading Assignment

* Richards, Mark. [Software Architecture Patterns](http://www.oreilly.com/programming/free/software-architecture-patterns.csp).  Chapter 1: Layered Architecture.

### Pattern Description

* Also known as the n-tier architecture pattern.  
* Found in most Java EE applications.  
* Organized as horizontal layers; each layer performs roles
* Three (four) tiers: Presentation, Logic, Persistance (database)
* Requests are passed from one layer to the next

### Key Concepts

* Separation of concerns
* Each layered is self-contained
* Cannot skip a layer
* Layers are (should be interchangeable)
* Closed vs. Open layer

### Considerations

* General purpose computing -- simple!
* Easy to understand and can be implemented relatively easily
* Sink hole occurs when data pass through a layer without touching it
* 80-20 rule to ensure architecture design is solid

### Pattern Analysis

* Agility
* Ease of Deployment
* Testability
* Performance
* Scalability
* Ease of Development
