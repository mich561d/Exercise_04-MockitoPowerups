# Exercise_04-MockitoPowerups
By Michael Due

## Requirements
Answer the following questions about Mockito. Use code examples in your explanations.

#### How do you verify that a mock was called?
You can verify that a mock has been called by using Mockito's `verify()` method - Which can be seen in this [example](https://github.com/mich561d/Exercise_04-MockitoPowerups/blob/a9dd06d419a6c30cb099da724bce0d334bc01c40/src/test/java/unit/servicelayer/player/CreatePlayerTest.java#L37). You specify a mock and if the test calls that mock the test will parse and if not the test will fail.

#### How do you verify that a mock was NOT called?
You can verify that a mock has not been called by using Mockito's `never()` or `times(0)` method - Which could have been used in this [example](https://github.com/mich561d/Exercise_04-MockitoPowerups/blob/a9dd06d419a6c30cb099da724bce0d334bc01c40/src/test/java/unit/servicelayer/player/CreatePlayerTest.java#L37). You specify a mock and if the test calls that mock the test will fail and if not the test will parse.

#### How do you specify how many times a mock should have been called?
You can verify that a mock has been called a specified amount of times by using Mockito's `times()` method - Which can be seen in this [example](https://github.com/mich561d/Exercise_04-MockitoPowerups/blob/a9dd06d419a6c30cb099da724bce0d334bc01c40/src/test/java/unit/servicelayer/player/CreatePlayerTest.java#L37). You specify an amount of times that the test will call a mock and the test will fail if that amount has not been reached and parses if the amount has been reached.

#### How do you verify that a mock was called with specific arguments?


#### How do you use a predicate to verify the properties of the arguments given to a call to the mock?


# Exercise_04-C_Tic_Tac_Toe
By Michael Due

## Requirements
Make a tic-tac-toe game using TDD. It should play against the human player. 

Include following:
- Coverage report (e.g. Jacoco).
- Mutation testing (e.g. PITest).
- Static analysis (e.g. Findbugs, PMD, CheckStyle). 

#### Coverage report

#### Mutations testing

#### Static analysis
