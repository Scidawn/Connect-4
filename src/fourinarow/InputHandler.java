package fourinarow;

public class InputHandler {
    public InputHandler(){

    }

    public boolean recognize(Game game, String input){
        input = input.toLowerCase().strip();
        if (input.equals("exit")){
            System.exit(0);
            return true;
        }
        else {
            try {
                int x = Integer.parseInt(input) -1;
                return enterCheck(game, game.getField(), x);
            } catch (NumberFormatException e){
                System.out.println("You should enter numbers!");
                return false;
            }
        }
    }

    private static boolean enterCheck(Game game, Field field, int x) {
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
                enter(game, x, y);
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

    private static void enter(Game game, int x, int y) {
        Slot player = game.getActivePlayer() == 1 ? Slot.X : Slot.O;
        game.getField().setSlot(x,y,player);
    }
}
