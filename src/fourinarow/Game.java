package fourinarow;

import java.util.Scanner;

public class Game {
    private Field field;
    private final int playerCount = 2;
    private int activePlayer = playerCount;
    InputHandler handler;

    public Game(){
        field = new Field(7,80);
        handler = new InputHandler();
        gameloop();
    }

    private void gameloop() {
        Scanner scanner = new Scanner(System.in);
        String input;
        int state = 0;

        while (state == 0){
            activePlayer = activePlayer % playerCount +1;
            System.out.println(field.getPrintableField());
            System.out.println("Player "+getActivePlayer()+"'s turn.");

            do {
                input = scanner.nextLine();
            } while (!handler.recognize(this, input));
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

    private int gameOverState() {
        if (field.checkWin())
            return 1;
        if (field.checkDraw())
            return 2;
        return 0;
    }

    public static void main(String[] args) {
        new Game();
    }

    public Field getField() {
        return field;
    }

    public int getActivePlayer() {
        return activePlayer;
    }
}