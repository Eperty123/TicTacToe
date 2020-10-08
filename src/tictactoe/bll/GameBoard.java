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
     * @int nextPlayer
     * Who is the next player
     * @int amtFieldsPlayed
     * Amount of fields "checked/filled" by a player
     * @int winPlayer
     * The winning player, -1 if draw or until a player has won
     */
    private int[][] rowsPlayed;
    private int nextPlayer;
    private int amtFieldsPlayed;
    private int winPlayer;

    /**
     * Reset the gameboard on init
     */
    public GameBoard() {
        resetGame();
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        return nextPlayer;
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
            rowsPlayed[row][col] = nextPlayer;
            nextPlayer = (nextPlayer == 0 ? 1 : 0);
            amtFieldsPlayed++;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks to see if the game is over.
     * The game is over if 9 fields has been played
     * If 9 fields has not been played, then game is
     * over when a winner is found in horizontal,
     * vertical or diagonal direction.
     * @return boolean true if game is over, else return false
     */
    public boolean isGameOver()
    {
        if(amtFieldsPlayed < 9) {
            return getWinnerHorizontal() != -1 || getWinnerVertical() != -1 || getWinnerDiagonal() != -1;
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

    /**
     * Calls the resetGame method
     */
    public void newGame() {
        resetGame();
    }

    /*
    Private functions (GameBoard exclusive)
     */

    /**
     * Sets next player to 0
     * Resets amount of fields played
     * Sets winning player to -1
     * Resets all rows and columns played to -1 (not played)
     */
    private void resetGame() {
        nextPlayer = 0;
        amtFieldsPlayed = 0;
        winPlayer = -1;

        rowsPlayed = new int[3][3];
        for(int[] row : rowsPlayed) {
            Arrays.fill(row, -1);
        }
    }

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
