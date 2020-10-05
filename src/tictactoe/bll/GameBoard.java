/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import java.util.Arrays;

/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{
    /**
     * @int rowsPlayed[row][column]
     * Describes the rows x columns played, with player number (0 or 1)
     */
    private int[][] rowsPlayed;
    private int currentPlayer;
    private int amtRowsPlayed;
    private int winPlayer;

    /**
     * Reset the gameboard on init
     */
    public GameBoard() {
        resetGame();
    }

    /**
     * Sets current player to 0
     * Resets all rows and columns played to -1 (not played)
     */
    public void resetGame() {
        currentPlayer = 0;
        amtRowsPlayed = 0;
        winPlayer = -1;

        rowsPlayed = new int[3][3];
        for(int[] row : rowsPlayed) {
            Arrays.fill(row, -1);
        }
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        return (currentPlayer == 0 ? 0 : 1);
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        int v = rowsPlayed[row][col];
        if(this.isGameOver()) { return false; }
        if(v == -1) {
            rowsPlayed[row][col] = currentPlayer;

            System.out.println("Field " + row + "x" + col + " was played by Player #" + rowsPlayed[row][col]);

            if(currentPlayer == 0) { currentPlayer = 1; }
            else { currentPlayer = 0; }

            amtRowsPlayed++;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isGameOver()
    {
        /*
        Diagonal 1: 0x0, 1x1, 2x2
        Diagonal 2: 2x0, 1x1, 0x2
        Else:
        All of row 0, all of row 1, all of row 2
        All of col 0, all of col 1, all of col 2
         */

        if(amtRowsPlayed < 9) {
            if( getWinnerHorizontal() == -1 && getWinnerVertical() == -1 && getWinnerDiagonal() == -1) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return true;
        }

    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        getAllWinner();
        return winPlayer;
    }

    public void newGame() {
        resetGame();
    }

    /*
    Private functions (GameBoard exclusive)
     */

    /**
     * getAllWinner() wraps all getWinner functions, to please the tests :-)
     */
    private void getAllWinner() {
        getWinnerHorizontal();
        getWinnerVertical();
        getWinnerDiagonal();
    }

    /**
     * Checks for winner, horizontally
     * @return -1 if none, else return player ID
     */
    private int getWinnerHorizontal() {
        boolean gameOver = false;
        for(int[] row : rowsPlayed) {
            if( ( row[0] == 0 && row[1] == 0 && row[2] == 0 ) || ( row[0] == 1 && row[1] == 1 && row[2] == 1 ) ) {
                gameOver = true;
                winPlayer = row[0];
            }
        }

        if(gameOver) {
            return winPlayer;
        }
        else {
            return -1;
        }
    }

    /**
     * Checks for winner, vertically
     * @return -1 if none, else return player ID
     */
    private int getWinnerVertical() {
        boolean gameOver = false;
        for(int i = 0; i < rowsPlayed.length; i++) {
            if( ( rowsPlayed[0][i] == 0 && rowsPlayed[1][i] == 0 && rowsPlayed[2][i] == 0 ) || ( rowsPlayed[0][i] == 1 && rowsPlayed[1][i] == 1 && rowsPlayed[2][i] == 1 ) ) {
                gameOver = true;
                winPlayer = rowsPlayed[0][i];
            }
        }

        if(gameOver) {
            return winPlayer;
        }
        else {
            return -1;
        }
    }

    /**
     * Checks for winner, diagonally
     * @return -1 if none, else return player Id
     */
    private int getWinnerDiagonal() {
        if( (rowsPlayed[0][0] == 0 && rowsPlayed[1][1] == 0 && rowsPlayed[2][2] == 0) || (rowsPlayed[0][0] == 1 && rowsPlayed[1][1] == 1 && rowsPlayed[2][2] == 1) ) {
            winPlayer = rowsPlayed[0][0];
            return winPlayer;
        }
        else if( (rowsPlayed[2][0] == 0 && rowsPlayed[1][1] == 0 && rowsPlayed[0][2] == 0) || ( (rowsPlayed[2][0] == 1 && rowsPlayed[1][1] == 1 && rowsPlayed[0][2] == 1)) ) {
            winPlayer = rowsPlayed[2][0];
            return winPlayer;
        }
        else {
            return -1;
        }
    }
}
