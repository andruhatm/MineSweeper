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

    /**
     * getter for value field
     * @return count of mines around
     */
    public int getValue() {
        return this.value;
    }

    /**
     * uncover cell
     */
    public void uncover() {
        this.cover = false;
    }

    /**
     * setter for mark field
     * @param mark mark bool value to set
     */
    public void setMark(boolean mark) {
        this.mark = mark;
    }

    /**
     * getter for value field
     * @return if cell has mines around
     */
    public boolean isEmpty() {
        return this.value == 0;
    }

    /**
     * checks if cell is mine
     * @return bool value
     */
    public boolean isMine() {
        return this.mine;
    }

    /**
     * setter for mine field
     * @param b bool value to set
     */
    public void setMine(boolean b) {
        this.mine = b;
    }

    /**
     * checks if cell is marked
     * @return bool value
     */
    public boolean isMarked() {
        return this.mark;
    }

    /**
     * checks if cell is covered
     * @return bool value
     */
    public boolean isCovered() {
        return this.cover;
    }

    /**
     * checks if cell is checked
     * @return bool value
     */
    public boolean isChecked() {
        return this.checked;
    }

    /**
     * checks if cell is covered mine
     * @return bool value
     */
    public boolean isCoveredMine() {
        return this.cover && this.mine;
    }

    /**
     * setter for checked field, set true
     */
    public void checked() {
        this.checked = true;
    }

    /**
     * setter for checked filed, set false
     */
    public void clearChecked() {
        this.checked = false;
    }

    /**
     * setter for value field
     * @param count set mines around count
     */
    public void setAroundMines(int count) {
        this.value = count;
    }

    /**
     * getter for value field
     * @return count of mines
     */
    public int getAroundMines() {
        return this.value;
    }

    /**
     * Cell toString mthd
     * @return cell fields values as String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cell{");
         sb.append("mine=").append(mine);
         sb.append(", mark=").append(mark);
         sb.append(", cover=").append(cover);
         sb.append(", checked=").append(checked);
         sb.append(", value=").append(value);
         sb.append('}');
         return sb.toString();
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
