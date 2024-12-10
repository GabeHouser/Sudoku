import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class SudokuTest {

    Sudoku sudokuApp;

    @BeforeEach
    void setUp() {
        sudokuApp = new Sudoku(); // Assuming SudokuApp is the main class
    }

    @AfterEach
    void tearDown() {
        sudokuApp = null; // Cleanup
    }

    @Test
    void main() {
        // Test to ensure the main method starts the application
        assertDoesNotThrow(() -> Sudoku.main(new String[]{}));
    }
    @Test
    void testSudokuBoardGUIInitialization() {
        // Call the method to initialize the GUI
        sudokuApp.sudokuBoardGUI(Mode.REGULAR);

        // Check that the frame is initialized
        JFrame frame = sudokuApp.frame;
        assertNotNull(frame, "Frame should be initialized.");
        assertEquals("Sudoku Board", frame.getTitle(), "Frame title should be 'Sudoku Board'.");
        assertEquals(650, frame.getWidth(), "Frame width should be 650.");
        assertEquals(625, frame.getHeight(), "Frame height should be 625.");
        assertTrue(frame.isVisible(), "Frame should be visible.");

        // Check that the main cell grid is present
        JPanel cellGrid = sudokuApp.cellGrid;
        assertNotNull(cellGrid, "Cell grid should be initialized.");
        assertEquals(9 * 9, cellGrid.getComponentCount(), "Cell grid should have 81 components.");

        // Verify each cell
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField field = sudokuApp.disBoard[i][j];
                assertNotNull(field, "Each cell should be initialized as a JTextField.");
                assertEquals("", field.getText(), "Each cell should be empty initially.");
                assertTrue(field.isEditable(), "Editable cells should be true for empty board.");
            }
        }

        // Verify the status label
        JLabel statusLabel = sudokuApp.statusLabel;
        assertNotNull(statusLabel, "Status label should be initialized.");
        assertEquals("Mistakes: 0/3", statusLabel.getText(), "Initial status label text should show no mistakes.");
    }
    @Test
    void startMenu() {
        // Test that the startMenu initializes correctly
        sudokuApp.startMenu();
        assertNotNull(sudokuApp.frame); // Ensure frame is created
        assertEquals("Sudoku", sudokuApp.frame.getTitle());
        assertEquals(600, sudokuApp.frame.getWidth());
        assertEquals(400, sudokuApp.frame.getHeight());
    }
    @Test
    void isBoardSolved() {
        // Set up a solved board and test the method
        int[][] solvedBoard = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        sudokuApp.solution = solvedBoard;

        // Test with an unsolved board
        int[][] unsolvedBoard = {
                {1, 2, 3, 4, 5, 6, 7, 8, 0},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
                // Rest of the rows omitted for brevity
        };
        sudokuApp.board = unsolvedBoard;
        assertFalse(sudokuApp.isBoardSolved());
    }
    @Test
    void generateBoard() {
        sudokuApp.generateBoard(Mode.REGULAR, Difficulty.EASY);
        assertNotNull(sudokuApp.solution);
        assertEquals(9, sudokuApp.solution.length);
        for (int[] row : sudokuApp.solution) {
            assertEquals(9, row.length);
        }
    }
    @Test
    void removeCellsForDifficulty() {
        sudokuApp.generateBoard(Mode.REGULAR, Difficulty.EASY);
        int emptyCells = 0;
        for (int[] row : sudokuApp.board) {
            for (int cell : row) {
                if (cell == 0) emptyCells++;
            }
        }
        assertTrue(emptyCells > 0 && emptyCells == 5);

        sudokuApp.generateBoard(Mode.REGULAR, Difficulty.MEDIUM);
        emptyCells = 0;
        for (int[] row : sudokuApp.board) {
            for (int cell : row) {
                if (cell == 0) emptyCells++;
            }
        }
        assertTrue(emptyCells > 0 && emptyCells == 15);

        sudokuApp.generateBoard(Mode.REGULAR, Difficulty.HARD);
        emptyCells = 0;
        for (int[] row : sudokuApp.board) {
            for (int cell : row) {
                if (cell == 0) emptyCells++;
            }
        }
        assertTrue(emptyCells > 0 && emptyCells == 30);

        sudokuApp.generateBoard(Mode.REGULAR, Difficulty.EXTREME);
        emptyCells = 0;
        for (int[] row : sudokuApp.board) {
            for (int cell : row) {
                if (cell == 0) emptyCells++;
            }
        }
        assertTrue(emptyCells > 0 && emptyCells == 40);

        sudokuApp.generateBoard(Mode.KILLER, Difficulty.EASY);
        emptyCells = 0;
        for (int[] row : sudokuApp.board) {
            for (int cell : row) {
                if (cell == 0) emptyCells++;
            }
        }
        assertTrue(emptyCells > 0 && emptyCells == 5);

        sudokuApp.generateBoard(Mode.KILLER, Difficulty.MEDIUM);
        emptyCells = 0;
        for (int[] row : sudokuApp.board) {
            for (int cell : row) {
                if (cell == 0) emptyCells++;
            }
        }
        assertTrue(emptyCells > 0 && emptyCells == 30);

        sudokuApp.generateBoard(Mode.KILLER, Difficulty.HARD);
        emptyCells = 0;
        for (int[] row : sudokuApp.board) {
            for (int cell : row) {
                if (cell == 0) emptyCells++;
            }
        }
        assertTrue(emptyCells > 0 && emptyCells == 40);

        sudokuApp.generateBoard(Mode.KILLER, Difficulty.EXTREME);
        emptyCells = 0;
        for (int[] row : sudokuApp.board) {
            for (int cell : row) {
                if (cell == 0) emptyCells++;
            }
        }
        assertTrue(emptyCells > 0 && emptyCells == 50);
    }
    @Test
    void getCageSum() {
        sudokuApp.generateBoard(Mode.REGULAR, Difficulty.EASY);
        assertNotNull(sudokuApp.cages);
        assertTrue(sudokuApp.cages.isEmpty());
        sudokuApp.generateBoard(Mode.REGULAR, Difficulty.MEDIUM);
        assertNotNull(sudokuApp.cages);
        assertTrue(sudokuApp.cages.isEmpty());
        sudokuApp.generateBoard(Mode.REGULAR, Difficulty.HARD);
        assertNotNull(sudokuApp.cages);
        assertTrue(sudokuApp.cages.isEmpty());
        sudokuApp.generateBoard(Mode.REGULAR, Difficulty.EXTREME);
        assertNotNull(sudokuApp.cages);
        assertTrue(sudokuApp.cages.isEmpty());

        sudokuApp.generateBoard(Mode.KILLER, Difficulty.EASY);
        assertNotNull(sudokuApp.cages);
        assertFalse(sudokuApp.cages.isEmpty());
        sudokuApp.generateBoard(Mode.KILLER, Difficulty.MEDIUM);
        assertNotNull(sudokuApp.cages);
        assertFalse(sudokuApp.cages.isEmpty());
        sudokuApp.generateBoard(Mode.KILLER, Difficulty.HARD);
        assertNotNull(sudokuApp.cages);
        assertFalse(sudokuApp.cages.isEmpty());
        sudokuApp.generateBoard(Mode.KILLER, Difficulty.EXTREME);
        assertNotNull(sudokuApp.cages);
        assertFalse(sudokuApp.cages.isEmpty());
    }
    @Test
    void generateCages() {
        sudokuApp.generateBoard(Mode.REGULAR, Difficulty.EASY);
        sudokuApp.generateCages();
        assertNotNull(sudokuApp.cages);
        assertFalse(sudokuApp.cages.isEmpty());
    }
    @Test
    void generateCageColors() {
        Map<Integer, Color> colors = sudokuApp.generateCageColors();
        assertNotNull(colors);
        assertEquals(sudokuApp.cages.size(), colors.size());

        // Check for distinct colors
        Set<Color> uniqueColors = new HashSet<>(colors.values());
        assertEquals(colors.size(), uniqueColors.size());
    }
    @Test
    void testHandlePlayerInput_validInput_noMistake() {
        int[][] solvedBoard = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        sudokuApp.solution = solvedBoard;

        JTextField cell = new JTextField();
        cell.setText("1"); // Valid input
        int row = 0, col = 0;

        sudokuApp.handlePlayerInput(row, col, cell);

        // Verify the input is accepted and board is updated
        assertEquals("1", cell.getText());
        assertEquals(Color.BLACK, cell.getForeground());
    }

    @Test
    void testHandlePlayerInput_invalidInput() {
        int[][] solvedBoard = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        sudokuApp.solution = solvedBoard;

        JTextField cell = new JTextField();
        cell.setText("12"); // Invalid input
        int row = 0, col = 0;

        sudokuApp.handlePlayerInput(row, col, cell);

        // Ensure board does not update for invalid input
        assertEquals(0, sudokuApp.board[row][col]); // Default value
        assertEquals("12", cell.getText());
    }

    @Test
    void testHandlePlayerInput_mistake() {
        int[][] solvedBoard = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        sudokuApp.solution = solvedBoard;

        JTextField cell = new JTextField();
        cell.setText("9");
        int row = 0, col = 0;

        sudokuApp.handlePlayerInput(row, col, cell);

        assertEquals(0, sudokuApp.mistakes);
    }

    @Test
    void testHandlePlayerInput_gameOver() {
        int[][] solvedBoard = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        sudokuApp.solution = solvedBoard;

        sudokuApp.maxMistakes = 1; // Set low for testing

        JTextField cell = new JTextField();
        cell.setText("9");
        int row = 0, col = 0;

        sudokuApp.handlePlayerInput(row, col, cell);

        // Verify game over behavior
        assertFalse(cell.isEditable());
    }
}
