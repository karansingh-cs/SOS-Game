package product;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import product.GUI;

public class Game;implements ActionListener {

    static int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static int TOTALCOLUMNS;
    static int TOTALROWS;

    JRadioButton blueS = GUI.bS;
    JRadioButton blueO = GUI.bO;
    JRadioButton redS = GUI.rS;
    JRadioButton redO = GUI.rO;
    JRadioButton simple = GUI.sgame;
    JRadioButton general = GUI.ggame;

    public enum Box {
        EMPTY, SignS, SignO
    }

    protected JButton[][] buttons;
    public String turn;

    public enum GameState {
        PLAYING, DRAW, BLUE_WON, RED_WON
    }

    GameState currentGameState;

    public Game() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the size of the grid");
            System.out.print("Row : ");
            TOTALROWS = sc.nextInt();
            System.out.print("Col : ");
            TOTALCOLUMNS = sc.nextInt();

        }
        buttons = new JButton[TOTALROWS][TOTALCOLUMNS];
        initGame();
    }

    private void initGame() {
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                buttons[row][col] = new JButton();

            }
        }
        currentGameState = GameState.PLAYING;
        turn = "Blue";
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    // if (getGameState() == GameState.PLAYING) {
    // for (int i = 0; i < TOTALROWS; i++) {
    // for (int j = 0; j < TOTALCOLUMNS; j++) {
    // if (e.getSource() == buttons[i][j]) {
    // if (turn == "Blue") {
    // if (buttons[i][j].getText().equals("")) {
    // GUI.gameStatusBar.setText("RED");
    // buttons[i][j].setForeground(Color.BLUE);
    // buttons[i][j].setText(GUI.getLetterSelected(blueS, blueO).getText());

    // // Random random = new Random();
    // // int so = random.nextInt(2);
    // // int position = random.nextInt(getTotalRows() * getTotalColumns());
    // // buttons[position / getTotalRows()][position / getTotalColumns()]
    // // .setText(GUI.getLetterSelected(blueS, blueO).getText());

    // turn = (turn == "Blue") ? "Red" : "Blue";
    // }
    // } else {
    // if (buttons[i][j].getText() == "") {
    // GUI.gameStatusBar.setText("BLUE");
    // buttons[i][j].setForeground(Color.RED);
    // // buttons[i][j].setText("O");
    // buttons[i][j].setText(GUI.getLetterSelected(redS, redO).getText());
    // turn = (turn == "Red") ? "Blue" : "Red";
    // }
    // }

    // if (hasWon(buttons)) {

    // if (simple.isSelected()) {
    // turn = (turn == "Red") ? "Blue" : "Red";
    // currentGameState = (getTurn() == "Blue") ? GameState.BLUE_WON
    // : GameState.RED_WON;
    // for (int r = 0; r < getTotalRows(); r++) {
    // for (int c = 0; c < getTotalColumns(); c++) {
    // buttons[r][c].setEnabled(false);
    // }
    // }
    // // gameStatusBar.setText(getGameState() + " Won! Click to play again.");
    // resetGame();

    // } else if (general.isSelected()) {
    // int blueCount = 0;
    // int redCount = 0;
    // int count;

    // {
    // // gameStatusBar.setText(getTurn() + " Won!");
    // count = blueCount = (getTurn() == "Blue") ? ++blueCount : ++redCount;
    // // gameStatusBar.setText(getTurn() + "Count : " + count);
    // // game.resetGame();
    // }
    // }

    // }

    // else if (isDraw()) {
    // currentGameState = GameState.DRAW;
    // // gameStatusBar.setText(getGameState() + "! Click to play again.");
    // resetGame();

    // }

    // }
    // }
    // }
    // }

    // }

    public void resetGame() {
        initGame();
    }

    public int getTotalRows() {
        return TOTALROWS;
    }

    public int getTotalColumns() {
        return TOTALCOLUMNS;
    }

    public JButton getCell(int row, int column) {
        if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS) {
            return buttons[row][column];
        } else {
            return null;
        }
    }

    public int getNumberOfEmptyCells() {
        int numberOfEmptyCells = 0;
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                if (buttons[row][col].getText() == "") {
                    numberOfEmptyCells++;
                }
            }
        }
        return numberOfEmptyCells;
    }

    public String getTurn() {
        return turn;
    }

    // public void actionPerformed(ActionEvent bingo) {
    // public void makeMove(int row, int column) {
    // if (getGameState() == GameState.PLAYING) {
    // for (int i = 0; i < TOTALROWS; i++) {
    // for (int j = 0; j < TOTALCOLUMNS; j++) {
    // {
    // if (bingo.getSource() == buttons[i][j]) {
    // if (turn == "Blue") {
    // if (buttons[i][j].getText().equals("")) {
    // GUI.gameStatusBar.setText("RED");
    // buttons[i][j].setForeground(Color.BLUE);
    // buttons[i][j].setText(GUI.getLetterSelected(blueS, blueO).getText());

    // // Random random = new Random();
    // // int so = random.nextInt(2);
    // // int position = random.nextInt(getTotalRows() * getTotalColumns());
    // // buttons[position / getTotalRows()][position / getTotalColumns()]
    // // .setText(GUI.getLetterSelected(blueS, blueO).getText());

    // turn = (turn == "Blue") ? "Red" : "Blue";
    // }
    // } else {
    // if (buttons[i][j].getText() == "") {
    // GUI.gameStatusBar.setText("BLUE");
    // buttons[i][j].setForeground(Color.RED);
    // // buttons[i][j].setText("O");
    // buttons[i][j].setText(GUI.getLetterSelected(redS, redO).getText());
    // turn = (turn == "Red") ? "Blue" : "Red";
    // }
    // }
    // }}
    // }

    // }
    // }
    // }

    private void updateGameState(String turn, int row, int column) {
        if (hasWon(buttons)) { // check for win
            currentGameState = (turn == "Blue") ? GameState.BLUE_WON : GameState.RED_WON;
        } else if (isDraw()) {
            currentGameState = GameState.DRAW;
        }
        // Otherwise, no change to current state (still GameState.PLAYING).
    }

    boolean isDraw() {
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                if (buttons[row][col].getText() == "") {
                    return false; // an empty cell found, not draw
                }
            }
        }
        return true;
    }

    static boolean search(JButton[][] buttons, int row, int col, String word) {
        // System.out.println("Test value: " + buttons[row][col].getText());
        // System.out.println("ZOME VALUE: " + word.substring(0, 1));
        if (!buttons[row][col].getText().equals(word.substring(0, 1)))
            return false; // If first character of word doesn't match the grid

        int len = word.length();
        for (int dir = 0; dir < 8; dir++) { // Search word in all 8 directions
            int k, rd = row + x[dir], cd = col + y[dir]; // Initialize starting point

            // First character is already checked, match remaining characters
            for (k = 1; k < len; k++) {
                // System.out.println("k: " + k);
                if (rd >= TOTALROWS || rd < 0 || cd >= TOTALCOLUMNS || cd < 0)
                    break; // If index out of bound

                // System.out.println("Substring: " + word.substring(k, k + 1));
                if (!buttons[rd][cd].getText().equals(word.substring(k, k + 1))) // If word not matched
                    break;

                if (buttons[rd][cd].getText().equals(word.substring(k, k + 1))) {
                    // buttons[rd][cd].setBackground(Color.CYAN);
                }

                rd += x[dir]; // Moving in particular direction
                cd += y[dir];
            }

            if (k == len) // If all character matched, then value == length of word
                return true;

            for (int i = 0; i < rd; i++) {
                for (int j = 0; j < cd; j++) {
                    if (buttons[rd][cd].getText().equals(word.substring(k, k + 1))) {
                        buttons[rd][cd].setBackground(Color.CYAN);
                    }
                }
            }
        }
        return false;
    }

    // Searches word in a given matrix in all 8 directions
    boolean hasWon(JButton[][] buttons) {
        for (int row = 0; row < TOTALROWS; row++) {
            for (int col = 0; col < TOTALCOLUMNS; col++) {
                if (search(buttons, row, col, "SOS"))
                    return true;
            }
        }
        return false;
        // system.out.println(hasWon());
    }

    public GameState getGameState() {
        return currentGameState;
    }

    public void makeMove(int row, int column) {

    public void actionPerformed(ActionEvent e) {

        if (getGameState() == GameState.PLAYING) {
            for (int i = 0; i < TOTALROWS; i++) {
                for (int j = 0; j < TOTALCOLUMNS; j++) {
                    if (e.getSource() == buttons[i][j]) {
                        if (turn == "Blue") {
                            if (buttons[i][j].getText().equals("")) {
                                GUI.gameStatusBar.setText("RED");
                                buttons[i][j].setForeground(Color.BLUE);
                                buttons[i][j].setText(GUI.getLetterSelected(blueS, blueO).getText());

                                // Random random = new Random();
                                // int so = random.nextInt(2);
                                // int position = random.nextInt(getTotalRows() * getTotalColumns());
                                // buttons[position / getTotalRows()][position / getTotalColumns()]
                                // .setText(GUI.getLetterSelected(blueS, blueO).getText());

                                turn = (turn == "Blue") ? "Red" : "Blue";
                            }
                        } else {
                            if (buttons[i][j].getText() == "") {
                                GUI.gameStatusBar.setText("BLUE");
                                buttons[i][j].setForeground(Color.RED);
                                // buttons[i][j].setText("O");
                                buttons[i][j].setText(GUI.getLetterSelected(redS, redO).getText());
                                turn = (turn == "Red") ? "Blue" : "Red";
                            }
                        }

                        if (hasWon(buttons)) {

                            if (simple.isSelected()) {
                                turn = (turn == "Red") ? "Blue" : "Red";
                                currentGameState = (getTurn() == "Blue") ? GameState.BLUE_WON
                                        : GameState.RED_WON;
                                for (int r = 0; r < getTotalRows(); r++) {
                                    for (int c = 0; c < getTotalColumns(); c++) {
                                        buttons[r][c].setEnabled(false);
                                    }
                                }
                                // gameStatusBar.setText(getGameState() + " Won! Click to play again.");
                                resetGame();

                            } else if (general.isSelected()) {
                                int blueCount = 0;
                                int redCount = 0;
                                int count;

                                {
                                    // gameStatusBar.setText(getTurn() + " Won!");
                                    count = blueCount = (getTurn() == "Blue") ? ++blueCount : ++redCount;
                                    // gameStatusBar.setText(getTurn() + "Count : " + count);
                                    // game.resetGame();
                                }
                            }

                        }

                        else if (isDraw()) {
                            currentGameState = GameState.DRAW;
                            // gameStatusBar.setText(getGameState() + "! Click to play again.");
                            resetGame();

                        }

                    }
                }
            }

        }

    }
}

}
