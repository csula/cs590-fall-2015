# Advanced Event Driven Architecture

## Simple client 

Let us take a look at the [event-pattern](src/event-pattern) source code and see how we can add functionalities to the example provided. 

### Server Push to Client

So we discussed three options for pushing information from server to client:

1. timer pull
2. reverse roll (server-client)
3. `core` async persistent connection

### Reading Assignment

These are more references than actual reading assignment.  They are listed here for your benefit.

* http://kanaka.github.io/clojurescript/web/synonym.html
* https://facebook.github.io/flux/docs/overview.html
* https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes
* [sente](https://github.com/ptaoussanis/sente).
* An excellent to the React framework is [Reagent](https://reagent-project.github.io/) which is clojurescript and has the necessary functions to support our goal.
