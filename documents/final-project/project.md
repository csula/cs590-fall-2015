# Final Project

Here are the requirements for the final project:

* You should tie together assignments and lectures to include the following:
   * login and registration
   * quiz and grade
   * security (session or token)

* You **do not** have to do homework page/tab

* Your software must be able to support dynamic data
  * You **cannot hard code** anything. For example, the number of quizzes, number of questions per quiz, or number of choices per question must not be hardcoded into the `html`, `clj`, or `cljs`. You can think of being dynamic as followed: If the teacher wants to add new a new quiz, does he/she have to edit `.html`, `.clj`, or `.cljs`? If the answer is yes then it is _not_ dynamic.
  * Grades should be reflect the actual quiz results.  One such way to think of this is that you have a view that has all of the grades in one page.

* Please be prepared to discuss architecture, trade design decision, guide rails, etc.

* You will also be measured on the _quality of the product_ in other words, is this a graduate-level product or is this an undergraduate-level product.  While this might appear to be subjective, it is in fact not.  Please take a look at [web pages that suck](http://www.webpagesthatsuck.com/) for inspiration.  Ask yourself, how does your product measure up to [them](http://www.webpagesthatsuck.com/)?

Finally, I am interested in your explanation of the architecture.  Be sure to understand it thoroughly.  I realize that you are working in teams, please don't hide in the shadow of your teammate.  

### Extra Credit

1. Implement a captcha that is other than google captcha
2. Implement a third party authentication
3. Develop a mobile client 
4. Implement a SAP model using `reagent` (the application must be fairly mature)

## Grading Rubric

* **Design decisions** you will be given a survey with a few questions.  The questions will speak specifically to your project [5 pts] -- PLEASE NOTE that you will do the survey by yourself.  You are not permitted to collaborate on the design survey. 
* **Functionality** it must do two primary tasks: 1. take quiz/exam, 2. grade quiz/exam.  It also must have a security layer (login and registration) [5 pts]
* **Dynamic content** nothing is to be hardcoded (see discussion above). [5 pts]
* **Look and feel** your project will have to look like a real product.  In other words, a half-baked project is not going to receive the full credit [5 pts]

Note that you must have worked on the project and understand your project's architecture.  If it appears to me that you do not understand the architecture, you will not get credit for the project.  This can be clearly measured in with how you reponse in the `Design Decisions` survey.

