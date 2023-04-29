package fourinarow;

import java.util.Arrays;

public class Field {
    private static final int WINNING_LENGTH = 4;
    private final int width;
    private final int height;
    private Slot[][] slots;

    public Field() {
        this(7, 6);
    }

    public Field(int width, int height) {
        this.width = width;
        this.height = height;

        slots = new Slot[width][height];
        for (int x = 0; x < width; x++) {
            Arrays.fill(slots[x], Slot.EMPTY);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLowestEmptySlot(int column) {
        for (int y = 0; y < height; y++) {
            if (slots[column][y] == Slot.EMPTY) {
                return y;
            }
        }
        return -1;
    }

    public Slot getSlot(int x, int y) {
        return slots[x][y];
    }

    public void setSlot(int x, int y, Slot slot) {
        slots[x][y] = slot;
    }

    public String getPrintableField() {
        StringBuilder sb = new StringBuilder();
        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                if (slots[x][y] == Slot.EMPTY)
                    sb.append("-");
                else {
                    sb.append(slots[x][y]);
                }
                sb.append("  ");
            }
            sb.append("\n");
        }

        for (int i = 1; i <= width; i++) {
            sb.append(i);
            if (i < 10) {
                sb.append("  ");
            } else
                sb.append(" ");
        }

        sb.append("\n");
        return sb.toString();
    }

    public boolean checkWin() {
        return checkHorizontal() || checkVertical() || checkDiagonal();
    }

    /*
    @Deprecated
    private boolean checkDiagonal() {
        //make diagonals with variable winning length
        for (int x = 0; x < width-4; x++) {
            for (int y = 0; y < height-4; y++) {
                if (slots[x][y] != Slot.EMPTY &&
                        slots[x][y] == slots[x+1][y+1] &&
                        slots[x][y] == slots[x+2][y+2] &&
                        slots[x][y] == slots[x+3][y+3]) {
                    return true;
                }
                if (slots[x+3][y] != Slot.EMPTY &&
                        slots[x+3][y] == slots[x+2][y+1] &&
                        slots[x+3][y] == slots[x+1][y+2] &&
                        slots[x+3][y] == slots[x][y+3]) {
                    return true;
                }
            }
        }
        return false;
    }*/

    /**
     * Checks if there is a diagonal win starting at the given coordinates
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if there is a diagonal win, false otherwise
     */
    private boolean checkDiagonal(int x, int y) {
        boolean upwards = true;
        boolean downwards = true;

        if (slots[x][y] == Slot.EMPTY) {
            return false;
        }

        for (int i = 1; i < WINNING_LENGTH &&
                x + WINNING_LENGTH < width &&
                y + WINNING_LENGTH < height; i++) {
            if (slots[x][y] != slots[x + i][y + i]) {
                upwards = false;
                break;
            }
        }
        for (int i = 1; i < WINNING_LENGTH &&
                x + WINNING_LENGTH <= width &&
                y - WINNING_LENGTH <= height; i++) {
            if (slots[x][y] != slots[x + i][y - i]) {
                downwards = false;
                break;
            }
        }
        return upwards || downwards;
    }

    /**
     * Checks if there is a diagonal win in the field
     *
     * @return true if there is a diagonal win, false otherwise
     */
    private boolean checkDiagonals() {
        for (int x = 0; x <= width - WINNING_LENGTH; x++) {
            for (int y = 0; y <= height; y++) {
                return checkDiagonal(x, y);
            }
        }
        return false;
    }

    /**
     * Checks if there is a vertical win in the field
     *
     * @return true if there is a vertical win, false otherwise
     */
    private boolean checkVertical() {
        for (int x = 0; x < width; x++) {
            int count = 0;
            Slot lastSlot = Slot.EMPTY;
            for (int y = 0; y < height; y++) {
                Slot slot = slots[x][y];
                if (slot == Slot.EMPTY) {
                    count = 0;
                    lastSlot = Slot.EMPTY;
                } else if (slot == lastSlot) {
                    count++;
                    if (count == WINNING_LENGTH) {
                        return true;
                    }
                } else {
                    count = 1;
                    lastSlot = slot;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a horizontal win in the field
     *
     * @return true if there is a horizontal win, false otherwise
     */
    private boolean checkHorizontal() {
        for (int y = 0; y < height; y++) {
            int count = 0;
            Slot lastSlot = Slot.EMPTY;
            for (int x = 0; x < width; x++) {
                Slot slot = slots[x][y];
                if (slot == Slot.EMPTY) {
                    count = 0;
                    lastSlot = Slot.EMPTY;
                } else if (slot == lastSlot) {
                    count++;
                    if (count == WINNING_LENGTH) {
                        return true;
                    }
                } else {
                    count = 1;
                    lastSlot = slot;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the field is full
     *
     * @return true if the field is full, false otherwise
     */
    public boolean checkDraw() {
        for (int x = 0; x < width; x++) {
            if (slots[x][height - 1] == Slot.EMPTY) {
                return false;
            }
        }
        return true;
    }
}
