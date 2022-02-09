package product;

import java.util.Random;

public class AutoGame extends Game {
    private String autoPlayer;

    public AutoGame() {
        this("O");
    }

    public AutoGame(String player) {
        this.autoPlayer = player;
        if (autoPlayer == "Blue") {
            makeFirstXMove();
        }
    }

    @Override
    public void resetGame() {
        super.resetGame();
        if (autoPlayer == "Blue") {
            makeFirstXMove();
        }
    }

    private void makeFirstXMove() {
        Random random = new Random();
        int position = random.nextInt(TOTALROWS * TOTALCOLUMNS);
        super.makeMove(position / TOTALROWS, position % TOTALCOLUMNS);
    }

    @Override
    public void makeMove(int row, int column) {
        if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS
                && buttons[row][column].getText() == "") {
            super.makeMove(row, column);
            if (turn == autoPlayer && getGameState() == GameState.PLAYING) {
                makeAutoMove();
            }
        }
    }

    private void makeAutoMove() {
        if (!makeWinningMove()) {
            if (!blockOpponentWinningMove())
                makeRandomMove();
        }

    }

    private boolean makeWinningMove() {
        return false;
    }

    private boolean blockOpponentWinningMove() {
        return false;
    }

    private void makeRandomMove() {
        int numberOfEmptyCells = getNumberOfEmptyCells();
        Random random = new Random();
        int targetMove = random.nextInt(numberOfEmptyCells);
        int index = 0;
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                if (buttons[row][col].getText() == "") {
                    if (targetMove == index) {
                        super.makeMove(row, col);
                        return;
                    } else
                        index++;
                }
            }
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

}
