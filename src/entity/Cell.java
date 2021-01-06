package src.entity;

import java.util.Objects;

/**
 * Entity class of Cell on the Board
 */
public class Cell {
    /**
     * if cell is mine or not
     */
    private boolean mine;
    /**
     * if cell is marked or not
     */
    private boolean mark;
    /**
     * if cell is covered or not
     */
    private boolean cover;
    /**
     * if cell is checked or not
     */
    private boolean checked;
    /**
     * number of mines around
     */
    private int value;

    /**
     * Cell constructor
     * initializes cell with default values
     */
    public Cell() {
        this.cover = true;
        this.mine = false;
        this.mark = false;
        this.value = 0;
    }

    public int getValue() {
        return this.value;
    }

    public void uncover() {
        this.cover = false;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean isEmpty() {
        return this.value == 0;
    }

    public boolean isMine() {
        return this.mine;
    }

    public void setMine(boolean b) {
        this.mine = b;
    }

    public boolean isMarked() {
        return this.mark;
    }

    public boolean isCovered() {
        return this.cover;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public boolean isCoveredMine() {
        return this.cover && this.mine;
    }

    public void checked() {
        this.checked = true;
    }

    public void clearChecked() {
        this.checked = false;
    }

    public void setAroundMines(int count) {
        this.value = count;
    }

    public int getAroundMines() {
        return this.value;
    }

    /**
     * Cell toString mthd
     * @return cell fields values as String
     */
    @Override
    public String toString() {
        return "Cell{" +
                "mine=" + mine +
                ", mark=" + mark +
                ", cover=" + cover +
                ", checked=" + checked +
                ", value=" + value +
                '}';
    }

    /**
     * mthd to compare object with other object
     * @param o object to compare with
     * @return bool compare result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return mine == cell.mine &&
                mark == cell.mark &&
                cover == cell.cover &&
                checked == cell.checked &&
                value == cell.value;
    }

    /**
     * hash mthd
     * @return hash code in int
     */
    @Override
    public int hashCode() {
        return Objects.hash(mine, mark, cover, checked, value);
    }
}
