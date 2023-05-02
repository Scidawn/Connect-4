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
        GameState state = GameState.ONGOING;

        while (state == GameState.ONGOING){
            activePlayer = activePlayer % playerCount +1;
            System.out.println(field.getPrintableField());
            System.out.println("Player "+getActivePlayer()+"'s turn.");

            do {
                input = scanner.nextLine();
            } while (!handler.recognize(input));
            state = gameOverState();
        }
        System.out.println(field.getPrintableField());
        if (state == GameState.WIN){
            System.out.println("Player "+getActivePlayer()+" wins!");
        }
        else if (state == GameState.DRAW){
            System.out.println("Draw!");
        }
    }

    /**
     * checks if the game is either won, drawn or ongoing.
     * @return The state of the game.
     */
    private GameState gameOverState() {
        if (field.checkWin())
            return GameState.WIN;
        if (field.checkDraw())
            return GameState.DRAW;
        return GameState.ONGOING;
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