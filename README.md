<<<<<<< HEAD
# Picasso
## Team Name: HotShots
### Members: Connor, Ford, Desire, and Nabil

An application that allows the user to create expressions that
evaluate to colors and then eventually to images.

The given code base is a good start, but it is sparsely documented
(document each method/part after you understand what it's doing) and,
as your application grows, you may need to refactor.

See the specification for Picasso on the course web site.

## Extensions 

### 1.  Animation for mutated expressions
Expressions saved in the expressions directory are mutated with randomly generated expressions. The 'Create Animations' button creates 10 random mutations and cross-fades into the next image. The GUI must be reset before creating more mutations.

*Note: A mutant may sometimes be evaluated as black for every pixel on the canvas.

### 2. Random expression generation:

A recursive method generates expressions randomly, displays them in the text field and evaluates them.  

*Note: Method is written to have a higher chance of generating functions and operators than colors and xy in order to create more interesting expressions

### 3. Saved assignment expressions:
Expressions assigned to variables can be viewed by clicking the "View saved expressions" button. The variables and their expressions are displayed on a pop-up box in alphabetical order. When there are no saved variables, a user-friendly message is displayed on the pop-up box. 

## Running Picasso

To run Picasso, run `picasso.Main`

## Project Organization

`src` - the source code for the project

`conf` - the configuration files for the project

The `images` directory contains some sample images generated from Picasso.  Some of the expressions for these images can be found in the `expressions` directory.

## Code Base History

This code base originated as a project in a course at Duke University.  The professors realized that the code could be designed better and refactored.  This code base has some code leftover from the original version.
=======
# Picasso
>>>>>>> e3e47d86d5e418a11c90521f4b031592d2f09b59
