package com.github.scidawn.connectfour;

import java.util.Scanner;

public class Game {
    private Field field;
    private final int playerCount = 2;
    private int activePlayer = playerCount;
    InputHandler handler;

    public Game(){
        field = new Field();
        handler = new InputHandler(this);
    }

    /**
     * The main game loop.
     */
    private void start() {
        Scanner scanner = new Scanner(System.in);
        String input;
        int state = 0;

        while (state == 0){
            activePlayer = activePlayer % playerCount +1;
            System.out.println(field.getPrintableField());
            System.out.println("Player "+getActivePlayer()+"'s turn.");

            do {
                input = scanner.nextLine();
            } while (!handler.recognize(input));
            state = gameOverState();
        }
        System.out.println(field.getPrintableField());
        if (state == 1){
            System.out.println("Player "+getActivePlayer()+" wins!");
        }
        else if (state == 2){
            System.out.println("Draw!");
        }
    }

    /**
     * Checks if the game is over.
     * @return 1 if a player has won, 2 if the game is a draw, 0 if the game is still going.
     */
    private int gameOverState() {
        if (field.checkWin())
            return 1;
        if (field.checkDraw())
            return 2;
        return 0;
    }

    /**
     * Starts the game.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    /**
     * Getters and setters.
     */
    public Field getField() {
        return field;
    }

    /**
     * Getters and setters.
     */
    public int getActivePlayer() {
        return activePlayer;
    }
}