package me.superpenguin.components;

import me.superpenguin.Board;

import javax.swing.*;
import java.awt.*;

public class BoardButton extends JButton {

    public enum ButtonState {
        NOUGHT("O", Color.BLUE), CROSS("X", Color.RED);

        private String text;
        private Color color;
        ButtonState(String text, Color color) {
            this.text = text;
            this.color = color;
        }

        public String getText() { return text; }
        public Color getColor() { return color; }
    }

    private Board board;
    private ButtonState state = null;
    private boolean triggered = false;

    public BoardButton(Board board){
        super("-");
        this.board = board;
        setFont(Board.BOARD_FONT);
        addActionListener(event -> {
            if (!triggered){
                setState(board.getNextMove());
                board.onChangeButtonState();
                triggered = true;
            }
        });
    }


    public void setState(ButtonState state){
        this.state = state;
        setText(state.getText());
        setForeground(state.getColor());
    }

    public ButtonState getState() {return state;}

    public static JLabel asLabel(ButtonState state){
        JLabel label = new JLabel(state.getText());
        label.setFont(Board.BOARD_FONT);
        label.setForeground(state.getColor());
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

}
