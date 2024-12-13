Sudoku Game README

*****************************************************************
The final project with all files is located in Sudoku(10).zip
*****************************************************************

Overview
This project is a Java-based Sudoku game featuring two main modes: Regular Sudoku and Killer Sudoku. The game allows players to play Sudoku puzzles with different difficulty levels (Easy, Medium, Hard, Extreme). The user interface is built using Java Swing, providing an interactive grid with input fields for the Sudoku board and color-coded cages for the Killer Sudoku variant.

Features
Game Modes:

Regular Sudoku: Classic Sudoku game with the typical 9x9 grid.
Killer Sudoku: In addition to the regular Sudoku rules, the grid is divided into "cages," each with a sum constraint.
Difficulty Levels:

Easy
Medium
Hard
Extreme
User Interface:

A grid of 9x9 cells is displayed where the user can enter numbers.
Color-coded cages for the Killer Sudoku mode.
A cage sum is shown in the top-left corner of each cage in Killer Sudoku mode.
Mistake Tracking: Players are limited to three mistakes. The game tracks mistakes and ends the game if the player exceeds the maximum allowed mistakes.

Game State Persistence:

Players' progress is visually represented as they fill out the grid.
If a player enters an incorrect number, they are alerted with a mistake tracker.
Installation
Make sure you have Java 11 or higher installed on your machine.

Clone this repository or download the source files.

Compile and run the project using your IDE or command line.

If you are using the command line:

bash
Copy code
javac Sudoku.java
java Sudoku
Usage
Launch the game. You will be greeted with a main menu offering two Sudoku modes: Regular Sudoku and Killer Sudoku.
Choose a mode and a difficulty level.
The Sudoku board will be displayed. Start entering numbers into the grid.
For Killer Sudoku mode, the cages will be color-coded, and the sum for each cage will be displayed.
If you make an incorrect entry, the game will track mistakes.
The game ends after you either solve the puzzle or exceed the mistake limit.
Game Logic
Regular Sudoku follows traditional Sudoku rules: fill the grid with numbers 1 to 9 such that each row, column, and 3x3 subgrid contains all digits without repetition.
Killer Sudoku adds complexity by grouping cells into cages. Each cage must sum to a specific value. Players must fill each cage while adhering to regular Sudoku rules.
The grid includes a mistake tracker that prevents players from making more than three mistakes.
Classes and Key Components
Cell: Represents a single cell in the Sudoku grid. It handles user input, displaying values, and managing the pencil (possible numbers for unsolved cells).
Cage: Represents a group of cells that must adhere to a sum constraint in Killer Sudoku.
Sudoku: The main class that handles the game logic, UI, and interaction. It initializes the game, sets up the board, and manages player inputs.
Testing
This project uses JUnit for unit testing. The tests focus on the core game functionality, including:

Validating the correctness of the Sudoku board generation.
Ensuring that the player’s inputs are correctly processed.
Checking the behavior of the game modes and difficulty levels.
To run the tests:
Ensure you have JUnit 5 in your classpath.
Run the following command to execute the tests:
bash
Copy code
javac -cp .:junit-platform-console-standalone-1.7.2.jar Sudoku.java
java -cp .:junit-platform-console-standalone-1.7.2.jar org.junit.platform.console.ConsoleLauncher --scan-classpath
Contributing
Fork the repository.
Create a feature branch (git checkout -b feature/your-feature).
Commit your changes (git commit -am 'Add new feature').
Push to the branch (git push origin feature/your-feature).
Open a pull request with a detailed description of your changes.
