package com.github.scidawn.connectfour;

public class InputHandler {
    private Game game;
    public InputHandler(Game game){
        this.game = game;
    }

    /**
     * Checks if the input is "exit" or a number, evaluates it and if so, enters the number into the game.
     * @param input The input to check.
     * @return True if the input was valid, false if not.
     */
    public boolean recognize(String input){
        input = input.toLowerCase().strip();
        if (input.equals("exit")){
            System.exit(0);
            return true;
        }
        else {
            try {
                int x = Integer.parseInt(input) -1;
                return enterCheck(game.getField(), x);
            } catch (NumberFormatException e){
                System.out.println("You should enter numbers!");
                return false;
            }
        }
    }

    /**
     * Checks if the input is valid and chooses the row to enter the number into.
     * @param field The field to enter the input into.
     * @param x The x-coordinate to enter the input into.
     * @return True if the input was valid, false if not.
     */
    private boolean enterCheck(Field field, int x) {
        try {

            int width = field.getWidth();
            if (x < 0 || x >= width){
                System.out.println("Coordinates should be from 1 to "+width+"!");
                return false;
            }
            else {
                int y = field.getLowestEmptySlot(x);
                if (y == -1){
                    throw new ColumnOccupiedException();
                }
                enter(x, y);
                return true;
            }
        } catch (NumberFormatException e){
            System.out.println("You should enter numbers!");
            return false;
        } catch (ColumnOccupiedException e){
            System.out.println("This column is occupied! Choose another one!");
            return false;
        }
    }

    /**
     * Enters the number into the game.
     * @param x The x-coordinate to enter the number into.
     * @param y The y-coordinate to enter the number into.
     */
    private void enter(int x, int y) {
        Slot player = game.getActivePlayer() == 1 ? Slot.X : Slot.O;
        game.getField().setSlot(x,y,player);
    }
}
