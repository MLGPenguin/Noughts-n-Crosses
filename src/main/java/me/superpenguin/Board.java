package me.superpenguin;

import me.superpenguin.components.BoardButton;
import me.superpenguin.components.BoardButton.ButtonState;
import me.superpenguin.components.ButtonGrid;
import me.superpenguin.components.FinishPage;
import me.superpenguin.components.PFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Board {

    private static final int SCREEN_WIDTH = 360;
    private static final int SCREEN_HEIGHT = 640;
    public static final Font BOARD_FONT = new Font(null, -1, 50);

    private ButtonState nextMove = ButtonState.CROSS;
    private JLabel moveLabel = BoardButton.asLabel(nextMove);
    private ButtonGrid grid;
    private PFrame frame;

    public ButtonState getNextMove() { return nextMove; }


    public Board(){}

    public Board(PFrame frame){
        this.frame = frame;
    }

    public void start(){
        PFrame newframe = new PFrame("Noughts n Crosses", SCREEN_WIDTH, SCREEN_HEIGHT);
        // Check it hasn't been constructed using an existing frame.
        if (frame == null) this.frame = newframe;
        frame.getContentPane().add(getPanel());
        frame.open();
    }

    public JPanel getPanel(){
        JPanel board = new JPanel();
        board.setPreferredSize(new Dimension());
        board.setLayout(new GridLayout(4, 3));

        board.add(new JLabel("Next Move: ", JLabel.RIGHT));
        board.add(moveLabel);
        board.add(new JLabel(""));

        BoardButton[][] buttonarr = new BoardButton[3][3];
        for (int i = 0; i < 9 ; i++) {
            BoardButton b = new BoardButton(this);
            buttonarr[i/3][i%3] = b;
            board.add(b);
        }
        grid = new ButtonGrid(buttonarr);
        return board;
    }

    public void onChangeButtonState(){
        this.nextMove = nextMove == ButtonState.NOUGHT ? ButtonState.CROSS : ButtonState.NOUGHT;
        moveLabel.setForeground(nextMove.getColor());
        moveLabel.setText(nextMove.getText());
        checkForWin();
    }

    private void checkForWin(){
        List<List<BoardButton>> matches = grid.getCombinations();
        // Remove any lengths with null states - (haven't yet been touched)
        matches.removeIf(list -> list.stream().map(button -> button.getState()).toList().contains(null));
        if (matches.size() == 0) return;
        for (List<BoardButton> list : matches){
            list.removeIf(button -> button.getState() == ButtonState.NOUGHT);
            if (list.size() == 0 || list.size() == 3){
                triggerWin(list.size() == 0 ? ButtonState.NOUGHT : ButtonState.CROSS);
                return;
            }
        }
        if (matches.size() == 8) new FinishPage(this.frame, null).open();
    }

    private void triggerWin(ButtonState winner){
        new FinishPage(this.frame, winner).open();
    }



}
