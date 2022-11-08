package me.superpenguin.components;

import javax.swing.*;

public class PFrame extends JFrame {

    public PFrame(String name, int screenWidth, int screenHeight){
        super(name);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(screenWidth, screenHeight);
    }

    public void open(){ setVisible(true); }



}
