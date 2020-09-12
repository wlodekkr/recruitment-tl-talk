# recruitment-tl-task-public

## Description
Let's assume you became a leader of the young, immature team, which produces solutions of questionable quality. Below you'll find one of the projects the team is responsible for. Take a look into the repository, do the code review. Point out all the issues with the code quality and how it conforms to clean code (and any other you can think of) principles. Make sure that you look at the code from all possible angles and describe all the possible improvements. Detail all the changes that would have to be done (once again, from all the perspectives you can think of), for this code to become a production ready service (instead of command line application). You could consider resiliency to high loads, things like ddos attacks, auditing etc.

### Task to be reviewed
#### Task description
Implement a REST service which validates a credit card number from a common credit card vendor (Visa, MasterCard). Validator should be implemented  in a way that allows extending it easily when adding new card vendors.

#### Task acceptance criteria
- Request contains only two parameters: card vendor and card number
- First version should support MasterCard and Visa

## Instructions for candidates
- once link to recruitment task branch is received, import repository to your Github account
- create a pull request in your repository
- add pull request comments in places you find appropriate
- also respond to production readiness aspects (resiliency, security, etc.) in a summarizing comment
- make repository public, so that recruiters will be able to review task solution

## Review acceptance criteria
- code review has a form of GitHub pull request comments 
- comments are in English
- code review was finished within a week (168 hours), counting from the moment, when link to recruitment task branch was received
