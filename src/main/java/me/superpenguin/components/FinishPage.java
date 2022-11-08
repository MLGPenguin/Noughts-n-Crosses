package me.superpenguin.components;

import javax.swing.*;
import java.awt.*;

import me.superpenguin.Board;
import me.superpenguin.components.BoardButton.ButtonState;

public class FinishPage {

    private PFrame frame;
    private JPanel screen;

    /**
     *
     * @param frame the frame to use
     * @param winner the winner of the game, or null for a tie.
     */
    public FinishPage(PFrame frame, ButtonState winner){
        this.frame = frame;

        JPanel panel = new JPanel();
        GridLayout grid = new GridLayout(2, 1);
        panel.setLayout(grid);
        panel.setPreferredSize(new Dimension(150, 280));

        String wintext = winner == null ? "It's a tie!" : winner.toString().toLowerCase() + " is the winner!";
        JLabel label = new JLabel(wintext);
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);
        JButton restart = new JButton("Play Again");
        restart.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new Board(frame).getPanel());
            frame.revalidate();
        });
        panel.add(restart);

        this.screen = panel;
    }

    public void open(){
        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().add(BorderLayout.CENTER, this.screen);
    }



}
