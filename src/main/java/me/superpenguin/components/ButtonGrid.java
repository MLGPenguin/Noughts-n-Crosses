package me.superpenguin.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonGrid {

    /** [row][column] (top left first) */
    private BoardButton[][] buttons;

    public ButtonGrid(BoardButton[][] buttons){
        this.buttons = buttons;
    }

    public int getAmountOfRows(){ return buttons.length; }
    public int getAmountOfColumns() { return buttons[0].length; }

    /**
     * get the row of buttons, beginning at row 0.
     * @param row
     * @return
     */
    public List<BoardButton> getRow(int row){
        return new ArrayList<>(Arrays.asList(buttons[row]));
    }

    public List<List<BoardButton>> getRows() {
        List<List<BoardButton>> rows = new ArrayList<>();
        for (int i = 0 ; i < getAmountOfRows() ; i++) rows.add(getRow(i));
        return rows;
    }

    /**
     * Gets the column of buttons, beginning at 0.
     * @param column
     * @return
     */
    public List<BoardButton> getColumn(int column){
        List<BoardButton> list = new ArrayList<>();
        for (int i = 0 ; i < buttons.length ; i ++) list.add(buttons[i][column]);
        return list;
    }

    public List<List<BoardButton>> getColumns(){
        List<List<BoardButton>> columns = new ArrayList<>();
        for (int i = 0 ; i < getAmountOfColumns() ; i++) columns.add(getColumn(i));
        return columns;
    }

    /**
     * Get all the diagonals of length 3.
     * @return
     */
    public List<List<BoardButton>> getLength3Diagonals(){
        List<List<BoardButton>> diagonals = new ArrayList<>();
        diagonals.add(new ArrayList<>());
        diagonals.add(new ArrayList<>());
        for (int i = 0 ; i < 3 ; i++) diagonals.get(0).add(buttons[i][i]);
        for (int i = 0, j = 2 ; i < 3 ; i++, j--) diagonals.get(1).add(buttons[i][j]);
        return diagonals;
    }

    public List<List<BoardButton>> getCombinations(){
        List<List<BoardButton>> matches = new ArrayList<>();
        matches.addAll(getColumns());
        matches.addAll(getRows());
        matches.addAll(getLength3Diagonals());
        return matches;
    }
}
